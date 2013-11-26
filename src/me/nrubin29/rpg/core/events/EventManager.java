package me.nrubin29.rpg.core.events;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import me.nrubin29.rpg.core.events.Event.EventType;
import me.nrubin29.rpg.core.map.Map;

public class EventManager {

	private EventManager() { }
	
	private static EventManager instance = new EventManager();
	
	public static EventManager getInstance() {
		return instance;
	}
	
	private HashMap<Map, ArrayList<Event>> events = new HashMap<Map, ArrayList<Event>>();
	
	public void registerEvent(Map map, Event e) {
		if (events.get(map) == null) events.put(map, new ArrayList<Event>());
		
		ArrayList<Event> current = events.get(map);
		current.add(e);
		events.put(map, current);
	}
	
	public void checkEvents(Map map, Point p, EventType type) {
		if (events.get(map) == null) return;
		
		for (Event e : events.get(map)) {
			if (e.getTilePoint().equals(p) && e.getType() == type && e.isEnabled()) e.run();
		}
		
		ArrayList<Event> toRemove = new ArrayList<Event>();
		
		for (Event e : events.get(map)) {
			if (e.shouldRemove()) toRemove.add(e);
		}
		
		events.get(map).removeAll(toRemove);
	}
}