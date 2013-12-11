package me.nrubin29.rpg.core.data.files;

import java.util.ArrayList;

import me.nrubin29.rpg.core.data.DataFile;
import me.nrubin29.rpg.core.gui.Notification;
import me.nrubin29.rpg.core.item.Item;
import me.nrubin29.rpg.core.misc.Achievement;

public class PlayerData extends DataFile {
	
	public PlayerData() { super("data", "playerdata"); }
    
    private ArrayList<Achievement> achs = new ArrayList<Achievement>();
    
    public void addAchievement(Achievement ach) {
    	achs.add(ach);
    	Notification.popupAcheievment(ach);
    }
    
    public boolean hasAchievement(Achievement ach) {
    	return achs.contains(ach);
    }

    private ArrayList<Item> inv = new ArrayList<Item>();

    public void addItem(Item item) {
        inv.add(item);
    }

    @SuppressWarnings("unchecked")
	public ArrayList<Item> getInventory() {
        return (ArrayList<Item>) inv.clone();
    }
}