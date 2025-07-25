package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class Sun {

    private int row;

    private int column;

    private Group group = new Group();

    private ImageView imageView = new ImageView(getClass().getResource("/view/images/sun.png").toString());

    public Timeline timeline;

    private Button button = new Button("");

    private boolean isSunflower = false;

    public Sun(int row , int column, boolean bool){
        this.row = row;
        this.column = column;
        isSunflower = bool;
        fixSun();
        timeline = new Timeline(new KeyFrame(Duration.millis(5000), event -> endSun()));
        timeline.setCycleCount(3);
        timeline.play();
    }

    private void fixSun(){
        imageView.setFitHeight(85);
        imageView.setFitWidth(85);
        imageView.setOpacity(1);

        getButton().setOpacity(0);
        getButton().setPrefHeight(60);
        getButton().setPrefWidth(60);

        if (!isSunflower){
            group.setLayoutX(row);
            group.setLayoutY(column);
        } else {
            imageView.setLayoutX(70);
            imageView.setLayoutY(90);
            getButton().setLayoutX(85);
            getButton().setLayoutY(100);
        }


            getGroup().getChildren().addAll(imageView, getButton());

    }

    public void endSun() {
        if (isSunflower) {
            if (DayLevel.getInstance() != null &&
                    DayLevel.getInstance().getCells() != null &&
                    row >= 0 && column >= 0 &&
                    DayLevel.getInstance().getCells()[row][column] != null &&
                    DayLevel.getInstance().getCells()[row][column].getGroup() != null) {
                DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(getGroup());
            }
        } else {
            if (DayLevel.getInstance() != null &&
                    DayLevel.getInstance().getDayAnc() != null) {
                DayLevel.getInstance().getDayAnc().getChildren().remove(getGroup());
            }
        }
    }

    public void stop(){
        timeline.stop();
    }

    public void play(){
        timeline.play();
    }

    public Group getGroup() {
        return group;
    }

    public Button getButton() {
        return button;
    }

}
