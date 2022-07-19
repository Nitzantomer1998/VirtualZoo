package mobility;


/**
 * Class to define position on a two-dimensional Axis
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 */
public class Point {

    private static final int MAX_X = 800;
    private static final int MAX_Y = 600;
    private static final int MIN_XY = 0;
    private final int x;
    private final int y;


    /**
     * The constructor of the Point object, Sets the fields of the object
     *
     * @param x is an Integer representing the location on axis X
     * @param y is an Integer representing the location on axis Y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Getter methods for the fields of the object / class
     *
     * @return The wanted field
     */
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public static int getMaxX() { return Point.MAX_X; }
    public static int getMaxY() { return Point.MAX_Y; }
    public static int getMinXY() { return Point.MIN_XY; }
}