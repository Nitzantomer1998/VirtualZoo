package diet;

public interface IDiet {

    boolean canEat(EFoodType food);
    double eat(Animal animal, IEdible food);
}
