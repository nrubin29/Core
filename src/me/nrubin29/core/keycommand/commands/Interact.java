package me.nrubin29.core.keycommand.commands;

import java.awt.Point;

import me.nrubin29.core.data.DataManager;
import me.nrubin29.core.data.files.Settings;
import me.nrubin29.core.event.EventManager;
import me.nrubin29.core.event.Event.EventType;
import me.nrubin29.core.gui.GUI;
import me.nrubin29.core.keycommand.Key;
import me.nrubin29.core.keycommand.KeyCommand;
import me.nrubin29.core.keycommand.KeyCommandManager;
import me.nrubin29.core.map.Direction;
import me.nrubin29.core.server.Session;

public class Interact extends KeyCommand {

	public Interact() {
		super(
				new Key(DataManager.getInstance().getConfigurationFile(Settings.class).get("interactID"), false, false, false)
				);
	}
	
	public void run(Key key) {
        //TODO: Use Interactable interface!
		
		if (!KeyCommandManager.getInstance().inputEnabled()) return;
		
		final Direction d = Session.getInstance().getLocalPlayer().getCurrentDirection();
		
		EventManager.getInstance().checkEvents(
				GUI.getInstance().getCurrentMap(),
				new Point(
						Session.getInstance().getLocalPlayer().getLocation().x + d.getMovement().x,
						Session.getInstance().getLocalPlayer().getLocation().y + d.getMovement().y
						),
				EventType.INTERACT);
	}
}