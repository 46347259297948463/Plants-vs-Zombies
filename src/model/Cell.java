package model;


import controller.FogLevel;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Cell {

    private Plants plant;

    private ArrayList<Zombie> zombies = null;

    private Group group;

    private Button button;

    private boolean isAvailable = true;

    private boolean isGrave = false;

    private ImageView graveImage;

    private ImageView cloudImage;

    public Cell(Button button, Group group) {
        this.button = button;
        this.group = group;
    }

    public void removePlant(){
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
        if (isAvailable || (isGrave && plant instanceof GraveBuster)) {
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

    public boolean isGrave() {
        return isGrave;
    }

    public void setGrave(boolean grave) {
        isGrave = grave;
    }

    public ImageView getGraveImage() {
        return graveImage;
    }

    public void setGraveImage(ImageView graveImage) {
        this.graveImage = graveImage;
    }

    public void setCloud() {
        cloudImage = new ImageView(getClass().getResource("/view/images/cloud.png").toString());
        cloudImage.setFitWidth(175);
        cloudImage.setFitHeight(145);
        cloudImage.setLayoutY(group.getLayoutY());
        cloudImage.setLayoutX(group.getLayoutX());
        FogLevel.getInstance().getFogAnc().getChildren().add(cloudImage);

    }
}
