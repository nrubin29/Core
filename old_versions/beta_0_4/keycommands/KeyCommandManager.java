package me.nrubin29.rpg.keycommands;

import java.util.ArrayList;

import me.nrubin29.rpg.keycommands.commands.BindKeys;
import me.nrubin29.rpg.keycommands.commands.Down;
import me.nrubin29.rpg.keycommands.commands.Fullscreen;
import me.nrubin29.rpg.keycommands.commands.Interact;
import me.nrubin29.rpg.keycommands.commands.Left;
import me.nrubin29.rpg.keycommands.commands.Right;
import me.nrubin29.rpg.keycommands.commands.Up;

public class KeyCommandManager {

	private KeyCommandManager() { }
	
	private static KeyCommandManager instance = new KeyCommandManager();
	
	public static KeyCommandManager getInstance() {
		return instance;
	}
	
	private ArrayList<KeyCommand> cmds = new ArrayList<KeyCommand>();
	
	public void setup() {
		cmds.add(new BindKeys());
		cmds.add(new Fullscreen());
		cmds.add(new Interact());
		
		cmds.add(new Down());
		cmds.add(new Left());
		cmds.add(new Right());
		cmds.add(new Up());
	}
}