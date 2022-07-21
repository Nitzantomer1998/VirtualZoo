package animals;

import diet.Omnivore;
import mobility.Point;


/**
 * A class representing a Bear, and inherit from Animal
 * Note : a Bear is an Omnivore
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see animals.Animal
 */
public class Bear extends Animal {

    /**
     * The constructor of the Bear object, Sets the attributes of the object
     *
     * @param name is a String representing the name of the animal
     * @param size is an Integer representing the size of the animal
     * @param horizontalSpeed is an Integer representing the Horizontal speed of the animal
     * @param verticalSpeed is an Integer representing the Vertical speed of the animal
     * @param color is a String representing the color of the animal
     */
    public Bear(String name, double size, int horizontalSpeed, int verticalSpeed, String color) {
        super(name, new Point(100, 5), size, horizontalSpeed, verticalSpeed, color, size, new Omnivore());
        this.loadImages(color.toLowerCase() + "_bear_");
    }


    /**
     * Method for providing the ratio between the size to the weight of the object (1 : 7 KG)
     *
     * @return The ratio between the size to the weight (1 : 7 KG)
     */
    protected double sizeScale() { return 7; }
}