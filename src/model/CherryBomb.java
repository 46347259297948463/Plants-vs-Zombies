package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class CherryBomb extends BombPlants{

    private final static int price = 150;
    private final static double rechargeTime = 35;
    private static boolean available = true;
    public CherryBomb(int i, int j, int price) {
        super(i, j, price, rechargeTime);
    }

    @Override
    public void BOMB() {

    }

//    private void isAvailable(){
//        if (availabe){
//            Timeline recharge = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), e -> {
//                available = false;
//            }));
//            recharge.play();
//        }
//    }

}
