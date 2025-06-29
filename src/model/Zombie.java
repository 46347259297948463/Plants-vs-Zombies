package model;

import controller.DayLevel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import controller.DayLevel;

public class Zombie {
    protected double row;

    protected double column;

    private int HP = 5;

    private int speed = 2;

    private ImageView image;

    private Timeline moveTimeline;

    protected boolean isRemoved = false;

    private int i = 0;

    private double limitColumn;

    private Timeline eatTimeline;

    public int rowBTN;

    public int columnBTN = 8;

    private double currentWidthBTN;

    public Zombie(double x, double y, int rowBTN){
        this.column = x;
        this.row = y;
        this.rowBTN = rowBTN;
        setImageOnAnc();
        startMove();
    }

    protected void setImageOnAnc(){
        Image image = new Image(getClass().getResource("/view/images/normalZombies.png").toString());
        ImageView imageV = new ImageView(image);
        setImage(imageV);
        imageV.setLayoutX(column);
        imageV.setLayoutY(row);
        imageV.setFitHeight(220);
        imageV.setFitWidth(165);
        DayLevel.getInstance().getDayAnc().getChildren().add(imageV);
    }

    private void startMove(){
        if(moveTimeline == null){
            currentWidthBTN = DayLevel.getInstance().getCells()[rowBTN][columnBTN].getButton().getWidth();
            limitColumn = column - currentWidthBTN;
            moveTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> move()));
            moveTimeline.setCycleCount(Timeline.INDEFINITE);
            moveTimeline.play();
        }
    }

    private void stopMove(){
        if(moveTimeline != null){
            moveTimeline.stop();
        }
    }

    public void move(){
       if (columnBTN > -1){
           if (!eat()){
               column -= speed/2;
               image.setLayoutX(column);
           } else {
               moveTimeline.stop();
               eatTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                   Plants plant = DayLevel.getInstance().getCells()[rowBTN][columnBTN].getPlant();
                   if(plant != null){
                       plant.takeDamage(1);
                       if (plant.isDead()){
                           DayLevel.getInstance().getCells()[rowBTN][columnBTN].getGroup().getChildren().remove(plant.getImage());
                           DayLevel.getInstance().getCells()[rowBTN][columnBTN].removePlant();
                           plant.end();
                           eatTimeline.stop();
                           moveTimeline = null;
                           startMove();
                       }
                   } else {
                       eatTimeline.stop();
                       moveTimeline = null;
                       startMove();
                   }
               }));
               eatTimeline.setCycleCount(Timeline.INDEFINITE);
               eatTimeline.play();
           }
       }
        update();
    }

    private void update(){
        if (limitColumn > column){

            columnBTN--;
            currentWidthBTN = DayLevel.getInstance().getCells()[rowBTN][columnBTN].getButton().getWidth();
            limitColumn = column - currentWidthBTN;
        }
        DayLevel.getInstance().columnLBL.setText(columnBTN + "");
        DayLevel.getInstance().rowLBL.setText(rowBTN + "");
        if (columnBTN < 8){
            DayLevel.getInstance().getCells()[rowBTN][columnBTN + 1].removeZombie(this);
        } else if (columnBTN == 9){
            columnBTN -= 1;
        }
        if(columnBTN < 0){
            DayLevel.getInstance().exitGame();
        } else {
            DayLevel.getInstance().getCells()[rowBTN][columnBTN].setZombies(this);
        }
        if(isDead()){
            stopMove();
            isRemoved = true;
            return;
        }
        image.setLayoutX(column);
    }

    private boolean eat(){
        return DayLevel.getInstance().getCells()[rowBTN][columnBTN].getPlant() != null;
    }

    public void takeDamage(int damage){
        HP -= damage;
    }

    public boolean isDead(){
        return HP <= 0;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public double getRow() {
        return row;
    }

    public double getColumn() {
        return column;
    }

    public int getHP() {
        return HP;
    }

    public int getSpeed() {
        return speed;
    }

    public void dead(){
        DayLevel.getInstance().getCells()[rowBTN][columnBTN].removeZombie(this);
        DayLevel.getInstance().getDayAnc().getChildren().remove(this.image);
    }

}
