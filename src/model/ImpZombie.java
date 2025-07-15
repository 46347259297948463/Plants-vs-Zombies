package model;

import controller.DayLevel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImpZombie extends Zombie{

    public ImpZombie(double x, double y, int rowBTN) {
        super(x, y, rowBTN);
        setHP(3);
        setSpeed(4);
    }

    @Override
    protected void setImageOnAnc(){
        Image image = new Image(getClass().getResource("/view/images/impZombie.png").toString());
        ImageView imageV = new ImageView(image);
        setImage(imageV);
        imageV.setLayoutX(column);
        imageV.setLayoutY(row);
        imageV.setFitHeight(140);
        imageV.setFitWidth(125);
        DayLevel.getInstance().getDayAnc().getChildren().add(imageV);
    }

}
