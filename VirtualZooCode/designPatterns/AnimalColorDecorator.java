package designPatterns;

import animals.Animal;


public class AnimalColorDecorator implements IAnimalColor {

    final private Animal animal;


    public AnimalColorDecorator(Animal animal) { this.animal = animal; }


    @Override
    public void PaintAnimal(String color) { this.animal.updateColor(color); }
}