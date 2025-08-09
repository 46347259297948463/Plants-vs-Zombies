package model;


import javafx.scene.Group;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class Cell {

    private Plants plant;

    private ArrayList<Zombie> zombies = null;

    private Group group;

    private Button button;

    private boolean isAvailable = true;

    public Cell(Button button, Group group) {
        this.button = button;
        this.group = group;
    }

    public void removePlant(){
        if (plant != null) {
            plant.end();
        }
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

    public ArrayList<Zombie> getZombies() {
        if (zombies != null) {
            zombies.removeIf(zombie -> zombie == null || zombie.isDead());
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

    public void clear() {
        zombies = null;
        plant = null;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
