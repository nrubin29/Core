package me.nrubin29.rpg.core.keycommand.commands;

import java.awt.event.KeyEvent;

import me.nrubin29.rpg.core.gui.Popup;
import me.nrubin29.rpg.core.keycommand.Key;
import me.nrubin29.rpg.core.keycommand.KeyCommand;

public class Inventory extends KeyCommand {

    public Inventory() {
        super(
                new Key(KeyEvent.VK_I, false, false, false)
        );
    }

    public void run(Key key) {
        new Popup.PopupFactory().addCustom(null, new me.nrubin29.rpg.core.gui.Inventory()).show();
    }
}