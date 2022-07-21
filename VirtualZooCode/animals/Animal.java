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


    public boolean isThreadSuspended() { return this.threadSuspended; }


    public void eat(IEdible food) {
        double gainWeight = diet.eat(this, food);

        if (gainWeight > 0.0) {
            this.weight = this.weight + gainWeight;
            this.size = this.weight / sizeScale();
            this.eatCounter = this.eatCounter + 1;
        }
    }


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


    @Override
    public void loadImages(String imageName) {
        try {
            this.rightImage = ImageIO.read(new File(PICTURE_PATH + imageName + "right.png"));
            this.leftImage = ImageIO.read(new File(PICTURE_PATH + imageName + "left.png"));
        }
        catch (IOException e) { System.out.println("Failed to load the image"); }
    }


    @Override
    public void drawObject (Graphics g) {
        int width = (int)Math.sqrt(this.weight) + 20;
        int height = (int)Math.sqrt(this.weight) + 20;
        if (this.xDirection == 1)
            g.drawImage(this.rightImage, this.getLocation().getX() - width / 2, this.getLocation().getY() - width / 2, width, height, ZooPanel.getInstance());

        else
            g.drawImage(this.leftImage, this.getLocation().getX() - width / 2, this.getLocation().getY() - width / 2, width, height, ZooPanel.getInstance());
    }


    private void updateWeight(double distanceTraveled) { this.weight = this.weight - (distanceTraveled * this.weight * 0.00025); }
    private void updateSize() { this.size = this.weight / sizeScale(); }

    
    protected abstract double sizeScale();
}
