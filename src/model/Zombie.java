package model;

import controller.DayLevel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Zombie {
    protected double row;

    protected double column;

    private int HP = 5;

    private int speed = 2;

    private ImageView image;

    private boolean attack = false;

    private Timeline moveTimeline;

    protected boolean isRemoved = false;

    private int i = 0;

    public Zombie(double x, double y){
        this.column = x;
        this.row = y;
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

    public void startMove(){
        if(moveTimeline == null){
            moveTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> move()));
            moveTimeline.setCycleCount(Timeline.INDEFINITE);
            moveTimeline.play();
        }
    }

    public void stopMove(){
        if(moveTimeline != null){
            moveTimeline.stop();
        }
    }

    public void move(){
        if (!attack){
            column -= speed/2;
            image.setLayoutX(column);
        }
        update();
    }

    public void update(){
        int updateRow, updateColumn;
        updateRow = (int) ((row - 130) / 185);
        updateColumn = (int) ((column - 517) / 140);
        if (updateColumn < 8){
            DayLevel.getInstance().getCells()[updateRow][updateColumn + 1].removeZombie(this);
        } else if (updateColumn == 9){
            updateColumn -= 1;
        }
        if(updateColumn < 0){
            //بازیکن باخته و زامبی به انتهای زمین رسیده است
            DayLevel.getInstance().exitGame();
        } else {
            DayLevel.getInstance().getCells()[updateRow][updateColumn].setZombies(this);
        }
        if(isDead()){
            stopMove();
            isRemoved = true;
            //باید روی زامبی ها فور بزنیم و اگر این بولین درست بود از روت عکس آن رو ریموو کنیم
            return;
        }
        image.setLayoutX(column);
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
        DayLevel.getInstance().getDayAnc().getChildren().remove(this.image);
    }
}
