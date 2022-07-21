package animals;

import diet.Herbivore;
import mobility.Point;


public class Elephant extends Animal {

    public Elephant(String name, double size, int horizontalSpeed, int verticalSpeed, String color) {
        super(name, new Point(50, 90), size, horizontalSpeed, verticalSpeed, color, size, new Herbivore());
        this.loadImages(color.toLowerCase() + "_elephant_");
    }


    protected double sizeScale() { return 10; }
}