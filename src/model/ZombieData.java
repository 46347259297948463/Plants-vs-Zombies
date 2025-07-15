package model;

import java.io.Serializable;

public class ZombieData implements Serializable {

    public double x, y;

    public int HP, rowBTN, columnBTN;

    public String type;

    private static final long serialVersionUID = 1L;

    public ZombieData(double x, double y, int HP, int rowBTN, int columnBTN, String type){
        this.x = x;
        this.y = y;
        this.HP = HP;
        this.rowBTN = rowBTN;
        this.columnBTN = columnBTN;
        this.type = type;
    }

}
