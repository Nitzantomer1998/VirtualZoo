package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;


/**
 * Interface to describe the functionality of eating,
 * Note : This interface used as a field to describe the functionality of eating
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 * @see animals.Animal
 */
public interface IDiet {

    boolean canEat(EFoodType food);
    double eat(Animal animal, IEdible food);
}
