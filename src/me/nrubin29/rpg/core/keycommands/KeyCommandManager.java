package me.nrubin29.rpg.core.keycommands;

import java.util.ArrayList;

import me.nrubin29.rpg.core.keycommands.commands.Fullscreen;
import me.nrubin29.rpg.core.keycommands.commands.Interact;
import me.nrubin29.rpg.core.keycommands.commands.Move;

public class KeyCommandManager {

	private KeyCommandManager() { }
	
	private static KeyCommandManager instance = new KeyCommandManager();
	
	public static KeyCommandManager getInstance() {
		return instance;
	}
	
	private ArrayList<KeyCommand> cmds = new ArrayList<KeyCommand>();
	
	public void setup() {
		registerKeyCommand(new Fullscreen());
		registerKeyCommand(new Interact());
		registerKeyCommand(new Move());
	}
	
	public void registerKeyCommand(KeyCommand cmd) {
		cmds.add(cmd);
	}
}