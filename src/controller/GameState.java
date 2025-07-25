package controller;

import javafx.animation.Timeline;
import model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class GameState implements Serializable {

    public String type;

    public ArrayList<String> names;

    public long gameTimer;

    public ArrayList<ZombieData> zombies;

    public ArrayList<PlantData> plants;

    public int sunPoints;

    public ArrayList<Long> rechargeTime;

    public boolean isOnGameMode = true;

}
