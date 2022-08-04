package designPatterns;


/**
 * a Class that defines the AbstarctFactory Design pattern,
 * General class for getting the desire appropriate DietFactory
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 */
public class DietFactory {

    /**
     * The constructor of the DietFactory object, Sets the fields of the object
     */
    public DietFactory() {}


    /**
     * Method in order to get the appropriate diet factory
     *
     * @param diet is a Diet representing the diet of the animal
     * @see designPatterns.IZooFactory
     * @return The appropriate factory
     */
    public IZooFactory getFactory(String diet) {

        if (diet.equalsIgnoreCase("Carnivore"))
            return new CarnivoreFactory();

        else if (diet.equalsIgnoreCase("Herbivore"))
            return new HerbivoreFactory();

        else
            return new OmnivoreFactory();
    }
}
