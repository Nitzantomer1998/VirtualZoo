package designPatterns;

import animals.Animal;
import animals.Elephant;
import animals.Giraffe;
import animals.Turtle;


/**
 * a Class that defines the Factory Design pattern,
 * General class of diet herbivore in order to create instances of it by implement the interface IZooFactory
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see designPatterns.IZooFactory
 */
public class HerbivoreFactory implements IZooFactory {

    /**
     * The constructor of the HerbivoreFactory object, Sets the fields of the object
     *
     * @see designPatterns.DietFactory
     */
    public HerbivoreFactory() {}


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
        return switch (type) {
            case "Elephant" -> new Elephant(name, size, horizontalSpeed, verticalSpeed, color);
            case "Giraffe" -> new Giraffe(name, size, horizontalSpeed, verticalSpeed, color);
            case "Turtle" -> new Turtle(name, size, horizontalSpeed, verticalSpeed, color);
            default -> null;
        };
    }
}