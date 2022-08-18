package designPatterns;

import animals.Animal;


/**
 * an Interface to implement the Clone Design Pattern
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see animals.Animal
 */
public interface ICloneable {

    Animal Clone();
}
