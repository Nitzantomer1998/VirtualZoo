package foodSupply;

import food.EFoodType;


/**
 * A class representing a Cabbage, and inherit from AvailableFood
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see foodSupply.AvailableFood
 */
public class Cabbage extends AvailableFood {

    public static volatile Cabbage instance = null;


    /**
     * The constructor of the cabbage object, Sets the fields of the object
     */
    private Cabbage() {
        super();
        this.loadImages("cabbage.png");
    }


    /**
     * Static method for ensuring there's maximum of one instance type of this class,
     * If there's no instance it will create one, else it will return the existing instance,
     * Note: Also updating the static class field of AvailableFood to be this instance
     * Note: Singleton DP + little tweaks
     *
     * @return Instance of this class
     */
    public static Cabbage getInstance() {
        if (Cabbage.instance == null)
            synchronized (Cabbage.class) {
                if (Cabbage.instance == null)
                    Cabbage.instance = new Cabbage();
            }

        if (!(AvailableFood.instance instanceof Cabbage))
            AvailableFood.instance = Cabbage.instance;

        return (Cabbage)AvailableFood.instance;
    }


    /**
     * Getter method for the food type of the object
     *
     * @see food.IEdible
     * @return The object's food type
     */
    @Override
    public EFoodType getFoodType() { return EFoodType.VEGETABLE; }
}
