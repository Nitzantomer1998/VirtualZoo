package animals;

import diet.Herbivore;
import mobility.Point;


public class Turtle extends Animal {

    public Turtle(String name, double size, int horizontalSpeed, int verticalSpeed, String color) {
        super(name, new Point(80, 0), size, horizontalSpeed, verticalSpeed, color, size, new Herbivore());
        this.loadImages(color.toLowerCase() + "_turtle_");
    }


    protected double sizeScale() { return 1; }
}