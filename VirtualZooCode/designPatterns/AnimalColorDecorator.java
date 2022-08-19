package designPatterns;

import animals.Animal;


/**
 * a Class to implement the DesignPattern Decorator, by implementing the IAnimalColor interface
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see designPatterns.IAnimalColor
 */
public class AnimalColorDecorator implements IAnimalColor {

    final private Animal animal;

    /**
     * The constructor of the AnimalColorDecorator object, Sets the fields of the object
     *
     * @see animals.Animal
     */
    public AnimalColorDecorator(Animal animal) { this.animal = animal; }


    /**
     * Method in order to update the animal color and image
     *
     * @param color is a String representing the new color of the animal
     */
    @Override
    public void PaintAnimal(String color) { this.animal.updateColor(color); }
}