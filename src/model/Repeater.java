package model;

public class Repeater extends PeaPlants{

    private final static int HP = 4;
    private final static int price = 200;
    private final static int bullets = 2;
    private final static double rechargeTime = 15;
    private static boolean available = true;

    public Repeater(int i, int j) {
        super(HP, i, j, price, bullets, rechargeTime);
    }
}
