package diet;


import animals.Animal;
import food.EFoodType;
import food.IEdible;

public class Carnivore implements IDiet {

    /**
     * The constructor of the Carnivore object, Sets the attributes of the object
     * Note : The object itself has no field, but he used as one
     *
     * @see animals.Animal
     */
    public Carnivore() {}


    @Override
    public boolean canEat(EFoodType food) { return food == EFoodType.MEAT; }


    @Override
    public double eat(Animal animal, IEdible food) {
        if (canEat(food.getFoodType()))
            return animal.getWeight() * 0.1;

        return 0.0;
    }
}