package model;

public class SnowPea extends PeaPlants{

    private final static int HP = 4;
    private final static int price = 175;
    private final static int bullets = 1;
    private final static double rechargeTime = 15;
    private static boolean available = true;

    public SnowPea(int i, int j) {
        super(HP, i, j, price, bullets, rechargeTime);
    }
}
