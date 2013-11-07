package me.nrubin29.rpg.quest;

import java.util.HashMap;
import java.util.Map.Entry;

import me.nrubin29.rpg.events.Event;
import me.nrubin29.rpg.events.EventManager;
import me.nrubin29.rpg.map.Maps;

public abstract class Quest {

	private final HashMap<Event, Maps> questEvents = new HashMap<Event, Maps>();
	
	public abstract void startQuest();
	
	public void finishQuest() {
		for (Entry<Event, Maps> e : questEvents.entrySet()) {
			e.getKey().requestRemoval();
		}
	}
	
	public final void registerEvent(Maps m, Event e) {
		EventManager.getInstance().registerEvent(m, e);
		questEvents.put(e, m);
	}
}