package me.nrubin29.citrine;

import me.nrubin29.citrine.map.Map;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static final String VERSION = "0";

    private static GUI gui;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        gui = new GUI(frame);
        frame.add(gui);

        frame.setSize(new Dimension(320, 320));
        frame.setPreferredSize(new Dimension(320, 320));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setVisible(true);

        getGUI().renderMap(Map.SAMPLE); //TODO: Temp
    }

    public static GUI getGUI() {
        return gui;
    }
}