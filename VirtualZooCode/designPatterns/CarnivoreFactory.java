package designPatterns;

import animals.Animal;
import animals.Lion;


public class CarnivoreFactory implements IZooFactory {

    public CarnivoreFactory() {}


    @Override
    public Animal createAnimal(String type, String name, double size, int horizontalSpeed, int verticalSpeed, String color) {
        if(type.equals("Lion"))
            return new Lion(name, size, horizontalSpeed, verticalSpeed, color);

        else
            return null;
    }
}