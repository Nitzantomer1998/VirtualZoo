package foodSupply;

import food.EFoodType;


public class Meat extends AvailableFood {

    public static volatile Meat instance = null;


    private Meat() {
        super();
        this.loadImages("meat.png");
    }


    public static Meat getInstance() {
        if (Meat.instance == null)
            synchronized (Meat.class) {
                if (Meat.instance == null)
                    Meat.instance = new Meat();
            }

        if (!(AvailableFood.instance instanceof Meat))
            AvailableFood.instance = Meat.instance;

        return (Meat)AvailableFood.instance;
    }


    @Override
    public EFoodType getFoodType() { return EFoodType.MEAT; }
}
