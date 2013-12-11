package me.nrubin29.rpg.misc;

import java.util.ArrayList;

import me.nrubin29.rpg.util.Constants;

public class PlayerData {

    private PlayerData() { }

    private static PlayerData instance = new PlayerData();

    public static PlayerData getInstance() {
        return instance;
    }
    
    private int health = Constants.MAX_HEALTH, food = Constants.MAX_FOOD;
    
    public int getHealth() {
    	return health;
    }
    
    public void setHealth(int health) {
    	this.health = health;
    }
    
    public int getFood() {
    	return food;
    }
    
    public void setFood(int food) {
    	this.food = food;
    }
    
    private ArrayList<Achievement> achs = new ArrayList<Achievement>();
    
    public void addAchievement(Achievement ach) {
    	achs.add(ach);
    }
    
    public boolean hasAchievement(Achievement ach) {
    	return achs.contains(ach);
    }
}