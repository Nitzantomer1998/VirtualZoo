package animals;

import diet.Herbivore;
import mobility.Point;


/**
 * A class representing an Elephant, and inherit from Animal
 * Note : an Elephant is a Herbivore
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see animals.Animal
 */
public class Elephant extends Animal {

    /**
     * The constructor of the Elephant object, Sets the attributes of the object
     *
     * @param name is a String representing the name of the animal
     * @param size is an Integer representing the size of the animal
     * @param horizontalSpeed is an Integer representing the Horizontal speed of the animal
     * @param verticalSpeed is an Integer representing the Vertical speed of the animal
     * @param color is a String representing the color of the animal
     */
    public Elephant(String name, double size, int horizontalSpeed, int verticalSpeed, String color) {
        super(name, new Point(50, 90), size, horizontalSpeed, verticalSpeed, color, size, new Herbivore());
        this.loadImages(color.toLowerCase() + "_elephant_");
    }


    /**
     * Method for providing the ratio between the size to the weight of the object (1 : 10 KG)
     *
     * @return The ratio between the size to the weight (1 : 10 KG)
     */
    protected double sizeScale() { return 10; }
}