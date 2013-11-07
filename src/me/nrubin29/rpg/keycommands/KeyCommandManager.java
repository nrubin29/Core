package me.nrubin29.rpg.keycommands;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import me.nrubin29.rpg.keycommands.commands.Fullscreen;

public class KeyCommandManager {

	private KeyCommandManager() { }
	
	private static KeyCommandManager instance = new KeyCommandManager();
	
	public static KeyCommandManager getInstance() {
		return instance;
	}
	
	private ArrayList<KeyCommand> cmds = new ArrayList<KeyCommand>();
	
	public void setup() {
		cmds.add(new Fullscreen());
	}
	
	public boolean runCommand(KeyEvent e) {
		for (KeyCommand cmd : cmds) {
			if (cmd.shouldRun(e)) {
				cmd.run();
				return true;
			}
		}
		
		return false;
	}
}