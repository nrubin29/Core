package me.nrubin29.rpg.core.util;

import me.nrubin29.rpg.core.Main;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageUtil {

    public static ImageIcon getImage(String imagePath) {
        try { return new ImageIcon(ImageIO.read(Main.class.getClassLoader().getResource("res/" + imagePath + ".png"))); }
        catch (Exception e) { return null; }
    }
    
    public static ImageIcon getImage(String imagePath, int width, int height) {
    	return resizeImage(getImage(imagePath), width, height);
    }
    
    public static ImageIcon getImageScaled(String imagePath, int widthX, int heightX) {
    	ImageIcon icon = getImage(imagePath);
    	return resizeImage(icon, icon.getIconWidth() / widthX, icon.getIconHeight() / heightX);
    }

	public static ImageIcon resizeImage(ImageIcon image, int width, int height) {
		return new ImageIcon(image.getImage().getScaledInstance(width, height, 0));
	}
	
	public static ImageIcon resizeImageScaled(ImageIcon image, int widthX, int heightX) {
		return resizeImage(image, image.getIconWidth() / widthX, image.getIconHeight() / heightX);
	}
}