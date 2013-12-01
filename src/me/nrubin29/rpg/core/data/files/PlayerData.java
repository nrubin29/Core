package me.nrubin29.rpg.core.data.files;

import java.util.ArrayList;

import me.nrubin29.rpg.core.data.DataFile;
import me.nrubin29.rpg.core.item.Item;
import me.nrubin29.rpg.core.misc.Achievement;
import me.nrubin29.rpg.core.util.Data;

public class PlayerData extends DataFile {
	
	public PlayerData() { super("playerdata"); }
    
    private ArrayList<Achievement> achs = new ArrayList<Achievement>();
    
    public void addAchievement(Achievement ach) {
    	achs.add(ach);
    }
    
    public boolean hasAchievement(Achievement ach) {
    	return achs.contains(ach);
    }

    private ArrayList<Item> inv = new ArrayList<Item>();

    public void addItem(Item item) {
        inv.add(item);
    }

    public ArrayList<Item> getInventory() {
        return (ArrayList<Item>) inv.clone();
    }
}