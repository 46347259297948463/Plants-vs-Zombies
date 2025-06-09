package model;

public class Bullet {

    protected String color = "green";
    protected double row;
    protected boolean isSnow = false;

//    public void setColor(String newColor) {
//        color = newColor;
//    }

    public void move(){
        row += 40;
    }

}
