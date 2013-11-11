package me.nrubin29.rpg.gui;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import me.nrubin29.rpg.util.Constants;
import me.nrubin29.rpg.util.FontUtil;

public class SplashScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	public SplashScreen() {
		setLayout(new GridBagLayout());
		
		JLabel title = new JLabel("loading " + Constants.NAME.toLowerCase());
		title.setFont(FontUtil.getFont(50));
		title.setForeground(Color.WHITE);
		
		JLabel name = new JLabel("programmed by noah rubin - graphics by nathan sinai\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tv" + Constants.VERSION);
		name.setFont(FontUtil.getFont(12));
		name.setForeground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.add(title);
		panel.add(name);
		add(panel);
		
        setSize(Constants.SPLASH_SCREEN_DIMENSION);
        setPreferredSize(Constants.SPLASH_SCREEN_DIMENSION);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setBackground(Constants.TRANSLUCENT);
        pack();
        setVisible(true);
	}
}