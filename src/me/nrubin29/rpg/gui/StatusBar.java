package me.nrubin29.rpg.gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import me.nrubin29.rpg.misc.Icon;
import me.nrubin29.rpg.util.Constants;

public class StatusBar extends JPanel {

	private static final long serialVersionUID = 1L;

	public StatusBar() {
		for (int i = 0; i < Constants.MAX_HEALTH; i++) {
			JLabel heart = new JLabel(Icon.HEART.getImage(Constants.STATUSBAR_OBJECT_DIM, Constants.STATUSBAR_OBJECT_DIM));
			add(heart);
		}
		
		add(Box.createVerticalStrut(1));
		
		for (int i = 0; i < Constants.MAX_FOOD; i++) {
			JLabel food = new JLabel(Icon.FOOD.getImage(Constants.STATUSBAR_OBJECT_DIM, Constants.STATUSBAR_OBJECT_DIM));
			add(food);
		}
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setPreferredSize(Constants.STATUSBAR_DIMENSION);
		setMinimumSize(Constants.STATUSBAR_DIMENSION);
		setMaximumSize(Constants.STATUSBAR_DIMENSION);
		setBackground(Constants.BACKGROUND_COLOR);
		setFocusable(false);
	}
}