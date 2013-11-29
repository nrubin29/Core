package me.nrubin29.rpg.server;

import java.awt.*;
import java.net.InetAddress;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	private DefaultListModel playersList = new DefaultListModel();
	private JList players = new JList(playersList);
	private JTextArea logs = new JTextArea();
	
	private static final long serialVersionUID = 1L;

	public GUI(int port) {
		super("RPG Core Server");
		
		JLabel info = null;
		
		try {
			info = new JLabel(
				"<html>" +
				"Server Address: " + InetAddress.getLocalHost().getHostAddress() + "<br/>" +
                "Secret Number: " + port
			);
		}
		catch (Exception e) { e.printStackTrace(); }
		
		info.setAlignmentX(Component.LEFT_ALIGNMENT);
		info.setHorizontalAlignment(SwingConstants.LEFT);
        info.setBorder(BorderFactory.createTitledBorder("Server Info"));

		logs.setEditable(false);

        JScrollPane playersScroll = new JScrollPane(players);
        playersScroll.setBorder(BorderFactory.createTitledBorder("Players"));

        JScrollPane logsScroll = new JScrollPane(logs);
        logsScroll.setBorder(BorderFactory.createTitledBorder("Log"));

		JPanel leftPane = new JPanel();
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));
		leftPane.setMaximumSize(new Dimension(100, 480));
		
		leftPane.add(info);
		leftPane.add(playersScroll);
		
		add(leftPane);
		add(logsScroll);
		
		setSize(1000, 480);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public void addPlayer(Client client) {
		playersList.addElement(client.getUserName());
		players.validate();
	}
	
	public void removePlayer(Client client) {
		playersList.removeElement(client.getUserName());
		players.validate();
	}
	
	public void write(String str) {
		logs.append("[" + new Date() + "] " + str + "\n");
	}
}