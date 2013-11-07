package me.nrubin29.rpg.misc;

import javax.swing.ImageIcon;

import me.nrubin29.rpg.util.ImageUtil;

public enum Image {

	HEART,
	FOOD, 
	TESTPERSON;

	private ImageIcon image;
	
	Image() {
        image = ImageUtil.getImage("images/" + name().toLowerCase());
    }
	
	public ImageIcon getImage(int width, int height) {
		return ImageUtil.resizeImage(image, width, height);
	}
}