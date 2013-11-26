package me.nrubin29.rpg.core.events;

import java.awt.Point;

import me.nrubin29.rpg.core.Game;
import me.nrubin29.rpg.core.map.Map;
import me.nrubin29.rpg.core.server.Session;
import me.nrubin29.rpg.core.util.Data;

public abstract class Event {
	
	public enum EventType { MOVE, INTERACT; }
	
	public static Event createMapMoveEvent(final Map to, Point trigger, EventType type, final Point spawnTo, boolean isEnabled) {
		return new Event(type, trigger.x, trigger.y, isEnabled) {
			public void run() {
				to.display();
                Game.getGUI().getPlayerLabel(Session.getInstance().getLocalPlayer()).setLocation(spawnTo);
			}
		};
	}

	private final EventType type;
    private final Point tilePoint;
    private boolean isEnabled, shouldRemove;

    public Event(EventType type, int xTile, int yTile, boolean isEnabled) {
    	this.type = type;
        this.tilePoint = new Point(xTile * Data.TILE_DIM, yTile * Data.TILE_DIM);
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
    
    public void requestRemoval() {
    	setEnabled(false);
    	shouldRemove = true;
    }

    public abstract void run();
}