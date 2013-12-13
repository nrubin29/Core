package me.nrubin29.core.keycommand.commands;

import me.nrubin29.core.data.DataManager;
import me.nrubin29.core.data.files.Settings;
import me.nrubin29.core.gui.GUI;
import me.nrubin29.core.keycommand.Key;
import me.nrubin29.core.keycommand.KeyCommand;
import me.nrubin29.core.keycommand.KeyCommandManager;
import me.nrubin29.core.map.Direction;
import me.nrubin29.core.server.Session;

public class Move extends KeyCommand {

	public Move() {
		super(
				new Key(DataManager.getInstance().getConfigurationFile(Settings.class).get("upID"), false, false, false),
				new Key(DataManager.getInstance().getConfigurationFile(Settings.class).get("downID"), false, false, false),
				new Key(DataManager.getInstance().getConfigurationFile(Settings.class).get("leftID"), false, false, false),
				new Key(DataManager.getInstance().getConfigurationFile(Settings.class).get("rightID"), false, false, false)
			);
	}
	
	public void run(Key key) {
		if (!KeyCommandManager.getInstance().inputEnabled()) return;
		
        GUI.getInstance().movement(Session.getInstance().getLocalPlayer(), Direction.valueOf(key.getKey()), true);
	}
}