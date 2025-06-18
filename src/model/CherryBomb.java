package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CherryBomb extends BombPlants{

    private final static int price = 150;

    private final static double rechargeTime = 8;

    private static boolean available = true;

    public CherryBomb(int i, int j) {
        super(i, j, price, rechargeTime);
        ImageView imageView = new ImageView(getClass().getResource("/view/images/cherry bomb.png").toString());
        imageView.setFitWidth(135);
        imageView.setFitHeight(140);
        setImage(imageView);
    }

    public CherryBomb(){

    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new CherryBomb(row, column);
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
