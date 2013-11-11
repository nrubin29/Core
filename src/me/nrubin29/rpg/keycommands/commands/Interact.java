package me.nrubin29.rpg.keycommands.commands;

import java.awt.Point;
import java.awt.event.KeyEvent;

import me.nrubin29.rpg.Main;
import me.nrubin29.rpg.events.Event.EventType;
import me.nrubin29.rpg.events.EventManager;
import me.nrubin29.rpg.gui.GUI;
import me.nrubin29.rpg.keycommands.KeyCommand;
import me.nrubin29.rpg.util.MapTileUtil.Direction;

public class Interact extends KeyCommand {

	public Interact() { super(KeyEvent.VK_ENTER, false, false, false); }
	
	public void run() {
		GUI gui = Main.getGUI();
		
		if (!gui.isInputEnabled()) return;
		
		final Direction d = gui.getPlayer().getCurrentDirection();
		
		EventManager.getInstance().checkEvents(
				gui.getCurrentMap(),
				new Point(
						gui.getPlayerLabel().getX() + d.getMovement().x,
						gui.getPlayerLabel().getY() + d.getMovement().y
						),
				EventType.INTERACT);
	}
}