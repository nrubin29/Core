package me.nrubin29.rpg.core.keycommand;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import me.nrubin29.rpg.core.gui.GUI;

public abstract class KeyCommand {

	public KeyCommand(final Key... keys) {
		for (int i = 0; i < keys.length; i++) {
			final Key key = keys[i];
			
			GUI.getInstance().getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key.getKey(), key.getKeySum()), key.getKey());
			GUI.getInstance().getActionMap().put(key.getKey(), new AbstractAction() {
				private static final long serialVersionUID = 1L;

				@Override
	            public void actionPerformed(ActionEvent e) {
					run(key);
	            }
	        });
		}
	}
	
	public abstract void run(Key key);
}