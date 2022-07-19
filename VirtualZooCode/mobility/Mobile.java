package mobility;

import java.util.Observable;


public class Mobile extends Observable implements ILocatable {

    private Point location;
    private double totalDistance;


    public Mobile(Point location) {
        this.location = new Point(location.getX(), location.getY());
        this.totalDistance = 0.0;
    }


    @Override
    public void setLocation(Point newLocation) { this.location = new Point(newLocation.getX(), newLocation.getY()); }


    @Override
    public Point getLocation() { return this.location; }


    public void addToTotalDistance(double distance) { this.totalDistance = this.totalDistance + distance; }


    public double calcDistance(Point location) {
        double distanceX = Math.pow(this.location.getX() - location.getX(), 2);
        double distanceY = Math.pow(this.location.getY() - location.getY(), 2);
        return Math.pow(distanceX + distanceY, 0.5);
    }

    
    public double move(Point newLocation) {
        double distanceTraveled = this.calcDistance(newLocation);

        this.setLocation(newLocation);
        this.addToTotalDistance(distanceTraveled);

        return distanceTraveled;
    }
}