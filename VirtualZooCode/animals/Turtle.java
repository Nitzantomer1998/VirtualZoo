package animals;

import diet.Herbivore;
import mobility.Point;


/**
 * A class representing a Turtle, and inherit from Animal
 * Note : a Turtle is a Herbivore
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see animals.Animal
 */
public class Turtle extends Animal {

    /**
     * The constructor of the Turtle object, Sets the attributes of the object
     *
     * @param name is a String representing the name of the animal
     * @param size is an Integer representing the size of the animal
     * @param horizontalSpeed is an Integer representing the Horizontal speed of the animal
     * @param verticalSpeed is an Integer representing the Vertical speed of the animal
     * @param color is a String representing the color of the animal
     */
    public Turtle(String name, double size, int horizontalSpeed, int verticalSpeed, String color) {
        super(name, new Point(80, 0), size, horizontalSpeed, verticalSpeed, color, size, new Herbivore());
        this.loadImages(color.toLowerCase() + "_turtle_");
    }


    /**
     * Method for providing the ratio between the size to the weight of the object (1 : 1 KG)
     *
     * @return The ratio between the size to the weight (1 : 1 KG)
     */
    protected double sizeScale() { return 1; }
}