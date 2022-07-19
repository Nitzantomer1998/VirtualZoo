package mobility;


public class Point {

    private static final int MAX_X = 800;
    private static final int MAX_Y = 600;
    private static final int MIN_XY = 0;
    private final int x;
    private final int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public static int getMaxX() { return Point.MAX_X; }
    public static int getMaxY() { return Point.MAX_Y; }
    public static int getMinXY() { return Point.MIN_XY; }
}