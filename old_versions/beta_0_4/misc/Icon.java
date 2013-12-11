package me.nrubin29.rpg.misc;

import javax.swing.ImageIcon;

import me.nrubin29.rpg.util.ImageUtil;

public enum Icon implements Image {

	HEART,
	FOOD, 
	TESTPERSON;

	private ImageIcon image;
	
	Icon() {
        image = ImageUtil.getImage("images/" + name().toLowerCase());
    }
	
	public ImageIcon getImage() {
		return image;
	}
	
	public ImageIcon getImage(int width, int height) {
		return ImageUtil.resizeImage(getImage(), width, height);
	}
}