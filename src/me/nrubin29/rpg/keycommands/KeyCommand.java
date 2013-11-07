package me.nrubin29.rpg.keycommands;

import java.awt.event.KeyEvent;

public abstract class KeyCommand {

	private final int key;
	private final boolean alt, control, shift;
	
	public KeyCommand(int key, boolean alt, boolean control, boolean shift) {
		this.key = key;
		this.alt = alt;
		this.control = control;
		this.shift = shift;
	}
	
	public final boolean shouldRun(KeyEvent e) {
		return (e.getKeyCode() == key && e.isAltDown() == alt && e.isControlDown() == control && e.isShiftDown() == shift);
	}
	
	public abstract void run();
}