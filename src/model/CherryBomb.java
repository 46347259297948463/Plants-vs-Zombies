package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CherryBomb extends BombPlants{

    private final static double rechargeTime = 8;

    private Timeline timer;

    private Timeline cherryBombTimer;

    private Cell[][] cells = DayLevel.getInstance().getCells();

    public CherryBomb(int i, int j, Group group) {
        super(i, j, 150, rechargeTime);
        this.group = group;
        DayLevel.getInstance().setAvailablePicked(false, DayLevel.getInstance().getAvailableNum());
//        System.out.println("availableNum = " + availableNum);;
        ImageView imageView = new ImageView(getClass().getResource("/view/images/cherry bomb.png").toString());
        imageView.setFitWidth(135);
        imageView.setFitHeight(140);
        setImage(imageView);
        cherryBombTimer = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> increaseSize()),
                new KeyFrame(Duration.seconds(2) , event -> BOMB())
        );
        cherryBombTimer.setCycleCount(1);
        cherryBombTimer.play();
        group.setOpacity(0.7);
        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public CherryBomb(){
        price = 150;
    }

    private void increaseSize(){
        Timeline timer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            image.setFitHeight(image.getFitHeight() + 1 );
            image.setFitWidth(image.getFitWidth() + 1);
        }));
        timer.setCycleCount(20);
        timer.play();
    }

    @Override
    public void end() {
        if (cherryBombTimer != null){
            cherryBombTimer.stop();
        }
        group.setOpacity(1);
    }

    @Override
    public Plants clonePlant(int row, int column, Group group) {
        return new CherryBomb(row, column, group);
    }

    @Override
    public void BOMB() {

        if (column > 0 && row > 0) cells[row-1][column-1].removeAllZombies();
        if (column > 0) cells[row][column-1].removeAllZombies();
        if (column > 0 && row < 5) cells[row + 1][column - 1].removeAllZombies();
        if (row > 0) cells[row - 1][column].removeAllZombies();
        cells[row][column].removeAllZombies();
        if (row < 5) cells[row + 1][column].removeAllZombies();
        if (column < 8 && row > 0) cells[row -1][column + 1].removeAllZombies();
        if (column < 8) cells[row][column + 1].removeAllZombies();
        if (column < 8 && row < 5) cells[row + 1][column + 1].removeAllZombies();

        DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(this.image);
        cells[row][column].removePlant();
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, DayLevel.getInstance().getAvailableNum());
        timer.stop();
        group.setOpacity(1);
    }

}
