package designPatterns;

import animals.Animal;


/**
 * an Interface to implement the AbstarctFactory Design Pattern
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see designPatterns.OmnivoreFactory, designPatterns.HerbivoreFactory, designPatterns.CarnivoreFactory
 */
public interface IZooFactory {

    Animal createAnimal(String type, String name, double size, int horizontalSpeed, int verticalSpeed, String color);
}