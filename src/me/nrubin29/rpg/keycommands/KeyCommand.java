package me.nrubin29.rpg.keycommands;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import me.nrubin29.rpg.Main;

public abstract class KeyCommand {

	public KeyCommand(int key, boolean alt, boolean control, boolean shift) {
		int keySum = 0;
		if (alt) keySum += InputEvent.ALT_DOWN_MASK;
		if (control) keySum += InputEvent.CTRL_DOWN_MASK;
		if (shift) keySum += InputEvent.SHIFT_DOWN_MASK;
		
		Main.getGUI().getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key, keySum), toString());
		Main.getGUI().getActionMap().put(toString(), new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
               run();
            }
        });
	}
	
	public abstract void run();
}