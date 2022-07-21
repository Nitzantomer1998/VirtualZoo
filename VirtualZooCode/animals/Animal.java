package animals;

import DesignPatterns.ICloneable;
import DesignPatterns.DietFactory;
import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.Mobile;
import mobility.Point;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Abstract class that defines the common attributes to all the animals,
 * inherit from Mobile, and implement Runnable, IEdible, IDrawable, ICloneable
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see Runnable,mobility.Mobile,food.IEdible,graphics.IDrawable,DesignPatterns.ICloneable
 */
public abstract class Animal extends Mobile implements Runnable, IEdible, IDrawable, ICloneable {

    private final String name;
    private final int horizontalSpeed;
    private final int verticalSpeed;
    private final IDiet diet;
    private double size;
    private String color;
    private double weight;
    private int eatCounter;
    private int xDirection;
    private int yDirection;
    private boolean threadSuspended;
    private BufferedImage rightImage;
    private BufferedImage leftImage;


    /**
     * The constructor of the Animal object, Sets the attributes of the object
     *
     * @param name is a String representing the name of the animal
     * @param location is a Point representing the location of the animal
     * @param size is an Integer representing the size of the animal
     * @param horizontalSpeed is an Integer representing the Horizontal speed of the animal
     * @param verticalSpeed is an Integer representing the Vertical speed of the animal
     * @param color is a String representing the color of the animal
     * @param weight is a Double representing the weight of the animal
     * @param diet is a IDiet representing the Type of the animal
     */
    public Animal(String name, Point location, double size, int horizontalSpeed, int verticalSpeed, String color, double weight, IDiet diet) {
        super(location);

        this.name = name;
        this.size = size;
        this.horizontalSpeed = horizontalSpeed;
        this.verticalSpeed = verticalSpeed;
        this.color = color;
        this.weight = weight * sizeScale();
        this.diet = diet;
        this.xDirection = 1;
        this.yDirection = 1;
        this.eatCounter = 0;
        this.threadSuspended = false;
        addObserver(ZooPanel.getInstance().getController());
    }


    /**
     * Setter methods for the fields of the object / class
     */
    public void setThreadSuspended() {
        if (!this.threadSuspended)
            synchronized (this) {
                this.threadSuspended = true;
            }
    }
    public void setThreadResumed() {
        if (this.threadSuspended)
            synchronized (this) {
                this.threadSuspended = false;
                notify();
            }
    }
    public void updateColor(String color) {
        this.color = color;
        this.loadImages(color.toLowerCase() + "_" + this.getClass().getSimpleName().toLowerCase() + "_");
    }


    /**
     * Getter methods for the fields of the object / class
     *
     * @return The wanted field
     */
    public String getName() { return this.name; }
    public double getSize() { return this.size; }
    public int getHorizontalSpeed() { return this.horizontalSpeed; }
    public int getVerticalSpeed() { return this.verticalSpeed; }
    public String getColor() { return this.color; }
    public double getWeight() { return this.weight; }
    public IDiet getDiet() { return this.diet; }
    public int getEatCounter() { return this.eatCounter; }
    public int getEAT_DISTANCE() { return 10; }
    public EFoodType getFoodType() {
        if (this instanceof Lion)
            return EFoodType.NOTFOOD;

        return EFoodType.MEAT;
    }

    /**
     * Method to check is the thread is suspended
     *
     * @return true if suspended, else false
     */
    public boolean isThreadSuspended() { return this.threadSuspended; }


    /**
     * Calls the eat method (from interface IDiet) with the diet attribute
     * which returns the weight gained form the action (if greater than zero, he ate successful)
     * if successful : updating the new weight of the animal (see calculation), else don't
     *
     * @param food is the food that the animal will attempt to eat
     * @see food.IEdible,diet.IDiet
     */
    public void eat(IEdible food) {
        double gainWeight = diet.eat(this, food);

        if (gainWeight > 0.0) {
            this.weight = this.weight + gainWeight;
            this.size = this.weight / sizeScale();
            this.eatCounter = this.eatCounter + 1;
        }
    }


