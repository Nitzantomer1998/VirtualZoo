package animals;

import diet.Omnivore;
import mobility.Point;


public class Bear extends Animal {

    public Bear(String name, double size, int horizontalSpeed, int verticalSpeed, String color) {
        super(name, new Point(100, 5), size, horizontalSpeed, verticalSpeed, color, size, new Omnivore());
        this.loadImages(color.toLowerCase() + "_bear_");
    }


    protected double sizeScale() { return 7; }
}