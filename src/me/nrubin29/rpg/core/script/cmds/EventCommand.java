package me.nrubin29.rpg.core.script.cmds;

import me.nrubin29.rpg.core.event.Event;
import me.nrubin29.rpg.core.event.Event.EventType;
import me.nrubin29.rpg.core.event.EventManager;
import me.nrubin29.rpg.core.gui.GUI;
import me.nrubin29.rpg.core.script.ScriptCommand;

public class EventCommand extends ScriptCommand {

	/*
	 * move x y popup image Popup Message | achievement give name
	 */
	public void parse(String[] args) {
		if (args.length < 3) return;
		
		EventType type;
		int x, y;
		
		try {
			type = EventType.valueOf(args[0].toUpperCase());
			x = Integer.parseInt(args[1]);
			y = Integer.parseInt(args[2]);
		}
		catch (Exception e) { return; }
		
		EventManager.getInstance().registerEvent(GUI.getInstance().getCurrentMap(), new Event(type, x, y, true, argsToString(args, 3).split("/")));
	}
}