package designPatterns;


public class DietFactory {

    public DietFactory() {}


    public IZooFactory getFactory(String diet) {

        if (diet.equalsIgnoreCase("Carnivore"))
            return new CarnivoreFactory();

        else if (diet.equalsIgnoreCase("Herbivore"))
            return new HerbivoreFactory();

        else
            return new OmnivoreFactory();
    }
}
