package model;

import controller.DayLevel;
import controller.FogLevel;
import controller.NightLevel;
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

    private int num = 0;

    private int n;

    public Sun(int row , int column, int num, int n){
        this.row = row;
        this.column = column;
        this.num = num;
        this.n = n;
        fixSun();
        timeline = new Timeline(new KeyFrame(Duration.millis(5000), event -> endSun()));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void fixSun() {
        imageView.setFitHeight(85);
        imageView.setFitWidth(85);
        imageView.setOpacity(1);

        getButton().setOpacity(0);
        getButton().setPrefHeight(60);
        getButton().setPrefWidth(60);

        if (num == 1){
            imageView.setLayoutX(70);
            imageView.setLayoutY(90);
            getButton().setLayoutX(85);
            getButton().setLayoutY(100);

        } else if (num == 2){
            group.setLayoutX(row);
            group.setLayoutY(column);
        } else if (num == 3) {
            if (n == 1) {
                imageView.setLayoutX(55);
                imageView.setLayoutY(75);
                getButton().setLayoutX(70);
                getButton().setLayoutY(80);
            } else if (n == 2) {
                imageView.setLayoutX(65);
                imageView.setLayoutY(85);
                getButton().setLayoutX(80);
                getButton().setLayoutY(90);
            } else if (n > 2) {
                imageView.setLayoutX(70);
                imageView.setLayoutY(90);
                getButton().setLayoutX(85);
                getButton().setLayoutY(95);
            }
        }

        getGroup().getChildren().addAll(imageView, getButton());

    }

    public void endSun() {
        if (Plants.obj instanceof DayLevel) {
            if (num == 1 || num == 3) {
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
        } else if (Plants.obj instanceof NightLevel) {
            if (num == 1 || num == 3) {
                    if (NightLevel.getInstance() != null &&
                            NightLevel.getInstance().getCells() != null &&
                            row >= 0 && column >= 0 &&
                            NightLevel.getInstance().getCells()[row][column] != null &&
                            NightLevel.getInstance().getCells()[row][column].getGroup() != null) {
                        NightLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(this.getGroup());
                    }
                } else {
                if (NightLevel.getInstance() != null && 
                        NightLevel.getInstance().getNightAnc() != null) {
                    NightLevel.getInstance().getNightAnc().getChildren().remove(getGroup());
                }
            }
        } else if (Plants.obj instanceof FogLevel) {
            if (num == 1 || num == 3) {
                if (FogLevel.getInstance() != null &&
                        FogLevel.getInstance().getCells() != null &&
                        row >= 0 && column >= 0 &&
                        FogLevel.getInstance().getCells()[row][column] != null &&
                        FogLevel.getInstance().getCells()[row][column].getGroup() != null) {
                    FogLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(this.getGroup());
                }
            } else {
                if (FogLevel.getInstance() != null &&
                        FogLevel.getInstance().getFogAnc() != null) {
                    FogLevel.getInstance().getFogAnc().getChildren().remove(getGroup());
                }
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