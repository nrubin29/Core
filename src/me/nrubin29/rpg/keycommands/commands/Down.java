package me.nrubin29.rpg.keycommands.commands;

import java.awt.event.KeyEvent;

import me.nrubin29.rpg.Main;
import me.nrubin29.rpg.keycommands.KeyCommand;

public class Down extends KeyCommand {

	public Down() { super(KeyEvent.VK_DOWN, false, false, false); }
	
	public void run() {
		Main.getGUI().movement(KeyEvent.VK_DOWN);
	}
}