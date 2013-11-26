package me.nrubin29.rpg.core.keycommands.commands;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;

import me.nrubin29.rpg.core.Game;
import me.nrubin29.rpg.core.keycommands.Key;
import me.nrubin29.rpg.core.keycommands.KeyCommand;

public class Fullscreen extends KeyCommand {

	public Fullscreen() {
		super(
				new Key(KeyEvent.VK_F, false, true, false)
				);
	}
	
	private boolean fullscreen = false;
	
	public void run(Key key) {
		Game.getFrame().dispose();
		Game.getFrame().setUndecorated(!Game.getFrame().isUndecorated());
		Game.getFrame().setVisible(true);
		
		GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		
		if (!fullscreen) screen.setFullScreenWindow(Game.getFrame());
		else screen.setFullScreenWindow(null);
		
		fullscreen = !fullscreen;
	}
}