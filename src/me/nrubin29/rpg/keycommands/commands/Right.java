package me.nrubin29.rpg.keycommands.commands;

import java.awt.event.KeyEvent;

import me.nrubin29.rpg.Main;
import me.nrubin29.rpg.keycommands.KeyCommand;

public class Right extends KeyCommand {

	public Right() { super(KeyEvent.VK_RIGHT, false, false, false); }
	
	public void run() {
		Main.getGUI().movement(KeyEvent.VK_RIGHT);
	}
}