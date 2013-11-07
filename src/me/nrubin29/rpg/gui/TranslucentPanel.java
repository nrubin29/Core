package me.nrubin29.rpg.gui;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import me.nrubin29.rpg.util.Constants;

public class TranslucentPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public TranslucentPanel() {
		setOpaque(false);
	}
	
	@Override
    public void paintComponent(Graphics g) {
        g.setColor(Constants.TRANSLUCENT);
        Rectangle r = g.getClipBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        super.paintComponent(g);
    }
}