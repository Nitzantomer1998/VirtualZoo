package designPatterns;

import animals.Animal;
import foodSupply.AvailableFood;

import java.util.Vector;
import java.awt.*;


public class ZooMemento {

    private final Vector<Animal> animalVectorBackUp;
    private final AvailableFood availableFoodBackUp;
    private final Color backgroundBackUp;


    public ZooMemento(Vector<Animal> animalVector, AvailableFood availableFood, Color background) {
        this.animalVectorBackUp = new Vector<>();
        for (Animal animal : animalVector)
            this.animalVectorBackUp.add(animal.Clone());

        this.availableFoodBackUp = availableFood;
        this.backgroundBackUp = background;
    }


    public Vector<Animal> getAnimalVectorBackUp() { return this.animalVectorBackUp; }
    public AvailableFood getAvailableFoodBackUp() { return this.availableFoodBackUp; }
    public Color getBackgroundBackUp() { return this.backgroundBackUp; }
}