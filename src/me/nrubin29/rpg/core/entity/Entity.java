package me.nrubin29.rpg.core.entity;

import java.awt.Point;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.swing.ImageIcon;

import me.nrubin29.rpg.core.map.Direction;
import me.nrubin29.rpg.core.misc.Image;
import me.nrubin29.rpg.core.util.Constants;
import me.nrubin29.rpg.core.util.ResourceUtil;

public abstract class Entity implements Image, Interactable {

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
			upMoving2 = null,
			face = null;
    
	private ImageIcon currentImage;
    private Direction currentDirection;
    private Point currentLocation;
    
    private HashMap<Direction, Integer> walkCycle = new HashMap<Direction, Integer>();
    
    public Entity(String name, Point spawn) {
    	for (Field f : getClass().getFields()) {
    		if (f.getType().equals(ImageIcon.class) && !f.getName().equals("face")) {
    			try {
    				f.set(this, ResourceUtil.getImage("sprites/" + name.toLowerCase() + "/" + f.getName(), Constants.TILE_DIM, Constants.TILE_DIM));
    			}
    			catch (Exception e) { e.printStackTrace(); }
    		}
    	}
    	
    	face = ResourceUtil.getImage("sprites/" + name.toLowerCase() + "/face");

    	this.currentImage = getImage(Direction.DOWN, false);
        this.currentLocation = spawn;
    }
    
    public ImageIcon getImage(Direction d, boolean moving) {
    	this.currentDirection = d;
    	
    	if (walkCycle.get(d) == null) walkCycle.put(d, 1);
    	
    	try {
    		Field f = getClass().getField(d.name().toLowerCase() + (moving ? "Moving" + walkCycle.get(d) : "Static"));
        	return (ImageIcon) f.get(this);
    	}
    	catch (Exception e) { return null; }
    }

    public abstract void interact();
    
    public Direction getCurrentDirection() {
    	return currentDirection;
    }

    public Point getLocation() {
        return currentLocation;
    }
    
    public void setLocation(Point loc) {
    	this.currentLocation = loc;
    }

    public ImageIcon getCurrentImage() {
    	return currentImage;
    }
    
    public void setCurrentImage(Direction d, boolean moving) {
    	this.currentImage = getImage(d, moving);
    }
    
	public ImageIcon getImage() {
		return face;
	}

	public ImageIcon getImage(int width, int height) {
		return ResourceUtil.resizeImage(face, width, height);
	}
}