package me.nrubin29.core.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import me.nrubin29.core.item.Inventory;
import me.nrubin29.core.item.Item;

public class InventoryGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public InventoryGUI() {
        for (Item item : Inventory.getInstance().getItems()) {
            JLabel icon = new JLabel(item.getImage(20, 20));
            icon.setToolTipText(item.getDescription());
            add(icon);
        }

        setLayout(new GridLayout(5, 5));
    }
}