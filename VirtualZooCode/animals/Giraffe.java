package animals;

import diet.Herbivore;
import mobility.Point;


public class Giraffe extends Animal {

    public Giraffe(String name, double size, int horizontalSpeed, int verticalSpeed, String color) {
        super(name, new Point(50, 0), size, horizontalSpeed, verticalSpeed, color, size, new Herbivore());
        this.loadImages(color.toLowerCase() + "_giraffe_");
    }


    protected double sizeScale() { return 4; }
}