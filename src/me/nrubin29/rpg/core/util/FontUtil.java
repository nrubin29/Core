package me.nrubin29.rpg.core.util;

import java.awt.Font;

import me.nrubin29.rpg.core.Game;

public class FontUtil {

	private static Font font;
	
	public static Font getFont() {
		if (font != null) return font;
		
		try {
            font = Font.createFont(Font.PLAIN, Game.class.getClassLoader().getResourceAsStream("res/font.ttf"));
            font = font.deriveFont(Font.PLAIN, 12);
            return font;
        }
		catch (Exception e) { return null; }
	}
	
	public static Font getFont(int size) {
		return getFont().deriveFont(Font.PLAIN, size);
	}
}