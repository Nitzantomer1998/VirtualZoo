package foodSupply;

import food.EFoodType;


/**
 * A class representing a Lettuce, and inherit from AvailableFood
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see foodSupply.AvailableFood
 */
public class Lettuce extends AvailableFood {

    public static volatile Lettuce instance = null;


    /**
     * The constructor of the lettuce object, Sets the fields of the object
     */
    private Lettuce() {
        super();
        this.loadImages("lettuce.png");
    }


    /**
     * Static method for ensuring there's maximum of one instance type of this class,
     * If there's no instance it will create one, else it will return the existing instance,
     * Note: Also updating the static class field of AvailableFood to be this instance
     * Note: Singleton DP + little tweaks
     *
     * @return Instance of this class
     */
    public static Lettuce getInstance() {
        if (Lettuce.instance == null)
            synchronized (Lettuce.class) {
                if (Lettuce.instance == null)
                    Lettuce.instance = new Lettuce();
            }

        if (!(AvailableFood.instance instanceof Lettuce))
            AvailableFood.instance = Lettuce.instance;

        return (Lettuce)AvailableFood.instance;
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
