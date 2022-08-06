package designPatterns;

import animals.Animal;


public interface IZooFactory {

    Animal createAnimal(String type, String name, double size, int horizontalSpeed, int verticalSpeed, String color);
}