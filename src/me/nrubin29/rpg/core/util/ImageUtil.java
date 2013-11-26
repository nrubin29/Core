package me.nrubin29.rpg.core.util;

import me.nrubin29.rpg.core.Game;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageUtil {

    public static ImageIcon getImage(String imagePath) {
        try { return new ImageIcon(ImageIO.read(Game.class.getClassLoader().getResource("res/" + imagePath + ".png"))); }
        catch (Exception e) { return null; }
    }

	public static ImageIcon resizeImage(ImageIcon image, int width, int height) {
		return new ImageIcon(image.getImage().getScaledInstance(width, height, 0));
	}
}