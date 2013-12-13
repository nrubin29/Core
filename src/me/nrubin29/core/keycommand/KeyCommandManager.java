package me.nrubin29.core.keycommand;

import java.util.ArrayList;

import me.nrubin29.core.keycommand.commands.Fullscreen;
import me.nrubin29.core.keycommand.commands.Interact;
import me.nrubin29.core.keycommand.commands.Inventory;
import me.nrubin29.core.keycommand.commands.Move;

public class KeyCommandManager {

	private KeyCommandManager() { }
	
	private static KeyCommandManager instance = new KeyCommandManager();
	
	public static KeyCommandManager getInstance() {
		return instance;
	}
	
	private ArrayList<KeyCommand> cmds = new ArrayList<KeyCommand>();
	private boolean inputEnabled = true;
	
	public void setup() {
		registerKeyCommand(new Fullscreen());
		registerKeyCommand(new Interact());
        registerKeyCommand(new Inventory());
		registerKeyCommand(new Move());
	}
	
	public void registerKeyCommand(KeyCommand cmd) {
		cmds.add(cmd);
	}
	
	public boolean inputEnabled() {
		return inputEnabled;
	}
	
	public void setInputEnabled(boolean inputEnabled) {
		this.inputEnabled = inputEnabled;
	}
}