package mobility;

import java.util.Observable;


/**
 * Class to define movement in space, extends Observable and implement ILocatable
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see Observable,mobility.ILocatable
 */
public class Mobile extends Observable implements ILocatable {

    private Point location;
    private double totalDistance;


    /**
     * The constructor of the Mobile object, Sets the fields of the object
     *
     * @param location is a Point representing location on the axis
     */
    public Mobile(Point location) {
        this.location = new Point(location.getX(), location.getY());
        this.totalDistance = 0.0;
    }


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
     * Getting a Point object and calculate the distance between em, relocate this object to the received Point
     * lastly add the distance traveled into the object data
     *
     * @param newLocation is a Point representing location on the axis
     * @return The distance made by our object
     */
    public double move(Point newLocation) {
        double distanceTraveled = this.calcDistance(newLocation);

        this.setLocation(newLocation);
        this.addToTotalDistance(distanceTraveled);

        return distanceTraveled;
    }


    /**
     * Helpful methods for the move method
     */
    private void addToTotalDistance(double distance) { this.totalDistance = this.totalDistance + distance; }
    private double calcDistance(Point location) {
        double distanceX = Math.pow(this.location.getX() - location.getX(), 2);
        double distanceY = Math.pow(this.location.getY() - location.getY(), 2);
        return Math.pow(distanceX + distanceY, 0.5);
    }
}