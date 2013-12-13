package me.nrubin29.core.event;

import java.awt.Point;
import java.util.Arrays;

import me.nrubin29.core.script.ScriptParser;
import me.nrubin29.core.util.Constants;

public class Event {
	
	public enum EventType { MOVE, INTERACT; }

	private final EventType type;
    private final Point tilePoint;
    private final String[] cmds;
    private boolean isEnabled, shouldRemove;

    public Event(EventType type, int xTile, int yTile, boolean isEnabled, String... cmds) {
    	this.type = type;
        this.tilePoint = new Point(xTile * Constants.TILE_DIM, yTile * Constants.TILE_DIM);
        this.cmds = cmds;
        this.isEnabled = isEnabled;
        
        System.out.println("Recieved cmds " + Arrays.toString(cmds));
    }

    public Point getTilePoint() {
        return tilePoint;
    }
    
    public EventType getType() {
    	return type;
    }
    
    public boolean isEnabled() {
    	return isEnabled;
    }
    
    public void setEnabled(boolean isEnabled) {
    	this.isEnabled = isEnabled;
    }
    
    public boolean shouldRemove() {
    	return shouldRemove;
    }
    
    public void requestRemoval() {
    	setEnabled(false);
    	shouldRemove = true;
    }
    
    public void run() {
    	for (String cmd : cmds) {
    		ScriptParser.getInstance().parse(cmd);
    	}
    }
}