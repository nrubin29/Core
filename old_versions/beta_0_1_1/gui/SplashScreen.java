package me.nrubin29.rpg.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
		
		JLabel title = new JLabel("loading rpg tech demo");
		title.setFont(FontUtil.getFont().deriveFont(Font.PLAIN, 50));
		title.setForeground(Color.WHITE);
		
		JLabel name = new JLabel("created by noah rubin\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tv" + Constants.VERSION);
		name.setFont(FontUtil.getFont().deriveFont(Font.PLAIN, 12));
		name.setForeground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.add(title);
		panel.add(name);
		add(panel);
		
		Dimension DIM = new Dimension(700, 100);
		setPreferredSize(DIM);
        setSize(DIM);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 50));
        pack();
        setVisible(true);
	}
}