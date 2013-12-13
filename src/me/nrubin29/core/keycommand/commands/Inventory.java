package me.nrubin29.core.keycommand.commands;

import java.awt.event.KeyEvent;

import me.nrubin29.core.gui.InventoryGUI;
import me.nrubin29.core.gui.Popup;
import me.nrubin29.core.keycommand.Key;
import me.nrubin29.core.keycommand.KeyCommand;

public class Inventory extends KeyCommand {

    public Inventory() {
        super(
                new Key(KeyEvent.VK_I, false, false, false)
        );
    }

    public void run(Key key) {
        new Popup.PopupFactory().addCustom(null, new InventoryGUI()).show();
    }
}