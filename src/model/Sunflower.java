package model;

import controller.DayLevel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sunflower extends Plants{

    private final static int HP = 4;
    private final static int price = 50;
    private final static double rechargeTime = 3;
    private static boolean available = true;
    private GameTimer timer = new GameTimer();
    public Sunflower(int i, int j) {
        super(HP, i, j, price, rechargeTime);
        timer.start();
        ImageView imageView = new ImageView(getClass().getResource("/view/images/sunflower.png").toString());
        imageView.setFitWidth(125);
        imageView.setFitHeight(125);
        setImage(imageView);
    }

    public Sunflower(){

    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new Sunflower(row, column);
    }

    public boolean needSun(){
        return timer.getTimeS() % 5 == 0;
    }

    public void makeSun(){
        if (needSun()){
            ImageView imageView = new ImageView(getClass().getResource("/view/images/sun.png").toString());
            DayLevel.getInstance().getBoardGroups()[row][column].getChildren().add(imageView);
        }
    }
}
