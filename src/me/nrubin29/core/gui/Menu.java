package me.nrubin29.core.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import me.nrubin29.core.data.DataManager;
import me.nrubin29.core.data.LocalizationManager;
import me.nrubin29.core.map.MapManager;
import me.nrubin29.core.util.Constants;
import me.nrubin29.core.util.ResourceUtil;
import me.nrubin29.core.util.ThreadUtil;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;

	public Menu() {
		super(Constants.NAME);
		
		final JTextField ip = new JTextField("Multiplayer Disabled", 14), secret = new JTextField("Multiplayer Disabled", 12);
        final JButton connect = new JButton("Connect"), local = new JButton("Play Local"), mapMaker = new JButton("Map Maker"), settings = new JButton("Settings");

        /*
         * Only for beta!
         */
        ip.setEditable(false);
        secret.setEditable(false);
        /* 
         * Only for beta!
         */
        
        local.setAlignmentX(Component.CENTER_ALIGNMENT);
        local.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	JPanel listPanel = new JPanel();
            	DefaultListModel listModel = new DefaultListModel();
            	JList list = new JList(listModel);
            	
            	for (File f : DataManager.getInstance().getFolder("maps", true).listFiles()) {
            		if (f.getName().endsWith(".map")) listModel.addElement(f.getName().substring(0, f.getName().indexOf(".")));
            	}
            	
            	if (listModel.isEmpty()) {
            		JOptionPane.showMessageDialog(Menu.this, "You don't have any maps. Make one in the Map Maker.");
            		return;
            	}
            	
            	listPanel.add(list);
            	
            	JOptionPane.showMessageDialog(Menu.this, listPanel);
            	
            	if (list.getSelectedValue() == null) return;
            	
            	String file = list.getSelectedValue().toString();
            	
            	File f = DataManager.getInstance().getFile("maps", file + ".map", false);
            	
            	MapManager.getInstance().createMap(f);
            	
            	setupGame();
            	
            	MapManager.getInstance().getMap(file).display();
            }
        });

        connect.setAlignmentX(Component.CENTER_ALIGNMENT);
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	JOptionPane.showMessageDialog(Menu.this, "Multiplayer Disabled.");
            	
//                String ipString = ip.getText(), secretString = secret.getText();
//
//                if (ipString.equals("")) {
//                    JOptionPane.showMessageDialog(Menu.this, "You didn't enter an IP address.");
//                    return;
//                }
//
//                if (secretString.equals("")) {
//                    JOptionPane.showMessageDialog(Menu.this, "You didn't enter a secret number.");
//                    return;
//                }
//
//                int secretInt;
//
//                try {
//                    secretInt = Integer.parseInt(secretString);
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(Menu.this, "Invalid secret number.");
//                    return;
//                }
//
//                if (!ServerConnector.getInstance().initConnection(ipString, secretInt)) return;
//
//                setupGame();
            }
        });
        
        mapMaker.setAlignmentX(Component.CENTER_ALIGNMENT);
        mapMaker.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new MapMaker();
        	}
        });

        settings.setAlignmentX(Component.CENTER_ALIGNMENT);
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(Menu.this, new SettingsPanel());
            }
        });
        
        JPanel logoPanel = new JPanel();
        logoPanel.add(new JLabel(ResourceUtil.getImage("logo")));

        JPanel panel = new JPanel();
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(400, 400));
        panel.add(logoPanel);
        JLabel info = new JLabel(Constants.NAME + " v" + Constants.VERSION + " by " + Constants.AUTHOR); info.setAlignmentX(Component.CENTER_ALIGNMENT); panel.add(info);
        panel.add(createTitledPanel(LocalizationManager.getInstance().getString("server"), ip));
        panel.add(createTitledPanel(LocalizationManager.getInstance().getString("secret"), secret));
        panel.add(createTitledPanel(null, connect));
        panel.add(createTitledPanel(null, local));
        panel.add(createTitledPanel(null, mapMaker));
        panel.add(createTitledPanel(null, settings));
        
        Box box = new Box(BoxLayout.Y_AXIS);
        box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        box.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        box.add(Box.createVerticalGlue());
        box.add(panel);
        box.add(Box.createVerticalGlue());
        add(box);
        
        setSize(Constants.GAME_DIMENSION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	private void setupGame() {
        GUI.getInstance().create();
        
        ThreadUtil.runTimer(0, new Runnable() {
        	public void run() {
        		dispose();
        		GUI.getInstance().getFrame().setVisible(true);
        	}
        });
	}
	
	public static JPanel createTitledPanel(String title, JComponent component) {
        JPanel panel = new JPanel();

        if (title != null) {
        	JLabel text = new JLabel(title.toLowerCase() + ":");
        	panel.add(text);
        }
        
        panel.add(component);

        return panel;
    }
}