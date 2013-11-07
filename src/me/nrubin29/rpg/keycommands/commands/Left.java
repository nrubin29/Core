package me.nrubin29.rpg.keycommands.commands;

import java.awt.event.KeyEvent;

import me.nrubin29.rpg.Main;
import me.nrubin29.rpg.keycommands.KeyCommand;

public class Left extends KeyCommand {

	public Left() { super(KeyEvent.VK_LEFT, false, false, false); }
	
	public void run() {
		Main.getGUI().movement(KeyEvent.VK_LEFT);
	}
}