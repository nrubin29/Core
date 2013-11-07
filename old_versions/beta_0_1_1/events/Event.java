package me.nrubin29.rpg.events;

import java.awt.Point;

import me.nrubin29.rpg.Main;
import me.nrubin29.rpg.map.Maps;
import me.nrubin29.rpg.util.Constants;

public abstract class Event {
	
	public enum EventType { MOVE, INTERACT; }
	
	public static Event createMapMoveEvent(final Maps to, Point trigger, EventType type, final Point spawnTo, boolean isEnabled) {
		return new Event(type, trigger.x, trigger.y, isEnabled) {
			public void run() {
				Main.getGUI().renderMap(to);
				Main.getGUI().getPlayerLabel().setLocation(spawnTo);
			}
		};
	}

	private final EventType type;
    private final Point tilePoint;
    private boolean isEnabled, shouldRemove;

    public Event(EventType type, int xTile, int yTile, boolean isEnabled) {
    	this.type = type;
        this.tilePoint = new Point(xTile * Constants.TILE_DIM, yTile * Constants.TILE_DIM);
        this.isEnabled = isEnabled;
    }

    public final Point getTilePoint() {
        return tilePoint;
    }
    
    public final EventType getType() {
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
    
    public void setShouldRemove() {
    	setEnabled(false);
    	shouldRemove = true;
    }

    public abstract void run();
}