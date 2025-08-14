package model;

import java.io.Serializable;

public class PlantData implements Serializable {

    public int row, column, HP;

    public String type;

    public boolean needCoffee;

    public boolean coffee;

    public double[] plantTimer = new double[3];

    public PlantData(int row, int column, int HP, String type, boolean coffee, boolean needCoffee){
        this.row = row;
        this.column = column;
        this.HP = HP;
        this.type = type;
        this.coffee = coffee;
        this.needCoffee = needCoffee;
    }

}
