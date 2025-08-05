package model;

import java.io.Serializable;

public class ZombieData implements Serializable {

    public double x, y;

    public double HP;

    public int rowBTN, columnBTN;

    public String type;

    public ZombieData(double x, double y, double HP, int rowBTN, int columnBTN, String type){
        this.x = x;
        this.y = y;
        this.HP = HP;
        this.rowBTN = rowBTN;
        this.columnBTN = columnBTN;
        this.type = type;
    }

}
