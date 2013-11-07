package me.nrubin29.rpg.sprite;

import java.lang.reflect.Field;
import java.util.HashMap;

import javax.swing.ImageIcon;

import me.nrubin29.rpg.util.Constants;
import me.nrubin29.rpg.util.ImageUtil;
import me.nrubin29.rpg.util.MapTileUtil.Direction;

public class Sprite {

	public ImageIcon
			downStatic = null,
			downMoving1 = null,
			downMoving2 = null,
			leftStatic = null,
			leftMoving1 = null,
			leftMoving2 = null,
			rightStatic = null,
			rightMoving1 = null,
			rightMoving2 = null,
			upStatic = null,
			upMoving1 = null,
			upMoving2 = null;
    
    private Direction current;
    
    private HashMap<Direction, Integer> walkCycle = new HashMap<Direction, Integer>();
    
    public Sprite(String name) {
    	for (Field f : getClass().getFields()) {
    		if (f.getType().equals(ImageIcon.class)) {
    			try {
    				f.set(this, ImageUtil.getImage("sprites/" + name.toLowerCase() + "/" + f.getName(), Constants.TILE_DIM, Constants.TILE_DIM));
    			}
    			catch (Exception e) { e.printStackTrace(); }
    		}
    	}
    }
    
    public ImageIcon getImage(Direction d, boolean moving) {
    	current = d;
    	
    	if (walkCycle.get(d) == null) walkCycle.put(d, 1);
    	
    	try {
    		Field f = getClass().getField(d.name().toLowerCase() + (moving ? "Moving" + walkCycle.get(d) : "Static"));
        	return (ImageIcon) f.get(this);
    	}
    	catch (Exception e) { e.printStackTrace(); return null; /* TODO: Error handling */ }
    }
    
    public Direction getCurrentDirection() {
    	return current;
    }
}