    /**
     * Moving the animal to a new location, calculate the weight she lost in the way, lastly update her current stats
     *
     * @param location is a Point representing location on the axis
     * @return The distance the Animal went
     */
    @Override
    public double move(Point location) {
        double distanceTraveled = super.move(location);

        this.updateWeight(distanceTraveled);
        this.updateSize();

        if (location.getX() == Point.getMaxX() || location.getX() <= Point.getMinXY())
            this.xDirection = this.xDirection * -1;

        if (location.getY() == Point.getMaxY() || location.getY() <= Point.getMinXY())
            this.yDirection = this.yDirection * -1;

        return distanceTraveled;
    }


    /**
     * Method for running a thread code
     */
    @Override
    public synchronized void run() {
        while(true) {
            if (this.threadSuspended) {
                try { wait(); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }

            else {
                if (ZooPanel.getInstance().isAvailableFood())
                    if (this.diet.canEat(ZooPanel.getInstance().getAvailableFood().getFoodType())) {
                        int foodX = ZooPanel.getInstance().getAvailableFood().getLocation().getX();
                        int foodY = ZooPanel.getInstance().getAvailableFood().getLocation().getY();
                        double slope = Math.abs((foodY - this.getLocation().getY()) / (foodX - this.getLocation().getX()));

                        double verticalStep = slope;
                        double horizontalStep = 1 / slope;

                        if (foodY - this.getLocation().getY() < 0)
                            verticalStep *= -1;

                        if (foodX - this.getLocation().getX() < 0) {
                            horizontalStep *= -1;
                            this.xDirection = -1;
                        }

                        else
                            this.xDirection = 1;

                        move(new Point((int)(this.getLocation().getX() + horizontalStep), (int)(this.getLocation().getY() + verticalStep)));
                    }

                    else
                        move(new Point(getLocation().getX() + this.horizontalSpeed * this.xDirection, getLocation().getY() + this.verticalSpeed * this.yDirection));

                else
                    move(new Point(getLocation().getX() + this.horizontalSpeed * this.xDirection, getLocation().getY() + this.verticalSpeed * this.yDirection));

                setChanged();
                notifyObservers();

                try { wait(30); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }


    /**
     * Method for creating deep copy of an object
     *
     * @return The deep copy object
     */
    @Override
    public Animal Clone() {
        Animal animal = new DietFactory().getFactory(getDiet().getClass().getSimpleName()).createAnimal(this.getClass().getSimpleName(), this.name, this.size, this.horizontalSpeed, this.verticalSpeed, this.color);

        animal.setLocation(this.getLocation());
        animal.eatCounter = this.eatCounter;
        animal.xDirection = this.xDirection;
        animal.yDirection = this.yDirection;
        if (this.threadSuspended)
            animal.setThreadSuspended();

        return animal;
    }


    /**
     * Loading an image for our project, from the project folder
     *
     * @param imageName is Image representing the image of the object
     */
    @Override
    public void loadImages(String imageName) {
        try {
            this.rightImage = ImageIO.read(new File(PICTURE_PATH + imageName + "right.png"));
            this.leftImage = ImageIO.read(new File(PICTURE_PATH + imageName + "left.png"));
        }
        catch (IOException e) { System.out.println("Failed to load the image"); }
    }


    /**
     * Drawing the image into the frame g, with specific dimensions
     *
     * @param g is a Graphic representing a frame
     */
    @Override
    public void drawObject (Graphics g) {
        int width = (int)Math.sqrt(this.weight) + 20;
        int height = (int)Math.sqrt(this.weight) + 20;
        if (this.xDirection == 1)
            g.drawImage(this.rightImage, this.getLocation().getX() - width / 2, this.getLocation().getY() - width / 2, width, height, ZooPanel.getInstance());

        else
            g.drawImage(this.leftImage, this.getLocation().getX() - width / 2, this.getLocation().getY() - width / 2, width, height, ZooPanel.getInstance());
    }


    /**
     * Helpful methods for updating the object fields
     */
    private void updateWeight(double distanceTraveled) { this.weight = this.weight - (distanceTraveled * this.weight * 0.00025); }
    private void updateSize() { this.size = this.weight / sizeScale(); }


    /**
     * abstract method in order to set the weight accordingly to the animal (Animal size : Animal weight)
     */
    protected abstract double sizeScale();
}
