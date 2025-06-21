package model;


import javafx.scene.Group;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class Cell {
    private int row;
    private int column;
    private Plants plant;
    private ArrayList<Zombie> zombies;
    private Group group;
    private Button button;

    public Cell(int row, int column, Button button, Group group) {
        this.row = row;
        this.column = column;
        this.button = button;
        this.group = group;
    }

    public boolean hasPlant(){
        return plant != null;
    }

    public boolean hasZombie(){
        return zombies.get(0) != null;
    }

    public void setPlants(Plants plant){
        this.plant = plant;
    }

    public void setZombies(Zombie zombie){
        zombies.add(zombie);
    }

    public void removePlant(){
        //isRemoved = true;
        this.plant = null;
    }

    public void removeZombie(Zombie zombie){
        zombies.remove(zombie);
    }

    public Button getButton(){
        return button;
    }

    public Group getGroup(){
        return group;
    }

    public Plants getPlant(){
        return plant;
    }

    public ArrayList<Zombie> getZombies(){
        return zombies;
    }




}
