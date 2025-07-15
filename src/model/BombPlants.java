package model;

public abstract class BombPlants extends Plants{

    private final static int HP = 4;

    public BombPlants(int i, int j, int price, double rechargeTime) {
        super(HP, i, j, price, rechargeTime);
    }

    public BombPlants(){

    }

    protected abstract void BOMB();

}
