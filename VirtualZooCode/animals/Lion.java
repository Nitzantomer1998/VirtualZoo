package animals;

import diet.Carnivore;
import mobility.Point;


/**
 * A class representing a Lion, and inherit from Animal
 * Note : a Lion is a Carnivore
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see animals.Animal
 */
public class Lion extends Animal {

    /**
     * The constructor of the Lion object, Sets the attributes of the object
     *
     * @param name is a String representing the name of the animal
     * @param size is an Integer representing the size of the animal
     * @param horizontalSpeed is an Integer representing the Horizontal speed of the animal
     * @param verticalSpeed is an Integer representing the Vertical speed of the animal
     * @param color is a String representing the color of the animal
     */
    public Lion(String name, double size, int horizontalSpeed, int verticalSpeed, String color) {
        super(name, new Point(20, 0), size, horizontalSpeed, verticalSpeed, color, size, new Carnivore());
        this.loadImages(color.toLowerCase() + "_lion_");
    }


    /**
     * Method for providing the ratio between the size to the weight of the object (1 : 3.5 KG)
     *
     * @return The ratio between the size to the weight (1 : 3.5 KG)
     */
    protected double sizeScale() { return 3.5; }
}