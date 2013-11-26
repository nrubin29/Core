package me.nrubin29.rpg.core.data.files;

import java.util.ArrayList;

import me.nrubin29.rpg.core.data.DataFile;
import me.nrubin29.rpg.core.misc.Achievement;
import me.nrubin29.rpg.core.util.Data;

public class Player extends DataFile {
	
	public Player() { super("playerdata"); }

    private int health = Data.MAX_HEALTH, food = Data.MAX_FOOD;
    
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