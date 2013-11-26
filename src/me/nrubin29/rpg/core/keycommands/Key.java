package me.nrubin29.rpg.core.keycommands;

import java.awt.event.InputEvent;

public class Key {

	private int key, keySum;
	
	public Key(int key, boolean alt, boolean control, boolean shift) {
		this.key = key;
		
		if (alt) keySum += InputEvent.ALT_DOWN_MASK;
		if (control) keySum += InputEvent.CTRL_DOWN_MASK;
		if (shift) keySum += InputEvent.SHIFT_DOWN_MASK;
	}
	
	public Key(String key, boolean alt, boolean control, boolean shift) {
		this(Integer.parseInt(key), alt, control, shift);
	}
	
	public int getKey() {
		return key;
	}
	
	public int getKeySum() {
		return keySum;
	}
}