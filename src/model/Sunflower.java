package model;

public class Sunflower extends Plants{

    private final static int HP = 4;
    private final static int price = 50;
    private final static double rechargeTime = 3;
    private static boolean available = true;

    public Sunflower(int hp, int i, int j, int price) {
        super(hp, i, j, price, rechargeTime);
    }

}
