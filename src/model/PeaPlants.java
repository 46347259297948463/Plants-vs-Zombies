package model;

public abstract class PeaPlants extends Plants{

    protected int bullets;

    public PeaPlants(int hp, int i, int j, int price, int bullets, double rechargeTime) {
        super(hp, i, j, price, rechargeTime);
        this.bullets = bullets;
    }

//    @Override
//    public void act() {
//
////        shoot();
//    }

    public void shoot(Zombie zombie) {

    }

}
