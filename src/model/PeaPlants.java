package model;

import javafx.animation.Timeline;

public abstract class PeaPlants extends Plants{

    protected int bullets;

    protected Timeline shootTimer;

    public PeaPlants(int hp, int i, int j, int price, int bullets, double rechargeTime) {
        super(hp, i, j, price, rechargeTime);
        this.bullets = bullets;
    }

    public PeaPlants(){

    }

    protected abstract void shoot(Zombie zombie);

    @Override
    public void end(){
        if (shootTimer != null){
            shootTimer.stop();
        }
    }

}
