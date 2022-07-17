package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;


/**
 * A class representing the Carnivores, and implement IDiet
 * Note: Carnivore class define the diet type (Meat consumers)
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see diet.IDiet
 */
public class Carnivore implements IDiet {

    /**
     * The constructor of the Carnivore object, Sets the attributes of the object
     * Note: The object itself has no field, but he used as one
     *
     * @see animals.Animal
     */
    public Carnivore() {}


    /**
     * Getting a food, and check if Carnivore can eat it
     *
     * @param food is a String representing the food type (food type define in EFoodType)
     * @see food.EFoodType
     * @return True if the food is MEAT, else False
     */
    @Override
    public boolean canEat(EFoodType food) { return food == EFoodType.MEAT; }


    /**
     * Getting Animal object and a food, check if the object may eat it using the canEat method,
     * If yes, calculate the gained weight, else the gained weight is None
     *
     * @param animal is an Animal representing animal object
     * @param food is a String representing the food type (food type is from EFoodType)
     * @return a Double representing the gained weight calculation
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        if (canEat(food.getFoodType()))
            return animal.getWeight() * 0.1;

        return 0.0;
    }
}