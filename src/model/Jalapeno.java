package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Jalapeno extends BombPlants{

    private final static int price = 125;
    private final static double rechargeTime = 30;
    private static boolean available = true;

    public Jalapeno(int i, int j, int price) {
        super(i, j, price, rechargeTime);
    }

    @Override
    public void BOMB() {

    }

}
