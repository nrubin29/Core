package me.nrubin29.rpg.core.gui;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import me.nrubin29.rpg.core.util.Constants;
import me.nrubin29.rpg.core.util.ImageUtil;

public class ErrorPopup extends JFrame {

	private static final long serialVersionUID = 1L;

	public ErrorPopup(final Throwable e) {
		super("Error");
		
		JLabel player = new JLabel(ImageUtil.getImage("sprites/player/downStatic", 25, 25));
		
		JPanel playerPanel = new JPanel();
		playerPanel.add(player);
		
		JTextArea notice = new JTextArea(
				"Congratulations, beta tester, you have discovered a bug! Please follow the steps below to correctly fill out a bug report:\n\n" +
				"1. Click Submit Bug Report.\n" +
				"2. Pick a nice title for the bug report.\n" +
				"3. Click Copy Information to copy the information to your clipboard. Paste the info into the bug report.\n" +
				"4. Hit Submit. That's it! Thanks!"
//				"An error has occurred and " + Data.NAME + " needs to stop. " +
//				"The error is detailed below. " +
//				"If you are a Java programmer, feel free to scoff at my childish mistake. " +
//				"If you know me (the developer), feel free to send me an email with this error. " +
//				"Otherwise, just restart the game and you should be good.\n"
				);
		notice.setEditable(false);
		notice.setFocusable(false);
		notice.setWrapStyleWord(true);
		notice.setLineWrap(true);
		notice.setBorder(null);
		notice.setBackground(getBackground());
		
		JLabel newReport = new JLabel("Submit Bug Report");
		newReport.setCursor(new Cursor(Cursor.HAND_CURSOR));
		newReport.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try { Desktop.getDesktop().browse(new URI("https://github.com/nrubin29/RPG-Core/issues/new"));}
				catch (Exception ex) { }
			}
		});
		
		JLabel copyInfo = new JLabel("Copy Information");
		copyInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		copyInfo.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent ev) {
				StringBuffer infoBuffer = new StringBuffer();
				
				infoBuffer.append("Error Type: " + e.getClass().getSimpleName() + "\n");
				infoBuffer.append("OS: " + System.getProperty("os.name") + "\n");
				infoBuffer.append("Java Version: " + System.getProperty("java.version") + "\n\n");
				for (StackTraceElement ste : e.getStackTrace()) infoBuffer.append(ste + "\n");
				
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(infoBuffer.toString()), null);
			}
		});
		
		JLabel click = new JLabel("Click here to exit.");
		click.setCursor(new Cursor(Cursor.HAND_CURSOR));
		click.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent ev) {
				System.exit(0);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(playerPanel);
		panel.add(notice);
		panel.add(newReport);
		panel.add(copyInfo);
		panel.add(click);
		add(panel);
		
        setSize(Constants.ERROR_DIMENSION);
      	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
	}
}