package me.nrubin29.rpg.launcher;

import java.awt.Dimension;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogTab extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextArea log = new JTextArea();
	
	public LogTab() {
		log.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(log);
		scroll.setPreferredSize(new Dimension(610 + 160, 320));
		
		add(scroll);
	}
	
	public void write(String text) {
		log.append("[" + new Date() + "] " + text + "\n");
	}
}