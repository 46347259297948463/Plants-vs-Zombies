package model;

import javafx.animation.Timeline;
import javafx.scene.Group;

public class Plantern extends Plants{

    private static Group group;

    private static int availableNum;

    @Override
    public Plants clonePlant(int row, int column) {
        return null;
    }

    @Override
    protected void recharge() {

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
        return null;
    }

    public static void setGroup(Group g) {
        group = g;
    }

    public static void setAvailableNum(int a) {
        availableNum = a;
    }

}
