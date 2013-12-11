package me.nrubin29.rpg.core.quest;

import java.util.HashMap;
import java.util.Map.Entry;

import me.nrubin29.rpg.core.event.Event;
import me.nrubin29.rpg.core.event.EventManager;
import me.nrubin29.rpg.core.map.Map;
import me.nrubin29.rpg.core.map.MapManager;

public abstract class Quest {

	private String name;
	private final HashMap<Event, Map> questEvents = new HashMap<Event, Map>();
	
	public Quest(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void startQuest();
	
	public void finishQuest() {
		for (Entry<Event, Map> e : questEvents.entrySet()) {
			e.getKey().requestRemoval();
		}
	}
	
	public final void registerEvent(Map m, Event e) {
		EventManager.getInstance().registerEvent(m, e);
		questEvents.put(e, m);
	}
	
	public final void registerEvent(String m, Event e) {
		registerEvent(MapManager.getInstance().getMap(m), e);
	}
}