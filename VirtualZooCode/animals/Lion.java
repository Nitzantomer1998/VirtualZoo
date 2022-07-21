package animals;

import diet.Carnivore;
import mobility.Point;


public class Lion extends Animal {
    
    public Lion(String name, double size, int horizontalSpeed, int verticalSpeed, String color) {
        super(name, new Point(20, 0), size, horizontalSpeed, verticalSpeed, color, size, new Carnivore());
        this.loadImages(color.toLowerCase() + "_lion_");
    }


    protected double sizeScale() { return 3.5; }
}