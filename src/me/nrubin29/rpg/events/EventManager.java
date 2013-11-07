package me.nrubin29.rpg.events;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import me.nrubin29.rpg.events.Event.EventType;
import me.nrubin29.rpg.map.Maps;

public class EventManager {

	private EventManager() { }
	
	private static EventManager instance = new EventManager();
	
	public static EventManager getInstance() {
		return instance;
	}
	
	private HashMap<Maps, ArrayList<Event>> events = new HashMap<Maps, ArrayList<Event>>();
	
	public void registerEvent(Maps map, Event e) {
		if (events.get(map) == null) events.put(map, new ArrayList<Event>());
		
		ArrayList<Event> current = events.get(map);
		current.add(e);
		events.put(map, current);
	}
	
	public void checkEvents(Maps map, Point p, EventType type) {
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