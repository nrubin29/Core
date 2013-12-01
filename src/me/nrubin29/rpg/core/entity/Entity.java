package me.nrubin29.rpg.core.entity;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.swing.*;

import me.nrubin29.rpg.core.misc.Image;
import me.nrubin29.rpg.core.misc.Interactable;
import me.nrubin29.rpg.core.util.Data;
import me.nrubin29.rpg.core.util.ImageUtil;
import me.nrubin29.rpg.core.util.MapTileUtil.Direction;

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
    
    private Direction current;
    
    private HashMap<Direction, Integer> walkCycle = new HashMap<Direction, Integer>();

    private JLabel label;
    private Point loc;
    
    public Entity(String name, int x, int y) {
    	for (Field f : getClass().getFields()) {
    		if (f.getType().equals(ImageIcon.class) && !f.getName().equals("face")) {
    			try {
    				f.set(this, ImageUtil.resizeImage(ImageUtil.getImage("sprites/" + name.toLowerCase() + "/" + f.getName()), Data.TILE_DIM, Data.TILE_DIM));
    			}
    			catch (Exception e) { e.printStackTrace(); }
    		}
    	}
    	
    	face = ImageUtil.getImage("sprites/" + name.toLowerCase() + "/face");

        label = new JLabel(getImage(Direction.DOWN, false));
        label.setBounds(x, y, Data.TILE_DIM, Data.TILE_DIM);
        loc = new Point(x, y);
    }
    
    public ImageIcon getImage(Direction d, boolean moving) {
    	current = d;
    	
    	if (walkCycle.get(d) == null) walkCycle.put(d, 1);
    	
    	try {
    		Field f = getClass().getField(d.name().toLowerCase() + (moving ? "Moving" + walkCycle.get(d) : "Static"));
        	return (ImageIcon) f.get(this);
    	}
    	catch (Exception e) { return null; }
    }

    public abstract void interact();
    
    public Direction getCurrentDirection() {
    	return current;
    }

    public Point getLocation() {
        return loc;
    }

    public JLabel getLabel() {
        return label;
    }
    
	public ImageIcon getImage() {
		return face;
	}

	public ImageIcon getImage(int width, int height) {
		return ImageUtil.resizeImage(face, width, height);
	}
}