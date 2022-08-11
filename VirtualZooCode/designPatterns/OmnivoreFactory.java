package designPatterns;

import animals.Animal;
import animals.Bear;


public class OmnivoreFactory implements IZooFactory {

    public OmnivoreFactory() {}

    
    @Override
    public Animal createAnimal(String type, String name, double size, int horizontalSpeed, int verticalSpeed, String color) {
        if(type.equals("Bear"))
            return new Bear(name, size, horizontalSpeed, verticalSpeed, color);

        else
            return null;
    }
}