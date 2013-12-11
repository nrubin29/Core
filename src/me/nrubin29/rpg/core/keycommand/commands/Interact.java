package me.nrubin29.rpg.core.keycommand.commands;

import java.awt.Point;

import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.files.Keys;
import me.nrubin29.rpg.core.event.Event.EventType;
import me.nrubin29.rpg.core.event.EventManager;
import me.nrubin29.rpg.core.gui.GUI;
import me.nrubin29.rpg.core.keycommand.Key;
import me.nrubin29.rpg.core.keycommand.KeyCommand;
import me.nrubin29.rpg.core.keycommand.KeyCommandManager;
import me.nrubin29.rpg.core.map.Direction;
import me.nrubin29.rpg.core.server.Session;

public class Interact extends KeyCommand {

	public Interact() {
		super(
				new Key(DataManager.getInstance().getConfigurationFile(Keys.class).getValue("interactID"), false, false, false)
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