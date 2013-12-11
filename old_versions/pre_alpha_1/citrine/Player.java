package me.nrubin29.citrine;

import me.nrubin29.citrine.util.ImageUtil;

import javax.swing.*;

public class Player {

    private Player() { }

    private static Player instance = new Player();

    public static Player getInstance() {
        return instance;
    }

    private JLabel label = new JLabel(ImageUtil.getImage("sprites/player/left_static"));

    public JLabel getLabel() {
        return label;
    }
}