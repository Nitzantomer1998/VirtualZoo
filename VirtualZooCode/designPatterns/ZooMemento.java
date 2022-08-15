package designPatterns;

import animals.Animal;
import foodSupply.AvailableFood;

import java.util.Vector;
import java.awt.*;


/**
 * a Class that defines the Memento Design pattern, Class that every instance of it is a backup
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 */
public class ZooMemento {

    private final Vector<Animal> animalVectorBackUp;
    private final AvailableFood availableFoodBackUp;
    private final Color backgroundBackUp;


    /**
     * The constructor of the ZooMemento object, Sets the fields of the object
     *
     * @param animalVector is a Vector representing the current existed animals in the zoo
     * @param availableFood is an AvailableFood representing the current available food in the zoo
     * @param background is a Color representing the background current color of the zoo panel
     */
    public ZooMemento(Vector<Animal> animalVector, AvailableFood availableFood, Color background) {
        this.animalVectorBackUp = new Vector<>();
        for (Animal animal : animalVector)
            this.animalVectorBackUp.add(animal.Clone());

        this.availableFoodBackUp = availableFood;
        this.backgroundBackUp = background;
    }


    /**
     * Getter methods for the fields of the object / class
     *
     * @return The wanted field
     */
    public Vector<Animal> getAnimalVectorBackUp() { return this.animalVectorBackUp; }
    public AvailableFood getAvailableFoodBackUp() { return this.availableFoodBackUp; }
    public Color getBackgroundBackUp() { return this.backgroundBackUp; }
}