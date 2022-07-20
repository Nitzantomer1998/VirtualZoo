package foodSupply;

import food.EFoodType;


public class Lettuce extends AvailableFood {

    public static volatile Lettuce instance = null;


    private Lettuce() {
        super();
        this.loadImages("lettuce.png");
    }


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


    @Override
    public EFoodType getFoodType() { return EFoodType.VEGETABLE; }
}
