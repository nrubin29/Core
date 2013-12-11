package me.nrubin29.rpg.launcher;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextArea log = new JTextArea();
	
	public LogWindow() {
		super("Log");
		
		log.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(log);
		scroll.setPreferredSize(new Dimension(770, 320));
		
		add(scroll);
		
		setSize(new Dimension(640, 480));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void write(String text) {
		log.append("[" + new Date() + "] " + text + "\n");
	}
	
	public void setProcess(Process process) {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		final BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		
		new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						String line = errorReader.readLine();
						if (line != null) write(line);
					}
				}
				catch (Exception e) { }
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						String line = reader.readLine();
						if (line != null) write(line);
					}
				}
				catch (Exception e) { }
			}
		}).start();
	}
}