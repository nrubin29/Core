package me.nrubin29.rpg.core.keycommands.commands;

import me.nrubin29.rpg.core.Game;
import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.files.Keys;
import me.nrubin29.rpg.core.keycommands.Key;
import me.nrubin29.rpg.core.keycommands.KeyCommand;
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
		Game.getGUI().movement(Session.getInstance().getLocalPlayer(), key.getKey());
	}
}