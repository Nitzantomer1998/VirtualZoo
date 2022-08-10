package designPatterns;

import animals.Animal;
import animals.Elephant;
import animals.Giraffe;
import animals.Turtle;


public class HerbivoreFactory implements IZooFactory {

    public HerbivoreFactory() {}


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