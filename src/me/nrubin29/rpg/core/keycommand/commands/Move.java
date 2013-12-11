package me.nrubin29.rpg.core.keycommand.commands;

import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.files.Keys;
import me.nrubin29.rpg.core.gui.GUI;
import me.nrubin29.rpg.core.keycommand.Key;
import me.nrubin29.rpg.core.keycommand.KeyCommand;
import me.nrubin29.rpg.core.keycommand.KeyCommandManager;
import me.nrubin29.rpg.core.map.Direction;
import me.nrubin29.rpg.core.server.Session;

public class Move extends KeyCommand {

	public Move() {
		super(
				new Key(DataManager.getInstance().getConfigurationFile(Keys.class).getValue("upID"), false, false, false),
				new Key(DataManager.getInstance().getConfigurationFile(Keys.class).getValue("downID"), false, false, false),
				new Key(DataManager.getInstance().getConfigurationFile(Keys.class).getValue("leftID"), false, false, false),
				new Key(DataManager.getInstance().getConfigurationFile(Keys.class).getValue("rightID"), false, false, false)
			);
	}
	
	public void run(Key key) {
		if (!KeyCommandManager.getInstance().inputEnabled()) return;
		
        GUI.getInstance().movement(Session.getInstance().getLocalPlayer(), Direction.valueOf(key.getKey()), true);
	}
}