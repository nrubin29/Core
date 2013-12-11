package me.nrubin29.rpg.core.script.cmds;

import me.nrubin29.rpg.core.map.Map;
import me.nrubin29.rpg.core.map.MapManager;
import me.nrubin29.rpg.core.script.ScriptCommand;

public class MapCommand extends ScriptCommand {

	/*
	 * map set name
	 */
	public void parse(String[] args) {
		if (args.length < 2) return;
		
		Map map;
		
		try {
			map = MapManager.getInstance().getMap(args[1]);
		}
		catch (Exception e) { return; }
		
		if (args[0].equalsIgnoreCase("set")) {
			map.display();
		}
	}
}