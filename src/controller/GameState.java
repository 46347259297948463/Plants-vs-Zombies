package controller;

import javafx.animation.Timeline;
import model.PlantData;
import model.Plants;
import model.Zombie;
import model.ZombieData;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {

    public ArrayList<String> names;

    public Timeline gameTimer;

    public ArrayList<ZombieData> zombies = new ArrayList<>();

    public ArrayList<PlantData> plants = new ArrayList<>();

    public int sunPoints;

    private static final long serialVersionUID = 1L;

}
