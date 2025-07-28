package model;

import controller.DayLevel;
import controller.NightLevel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConeheadZombie extends Zombie{

    public ConeheadZombie(double x, double y, int rowBTN) {
        super(x, y, rowBTN);
        setHP(7);
    }

    @Override
    protected void setImageOnAnc(){
        Image image = new Image(getClass().getResource("/view/images/coneheadZombie.png").toString());
        ImageView imageV = new ImageView(image);
        setImage(imageV);
        imageV.setLayoutX(column);
        imageV.setLayoutY(row);
        imageV.setFitHeight(240);
        imageV.setFitWidth(190);
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().getDayAnc().getChildren().add(imageV);
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().getNightAnc().getChildren().add(imageV);
        }
    }

}
