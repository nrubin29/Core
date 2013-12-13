package me.nrubin29.core.keycommand.commands;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;

import me.nrubin29.core.gui.GUI;
import me.nrubin29.core.keycommand.Key;
import me.nrubin29.core.keycommand.KeyCommand;

public class Fullscreen extends KeyCommand {

	public Fullscreen() {
		super(
				new Key(KeyEvent.VK_F, true, false, false)
				);
	}
	
	private boolean fullscreen = false;
	
	public void run(Key key) {
		GUI.getInstance().getFrame().dispose();
		GUI.getInstance().getFrame().setUndecorated(!GUI.getInstance().getFrame().isUndecorated());
		GUI.getInstance().getFrame().setVisible(true);
		
		GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		
		if (!fullscreen) screen.setFullScreenWindow(GUI.getInstance().getFrame());
		else screen.setFullScreenWindow(null);
		
		fullscreen = !fullscreen;
	}
}