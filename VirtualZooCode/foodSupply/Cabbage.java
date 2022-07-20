package foodSupply;

import food.EFoodType;


public class Cabbage extends AvailableFood {

    public static volatile Cabbage instance = null;


    private Cabbage() {
        super();
        this.loadImages("cabbage.png");
    }


    public static Cabbage getInstance() {
        if (Cabbage.instance == null)
            synchronized (Cabbage.class) {
                if (Cabbage.instance == null)
                    Cabbage.instance = new Cabbage();
            }

        if (!(AvailableFood.instance instanceof Cabbage))
            AvailableFood.instance = Cabbage.instance;

        return (Cabbage)AvailableFood.instance;
    }


    @Override
    public EFoodType getFoodType() { return EFoodType.VEGETABLE; }
}
