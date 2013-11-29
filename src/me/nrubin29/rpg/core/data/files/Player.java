package me.nrubin29.rpg.core.data.files;

import java.util.ArrayList;

import me.nrubin29.rpg.core.data.DataFile;
import me.nrubin29.rpg.core.misc.Achievement;
import me.nrubin29.rpg.core.util.Data;

public class Player extends DataFile {
	
	public Player() { super("playerdata"); }
    
    private ArrayList<Achievement> achs = new ArrayList<Achievement>();
    
    public void addAchievement(Achievement ach) {
    	achs.add(ach);
    }
    
    public boolean hasAchievement(Achievement ach) {
    	return achs.contains(ach);
    }
}