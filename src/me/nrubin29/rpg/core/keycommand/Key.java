package me.nrubin29.rpg.core.keycommand;

import java.awt.event.InputEvent;

public class Key {

	private int key, keySum;
	
	public Key(int key, boolean command, boolean alt, boolean shift) {
        this.key = key;

        if (command) {
            if (System.getProperty("os.name").startsWith("Mac")) keySum += InputEvent.META_DOWN_MASK;
            else keySum += InputEvent.CTRL_DOWN_MASK;
        }

		if (alt) keySum += InputEvent.ALT_DOWN_MASK;
		if (shift) keySum += InputEvent.SHIFT_DOWN_MASK;
	}
	
	public Key(String key, boolean command, boolean alt, boolean shift) {
		this(Integer.parseInt(key), command, alt, shift);
	}
	
	public int getKey() {
		return key;
	}
	
	public int getKeySum() {
		return keySum;
	}
}