package me.nrubin29.rpg.util;

import me.nrubin29.rpg.Main;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageUtil {

    public static ImageIcon getImage(String imagePath) {
        try { return new ImageIcon(ImageIO.read(Main.class.getClassLoader().getResource("res/" + imagePath + ".png"))); }
        catch (Exception e) { e.printStackTrace(); /* TODO: Handle Errors */ return null; }
    }

    public static ImageIcon getImage(String imagePath, int width, int height) {
        return new ImageIcon(getImage(imagePath).getImage().getScaledInstance(width, height, 0));
    }
}