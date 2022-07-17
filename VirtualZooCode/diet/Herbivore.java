package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;


public class Herbivore implements IDiet {

    public Herbivore() {}


    @Override
    public boolean canEat(EFoodType food) { return food == EFoodType.VEGETABLE; }


    @Override
    public double eat(Animal animal, IEdible food) {
        if (canEat(food.getFoodType()))
            return animal.getWeight() * 0.07;

        return 0.0;
    }
}