package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;


/**
 * A class representing the Omnivores, and implement IDiet
 * Note: Omnivore class define the diet (Meat and Vegetable consumers)
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see diet.IDiet
 */
public class Omnivore implements IDiet {

    private final IDiet carnivore;
    private final IDiet herbivore;


    /**
     * The constructor of the Omnivore object, Sets the attributes of the object
     * Note: The object itself used as field
     *
     * @see animals.Animal
     */
    public Omnivore() {
        this.carnivore = new Carnivore();
        this.herbivore = new Herbivore();
    }


    /**
     * Getting a food, and check if Omnivore can eat it
     *
     * @param food a String representing the food type (food type define in EFoodType)
     * @see food.EFoodType
     * @return True if the food is either MEAT or VEGETABLE, else False
     */
    @Override
    public boolean canEat(EFoodType food) { return food != EFoodType.NOTFOOD; }


    /**
     * Getting Animal object and a food, check if the object may eat it using the canEat method,
     * If yes, calculate the gained weight, else the gained weight is None
     *
     * @param animal is an Animal representing animal object
     * @param food is a String representing the food type (food type is from EFoodType)
     * @return a Double representing the gained weight calculation
     */
    @Override
    public double eat(Animal animal, IEdible food) { return this.carnivore.eat(animal, food) + this.herbivore.eat(animal, food); }
}