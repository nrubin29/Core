package me.nrubin29.rpg.core.keycommands.commands;

import java.awt.Point;

import me.nrubin29.rpg.core.Game;
import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.files.Keys;
import me.nrubin29.rpg.core.events.EventManager;
import me.nrubin29.rpg.core.events.Event.EventType;
import me.nrubin29.rpg.core.gui.GUI;
import me.nrubin29.rpg.core.keycommands.Key;
import me.nrubin29.rpg.core.keycommands.KeyCommand;
import me.nrubin29.rpg.core.server.Session;
import me.nrubin29.rpg.core.util.MapTileUtil.Direction;

public class Interact extends KeyCommand {

	public Interact() {
		super(
				new Key(DataManager.getInstance().getConfigurationFile(Keys.class).getValue("interactID"), false, false, false)
				);
	}
	
	public void run(Key key) {
		GUI gui = Game.getGUI();
		
		if (!gui.isInputEnabled()) return;
		
		final Direction d = Session.getInstance().getLocalPlayer().getCurrentDirection();
		
		EventManager.getInstance().checkEvents(
				gui.getCurrentMap(),
				new Point(
						gui.getPlayerLabel(Session.getInstance().getLocalPlayer()).getX() + d.getMovement().x,
						gui.getPlayerLabel(Session.getInstance().getLocalPlayer()).getY() + d.getMovement().y
						),
				EventType.INTERACT);
	}
}