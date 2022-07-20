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


public abstract class AvailableFood implements ILocatable, IDrawable, IEdible {

    protected static AvailableFood instance = null;
    private BufferedImage foodImage;
    private Point location;


    protected AvailableFood() { this.location = new Point(Point.getMaxX() / 2, Point.getMaxY() / 2); }


    @Override
    public void setLocation(Point newLocation) { this.location = new Point(newLocation.getX(), newLocation.getY()); }


    @Override
    public Point getLocation() { return this.location; }


    public void loadImages(String imageName) {
        try { foodImage = ImageIO.read(new File(PICTURE_PATH + imageName)); }
        catch (IOException e) { System.out.println("Failed to load the image"); }
    }

    
    public void drawObject (Graphics g) {
        g.drawImage(this.foodImage, 375, 275, 20, 20, ZooPanel.getInstance());
    }
}
