package me.nrubin29.rpg.core.util;

import java.awt.Font;

import me.nrubin29.rpg.core.Main;

public class FontUtil {

	private static Font font;
	
	public static Font getFont() {
		if (font != null) return font;
		
		try {
            font = Font.createFont(Font.PLAIN, Main.class.getClassLoader().getResourceAsStream("res/font.otf"));
            font = font.deriveFont(Font.PLAIN, 12);
            return font;
        }
		catch (Exception e) { return null; }
	}
	
	public static Font getFont(int size) {
		return getFont().deriveFont(Font.PLAIN, size);
	}
}