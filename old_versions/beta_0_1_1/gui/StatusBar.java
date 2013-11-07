package me.nrubin29.rpg.gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import me.nrubin29.rpg.util.Constants;
import me.nrubin29.rpg.util.ImageUtil;

public class StatusBar extends JPanel {

	private static final long serialVersionUID = 1L;

	public StatusBar() {
		for (int i = 0; i < Constants.MAX_HEALTH; i++) {
			JLabel heart = new JLabel(Image.HEART.getImage());
			add(heart);
		}
		
		add(Box.createVerticalStrut(1));
		
		for (int i = 0; i < Constants.MAX_FOOD; i++) {
			JLabel food = new JLabel(Image.FOOD.getImage());
			add(food);
		}
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setPreferredSize(Constants.STATUSBAR_DIMENSION);
		setBackground(Constants.BACKGROUND_COLOR);
	}
}

enum Image {
	
	HEART,
	FOOD;

	private ImageIcon image;
	
	Image() {
        image = ImageUtil.getImage("images/" + name().toLowerCase(), Constants.STATUSBAR_OBJECT_DIM, Constants.STATUSBAR_OBJECT_DIM);
    }
	
	public ImageIcon getImage() {
		return image;
	}
}