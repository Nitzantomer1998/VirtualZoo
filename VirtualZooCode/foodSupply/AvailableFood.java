package foodSupply;

import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * An abstract class representing the available food supply of the zoo, implement ILocatable, IDrawable and IEdible
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see mobility.ILocatable,graphics.IDrawable,food.IEdible
 */
public abstract class AvailableFood implements ILocatable, IDrawable, IEdible {

    protected static AvailableFood instance = null;
    private BufferedImage foodImage;
    private Point location;


    /**
     * The constructor of the AvailableFood object, Sets the fields of the object
     * Note: We're placing the food in the middle of the zoo screen
     *
     */
    protected AvailableFood() { this.location = new Point(Point.getMaxX() / 2, Point.getMaxY() / 2); }


    /**
     * Setter methods for the fields of the object / class
     */
    @Override
    public void setLocation(Point newLocation) { this.location = new Point(newLocation.getX(), newLocation.getY()); }


    /**
     * Getter methods for the fields of the object / class
     *
     * @return The wanted field
     */
    @Override
    public Point getLocation() { return this.location; }


    /**
     * Loading an image for our project from the project folder
     *
     * @param imageName is Image representing the image of the object
     */
    public void loadImages(String imageName) {
        try { foodImage = ImageIO.read(new File(PICTURE_PATH + imageName)); }
        catch (IOException e) { System.out.println("Failed to load the image"); }
    }


    /**
     * Drawing the image into the frame g, with specific dimensions
     *
     * @param g is a Graphic representing a frame
     */
    public void drawObject (Graphics g) {
        g.drawImage(this.foodImage, 375, 275, 20, 20, ZooPanel.getInstance());
    }
}
