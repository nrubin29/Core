package me.nrubin29.rpg.core.script;

import java.util.ArrayList;

import me.nrubin29.rpg.core.script.cmds.AchievementCommand;
import me.nrubin29.rpg.core.script.cmds.AudioCommand;
import me.nrubin29.rpg.core.script.cmds.EventCommand;
import me.nrubin29.rpg.core.script.cmds.ItemCommand;
import me.nrubin29.rpg.core.script.cmds.MapCommand;
import me.nrubin29.rpg.core.script.cmds.PopupCommand;

/*
 * This class handles parsing CoreScript, RPG-Core's implementation of a game scripting language.
 * With CoreScript, you can tap into RPG-Core's power from your own map!
 * 
 * Sample inputs:
 * 
 * entity spawn npc type x y
 * event move x y popup image Popup Message
 * sfx play name
 * achievement award name
 * 
 * Advanced inputs (coming later):
 * 
 * if achievement has name popup image Popup Message
 */
public class ScriptParser {
	
	private ScriptParser() { }
	
	private static ScriptParser instance = new ScriptParser();
	
	public static ScriptParser getInstance() {
		return instance;
	}

	private ArrayList<ScriptCommand> cmds = new ArrayList<ScriptCommand>();
	
	public void setup() {
		cmds.add(new AchievementCommand());
		cmds.add(new AudioCommand());
		cmds.add(new EventCommand());
		cmds.add(new ItemCommand());
		cmds.add(new MapCommand());
		cmds.add(new PopupCommand());
	}
	
	@SuppressWarnings("unchecked")
	public void parse(String input) {
		try {
			String cmdClassNameLowerCase = input.split(" ")[0];
			String cmdClassName = Character.toUpperCase(cmdClassNameLowerCase.charAt(0)) + cmdClassNameLowerCase.substring(1);
			
			Class<? extends ScriptCommand> cmdClass = (Class<? extends ScriptCommand>) Class.forName("me.nrubin29.rpg.core.script.cmds." + cmdClassName + "Command");
			
			ArrayList<String> args = new ArrayList<String>();
			
			for (int i = 1; i < input.split(" ").length; i++) {
				args.add(input.split(" ")[i]);
			}
			
			for (ScriptCommand cmd : cmds) {
				if (cmd.getClass().equals(cmdClass)) {
					cmd.parse(args.toArray(new String[args.size()]));
					break;
				}
			}
		}
		catch (Exception ignored) { }
	}
}