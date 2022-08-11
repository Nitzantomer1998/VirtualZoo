package designPatterns;

import animals.Animal;
import animals.Bear;


/**
 * a Class that defines the Factory Design pattern,
 * General class of diet Omnivore in order to create instances of it by implement the interface IZooFactory
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see designPatterns.IZooFactory
 */
public class OmnivoreFactory implements IZooFactory {

    /**
     * The constructor of the OmnivoreFactory object, Sets the fields of the object
     *
     * @see designPatterns.DietFactory
     */
    public OmnivoreFactory() {}


    /**
     * Implementation of the IZooFactory methods
     *
     * @param type is a String representing the type of the animal
     * @param name is a String representing the name of the animal
     * @param size is an Integer representing the size of the animal
     * @param horizontalSpeed is an Integer representing the Horizontal speed of the animal
     * @param verticalSpeed is an Integer representing the Vertical speed of the animal
     * @param color is a String representing the color of the animal
     */
    @Override
    public Animal createAnimal(String type, String name, double size, int horizontalSpeed, int verticalSpeed, String color) {
        if(type.equals("Bear"))
            return new Bear(name, size, horizontalSpeed, verticalSpeed, color);

        else
            return null;
    }
}