package model;
import controller.DayLevel;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.Group;


public class HypnoShroom extends Plants{

    private Timeline timer;

    private Cell[][] cells = DayLevel.getInstance().getCells();

    private static Group group;

    private static int availableNum;

    private final static int HP = 4;

    public HypnoShroom(int i, int j) {
        super(HP, i, j, 75, 20);
        DayLevel.getInstance().setAvailablePicked(false,availableNum);
        ImageView imageView = new ImageView(getClass().getResource("/images/hypno shroom.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(125);
        setImage(imageView);
        group.setOpacity(0.7);

    }


    @Override
    public Plants clonePlant(int row, int column) {
        return new HypnoShroom(row, column);
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, availableNum);
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

    }

    @Override
    public Timeline getTimer() {
        return timer;
    }

    public static void setAvailableNum(int a) {
        availableNum = a;
    }

    public static void setGroup(Group g) {
        group = g;
    }
}
