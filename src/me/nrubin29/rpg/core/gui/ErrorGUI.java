package me.nrubin29.rpg.core.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import me.nrubin29.rpg.core.util.Data;

public class ErrorGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public ErrorGUI(Throwable e) {
		super("Error");
		
		JTextArea notice = new JTextArea(
				"An error has occurred and " + Data.NAME + " needs to stop. " +
				"The error is detailed below. " +
				"If you are a Java programmer, feel free to scoff at my childish mistake. " +
				"If you know me (the developer), feel free to send me an email with this error. " +
				"Otherwise, just restart the game and you should be good.\n"
				);
		notice.setEditable(false);
		notice.setFocusable(false);
		notice.setWrapStyleWord(true);
		notice.setLineWrap(true);
		notice.setBorder(null);
		notice.setForeground(Data.FOREGROUND_COLOR_ON_BACKGROUND);
		notice.setBackground(Data.BACKGROUND_COLOR);
		
		JTextArea errorArea = new JTextArea();
		errorArea.setEditable(false);
		errorArea.setForeground(Data.FOREGROUND_COLOR_ON_BACKGROUND);
		errorArea.setBackground(Data.BACKGROUND_COLOR);
		
		JTextArea click = new JTextArea("\nClick here to exit.");
		click.setEditable(false);
		click.setFocusable(false);
		click.setBorder(null);
		click.setForeground(Data.FOREGROUND_COLOR_ON_BACKGROUND);
		click.setBackground(Data.BACKGROUND_COLOR);
		click.setCursor(new Cursor(Cursor.HAND_CURSOR));
		click.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		errorArea.append("A(n) " + e.getClass().getSimpleName() + " has occurred.\n\nStack Trace:\n");
		for (StackTraceElement ste : e.getStackTrace()) errorArea.append(ste + "\n");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JScrollPane scroll = new JScrollPane(errorArea);
		scroll.setPreferredSize(new Dimension(Data.ERROR_POPUP_DIMENSION.width, Data.ERROR_POPUP_DIMENSION.height - 50));
		
		panel.add(notice);
		panel.add(scroll);
		panel.add(click);
		add(panel);
		
        setSize(Data.ERROR_POPUP_DIMENSION);
      	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
	}
}