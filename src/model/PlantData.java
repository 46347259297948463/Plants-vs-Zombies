package model;

import java.io.Serializable;

public class PlantData implements Serializable {

    public int row, column, HP;

    public String type;

    private static final long serialVersionUID = 1L;

    public PlantData(int row, int column, int HP, String type){
        this.row = row;
        this.column = column;
        this.HP = HP;
        this.type = type;
    }

}
