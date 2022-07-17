package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;



public class Omnivore implements IDiet {

    private final IDiet carnivore;
    private final IDiet herbivore;

    
    public Omnivore() {
        this.carnivore = new Carnivore();
        this.herbivore = new Herbivore();
    }


    @Override
    public boolean canEat(EFoodType food) { return food != EFoodType.NOTFOOD; }


    @Override
    public double eat(Animal animal, IEdible food) { return this.carnivore.eat(animal, food) + this.herbivore.eat(animal, food); }
}