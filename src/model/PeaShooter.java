package model;

public class PeaShooter extends PeaPlants{

    private final static int HP = 4;
    private final static int price = 100;
    private final static int bullets = 1;
    private final static double rechargeTime = 7.5;
    private static boolean available = true;

    public PeaShooter(int i, int j) {
        super(HP, i, j, price, bullets, rechargeTime);
    }
}
