package mobility;


/**
 * Interface to describe place functionality
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 */
public interface ILocatable {

    void setLocation(Point newLocation);
    Point getLocation();
}