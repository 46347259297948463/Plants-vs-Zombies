package model;


import javafx.scene.Group;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class Cell {
    private int row;

    private int column;

    private Plants plant;

    private ArrayList<Zombie> zombies = null;

    private Group group;

    private Button button;

    private boolean isAvailable = true;

    public Cell(int row, int column, Button button, Group group) {
        this.row = row;
        this.column = column;
        this.button = button;
        this.group = group;
    }

    public void removePlant(){
        plant.end();
        this.plant = null;
    }

    public void removeZombie(Zombie zombie){
        if (zombies != null) {
            zombies.remove(zombie);
            if (zombies.isEmpty()) {
                zombies = null;
            }
        }
    }

    public void removeAllZombies(){
        if (zombies != null && !zombies.isEmpty()){
            ArrayList<Zombie> temp = new ArrayList<>(zombies);
            for (Zombie zombie : temp){
                zombie.setHP(0);
                zombie.dead();
            }
        }
        zombies = null;
    }

    public boolean hasPlant(){
        return plant != null;
    }

    public boolean hasZombie(){
        return zombies.get(0) != null;
    }

    public void setPlants(Plants plant){
        if (isAvailable) {
            this.plant = plant;
        }
    }

    public void setZombies(Zombie zombie){
        if (zombies == null || zombies.isEmpty()){
            zombies = new ArrayList<>();
        }
        zombies.add(zombie);
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
        if (zombies != null) {
            zombies.removeIf(zombie -> zombie.isDead());
            if (zombies.isEmpty()) {
                zombies = null;
                return null;
            }
        }
        return zombies;

    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
