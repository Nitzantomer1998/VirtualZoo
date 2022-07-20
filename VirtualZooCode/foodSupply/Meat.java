package foodSupply;

import food.EFoodType;


/**
 * A class representing a Meat, and inherit from AvailableFood
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see foodSupply.AvailableFood
 */
public class Meat extends AvailableFood {

    public static volatile Meat instance = null;


    /**
     * The constructor of the Meat object, Sets the fields of the object
     */
    private Meat() {
        super();
        this.loadImages("meat.png");
    }


    /**
     * Static method for ensuring there's maximum of one instance type of this class,
     * If there's no instance it will create one, else it will return the existing instance,
     * Note: Also updating the static class field of AvailableFood to be this instance
     * Note: Singleton DP + little tweaks
     *
     * @return Instance of this class
     */
    public static Meat getInstance() {
        if (Meat.instance == null)
            synchronized (Meat.class) {
                if (Meat.instance == null)
                    Meat.instance = new Meat();
            }

        if (!(AvailableFood.instance instanceof Meat))
            AvailableFood.instance = Meat.instance;

        return (Meat)AvailableFood.instance;
    }


    /**
     * Getter method for the food type of the object
     *
     * @see food.IEdible
     * @return The object's food type
     */
    @Override
    public EFoodType getFoodType() { return EFoodType.MEAT; }
}
