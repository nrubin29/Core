package me.nrubin29.rpg.keycommands.commands;

import java.awt.event.KeyEvent;

import me.nrubin29.rpg.Main;
import me.nrubin29.rpg.data.DataManager;
import me.nrubin29.rpg.data.files.Keys;
import me.nrubin29.rpg.keycommands.KeyCommand;

public class Up extends KeyCommand {

	public Up() { super(Integer.parseInt(DataManager.getInstance().getConfigurationFile(Keys.class).getValue("upID")), false, false, false); }
	
	public void run() {
		Main.getGUI().movement(KeyEvent.VK_UP);
	}
}