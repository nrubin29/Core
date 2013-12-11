package me.nrubin29.rpg.sprite;

import java.lang.reflect.Field;
import java.util.HashMap;

import javax.swing.ImageIcon;

import me.nrubin29.rpg.util.Constants;
import me.nrubin29.rpg.util.ImageUtil;
import me.nrubin29.rpg.util.MapTileUtil.Direction;

public class Sprite {

	public final ImageIcon downStatic, downMoving1, downMoving2, leftStatic, leftMoving1, leftMoving2, rightStatic, rightMoving1, rightMoving2, upStatic, upMoving1, upMoving2;
    
    private Direction current;
    
    private HashMap<Direction, Integer> walkCycle = new HashMap<Direction, Integer>();
    
    public Sprite(String name) {
        downStatic = ImageUtil.getImage("sprites/" + name.toLowerCase() + "/down_static", Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);
        downMoving1 = ImageUtil.getImage("sprites/" + name.toLowerCase() + "/down_moving1", Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);
        downMoving2 = ImageUtil.getImage("sprites/" + name.toLowerCase() + "/down_moving2", Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);

        leftStatic = ImageUtil.getImage("sprites/" + name.toLowerCase() + "/left_static", Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);
        leftMoving1 = ImageUtil.getImage("sprites/" + name.toLowerCase() + "/left_moving1", Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);
        leftMoving2 = ImageUtil.getImage("sprites/" + name.toLowerCase() + "/left_moving2", Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);

        rightStatic = ImageUtil.getImage("sprites/" + name.toLowerCase() + "/right_static", Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);
        rightMoving1 = ImageUtil.getImage("sprites/" + name.toLowerCase() + "/right_moving1", Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);
        rightMoving2 = ImageUtil.getImage("sprites/" + name.toLowerCase() + "/right_moving2", Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);

        upStatic = ImageUtil.getImage("sprites/" + name.toLowerCase() + "/up_static", Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);
        upMoving1 = ImageUtil.getImage("sprites/" + name.toLowerCase() + "/up_moving1", Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);
        upMoving2 = ImageUtil.getImage("sprites/" + name.toLowerCase() + "/up_moving2", Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);
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