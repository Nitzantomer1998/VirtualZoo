package designPatterns;


/**
 * an Interface to implement the Decorator Design Pattern
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see designPatterns.AnimalColorDecorator,graphics.ChangeColorDialog,animals.Animal
 */
public interface IAnimalColor {

    void PaintAnimal(String color);
}