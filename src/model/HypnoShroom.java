package model;
import controller.DayLevel;
import controller.FogLevel;
import controller.NightLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.util.Duration;


public class HypnoShroom extends Plants{

    private Timeline timer;

    private Cell[][] cells;

    private static Group group;

    private static int availableNum;

    private final static int HP = 1;

    public HypnoShroom(int i, int j) {
        super(HP, i, j, 75, 20);
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().setAvailablePicked(false,availableNum);
            cells = DayLevel.getInstance().getCells();
            needCoffee = true;
            coffee = false;
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().setAvailablePicked(false,availableNum);
            cells = NightLevel.getInstance().getCells();
            needCoffee = false;
            coffee = true;
        } else if (obj instanceof FogLevel) {
            FogLevel.getInstance().setAvailablePicked(false,availableNum);
            cells = FogLevel.getInstance().getCells();
            needCoffee = false;
            coffee = true;
        }
        ImageView imageView = new ImageView(getClass().getResource("/view/images/hypno shroom.png").toString());
        imageView.setFitWidth(90);
        imageView.setFitHeight(120);
        imageView.setLayoutX(25);
        setImage(imageView);

        group.setOpacity(0.7);

        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public HypnoShroom() {
        price = 75;
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new HypnoShroom(row, column);
    }

    @Override
    protected void recharge() {
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().setAvailablePicked(true, availableNum);
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().setAvailablePicked(true, availableNum);
        } else if (obj instanceof FogLevel) {
            FogLevel.getInstance().setAvailablePicked(true, availableNum);
        }
        timer.stop();
        group.setOpacity(1);
    }

    @Override
    public void stop() {

    }

    @Override
    public void play() {

    }

    @Override
    public void end() {
        cells[row][column].getGroup().getChildren().remove(image);
        cells[row][column].removePlant();
    }

    @Override
    public Timeline getTimer() {
        return timer;
    }

    @Override
    public void takeDamage(int damage){
        super.HP -= damage;
        if (super.HP < 1) {
            this.end();
        }
    }

    public static void setAvailableNum(int a) {
        availableNum = a;
    }

    public static void setGroup(Group g) {
        group = g;
    }

}
