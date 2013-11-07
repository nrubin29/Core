package me.nrubin29.rpg.keycommands.commands;

import java.awt.event.KeyEvent;

import me.nrubin29.rpg.Main;
import me.nrubin29.rpg.keycommands.KeyCommand;

public class Up extends KeyCommand {

	public Up() { super(KeyEvent.VK_UP, false, false, false); }
	
	public void run() {
		Main.getGUI().movement(KeyEvent.VK_UP);
	}
}