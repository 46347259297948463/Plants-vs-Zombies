package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class Sun {

    private int row , col;

    private Group group = new Group();

    private ImageView imageView = new ImageView(getClass().getResource("/view/images/sun.png").toString());

    private Timeline timeline;

    private Button button = new Button("");

    public Sun(int row , int col){
        this.row = row;
        this.col = col;
        fixSun();
        timeline = new Timeline(new KeyFrame(Duration.millis(5000), event -> endSun()));
        timeline.setCycleCount(3);
        timeline.play();
    }

    private void fixSun(){
        imageView.setFitHeight(85);
        imageView.setFitWidth(85);
        imageView.setOpacity(1);
        imageView.setLayoutX(70);
        imageView.setLayoutY(90);

        getButton().setOpacity(0);
        getButton().setLayoutX(85);
        getButton().setLayoutY(100);
        getButton().setPrefHeight(60);
        getButton().setPrefWidth(60);

        getGroup().getChildren().addAll(imageView, getButton());

    }

    private void endSun(){
        DayLevel.getInstance().getCells()[row][col].getGroup().getChildren().remove(getGroup());
    }

    public Group getGroup() {
        return group;
    }

    public Button getButton() {
        return button;
    }
}
