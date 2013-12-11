package me.nrubin29.rpg.util;

import java.awt.Font;

import me.nrubin29.rpg.Main;

public class FontUtil {

	private static Font font;
	
	public static Font getFont() {
		if (font != null) return font;
		
		try { font = Font.createFont(Font.PLAIN, Main.class.getClassLoader().getResourceAsStream("res/font.ttf")); return font; }
		catch (Exception e) { e.printStackTrace(); return null; } //TODO: Error Handling
	}
}