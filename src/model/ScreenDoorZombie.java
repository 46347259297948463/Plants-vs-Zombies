package model;

import controller.DayLevel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ScreenDoorZombie extends Zombie{

    public ScreenDoorZombie(double x, double y, int rowBTN) {
        super(x, y, rowBTN);
        setHP(10);
    }

    @Override
    protected void setImageOnAnc(){
        Image image = new Image(getClass().getResource("/view/images/doorZombie.png").toString());
        ImageView imageV = new ImageView(image);
        setImage(imageV);
        imageV.setLayoutX(column);
        imageV.setLayoutY(row);
        imageV.setFitHeight(225);
        imageV.setFitWidth(145);
        DayLevel.getInstance().getDayAnc().getChildren().add(imageV);
    }
}
