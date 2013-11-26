package me.nrubin29.rpg.core.keycommands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import me.nrubin29.rpg.core.Game;

public abstract class KeyCommand {

	public KeyCommand(final Key... keys) {
		for (int i = 0; i < keys.length; i++) {
			final Key key = keys[i];
			
			Game.getGUI().getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key.getKey(), key.getKeySum()), toString() + i);
			Game.getGUI().getActionMap().put(toString() + i, new AbstractAction() {
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