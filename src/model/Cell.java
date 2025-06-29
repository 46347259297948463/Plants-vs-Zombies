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
        if (zombies == null){
            zombies = new ArrayList<>();
        }
        zombies.add(zombie);
    }

    public void removePlant(){
        this.plant = null;
    }

    public void removeZombie(Zombie zombie){
        if (zombies == null){
            return;
        }
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
        if (zombies != null){
            for (int i = 0; i < zombies.size(); i++){
                if (zombies.get(i).isDead()){
                    zombies.remove(i);
                }
            }
            if (zombies.size() == 0){
                zombies = null;
                return null;
            }
        }
        return zombies;
    }

    public void removeAllZombies(){
        if (zombies != null){
            while (zombies.size() > 0){
                zombies.get(0).setHP(0);
                zombies.get(0).dead();
                this.removeZombie(zombies.get(0));
                System.out.println("zombie.rowBTN = " + zombies.get(0).rowBTN);
                System.out.println("zombie.columnBTN = " + zombies.get(0).columnBTN);
            }
        }
        zombies = null;
    }

}
