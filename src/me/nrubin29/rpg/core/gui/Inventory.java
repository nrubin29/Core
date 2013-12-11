package me.nrubin29.rpg.core.gui;

import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.files.PlayerData;
import me.nrubin29.rpg.core.item.Item;

import javax.swing.*;
import java.awt.*;

public class Inventory extends JPanel {

	private static final long serialVersionUID = 1L;

	public Inventory() {
        for (Item item : ((PlayerData) DataManager.getInstance().getConfigurationFile(PlayerData.class)).getInventory()) {
            JLabel icon = new JLabel(item.getImage(20, 20));
            icon.setToolTipText(item.getDescription());
            add(icon);
        }

        setLayout(new GridLayout(5, 5));
    }
}