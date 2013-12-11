package me.nrubin29.rpg.keycommands.commands;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;

import me.nrubin29.rpg.Main;
import me.nrubin29.rpg.keycommands.KeyCommand;

public class Fullscreen extends KeyCommand {

	public Fullscreen() { super(KeyEvent.VK_F, false, true, false); }
	
	private boolean fullscreen = false;
	
	public void run() {
		Main.getFrame().dispose();
		Main.getFrame().setUndecorated(!Main.getFrame().isUndecorated());
		Main.getFrame().setVisible(true);
		
		GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		
		if (!fullscreen) screen.setFullScreenWindow(Main.getFrame());
		else screen.setFullScreenWindow(null);
		
		fullscreen = !fullscreen;
	}
}