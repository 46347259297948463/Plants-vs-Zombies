package model;

import controller.DayLevel;
import controller.FogLevel;
import controller.NightLevel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet {

    protected String color = "green";

    protected double row;

    protected double column;

    protected boolean isSnow = false;

    protected ImageView imageView;

    public static Object obj;

    protected int n;

    protected Object bulletObject;

    protected Cell[][] cells;

    public Bullet(double row, double column, int n){
        if (obj instanceof DayLevel) {
            cells = DayLevel.getInstance().getCells();
        } else if (obj instanceof NightLevel) {
            cells = NightLevel.getInstance().getCells();
        } else if (obj instanceof FogLevel) {
            cells = FogLevel.getInstance().getCells();
        }
        this.row = row * 191.5 + 153;
        this.column = column * 153.5 + 590;
        this.n = n;
        setting();
    }

    protected void setting(){
        Image image = new Image(getClass().getResource("/view/images/green bullet.png").toString());
        ImageView imageV = new ImageView(image);
        imageV.setFitWidth(50);
        imageV.setFitHeight(50);
        imageV.setLayoutX(this.column);
        imageV.setLayoutY(this.row);
        this.imageView = imageV;
    }

    public void endBullet(){
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().getDayAnc().getChildren().remove(imageView);
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().getNightAnc().getChildren().remove(imageView);
        } else if (obj instanceof FogLevel) {
            FogLevel.getInstance().getFogAnc().getChildren().remove(imageView);
        }
    }

    public void move(){
//        int rowBTN = (int) ((row - 153) / 191.5);
//        int columnBTN = (int) ((column - 590) / 153.5);
//        if (columnBTN > 4 && columnBTN < 9 && cells[rowBTN][columnBTN].getCloudImage() != null) {
//            hide();
//        } else {
//            apear();
//        }
        column += 35;
        imageView.setLayoutX(column);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void hide() {
        if (imageView != null) {
            imageView.setOpacity(0);
        }
    }

    public void apear() {
        if (imageView != null) {
            imageView.setOpacity(1);
        }
    }

}
