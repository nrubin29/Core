package me.nrubin29.rpg.core.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.KeyStroke;

import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.LocalizationManager;
import me.nrubin29.rpg.core.data.files.Keys;
import me.nrubin29.rpg.core.map.MapManager;
import me.nrubin29.rpg.core.server.ServerConnector;
import me.nrubin29.rpg.core.util.Constants;
import me.nrubin29.rpg.core.util.ImageUtil;
import me.nrubin29.rpg.core.util.ThreadUtil;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;

	public Menu() {
		super(Constants.NAME);
		
		final JTextField ip = new JTextField(14), secret = new JTextField(10);
        final JButton connect = new JButton("Connect"), local = new JButton("Play Local"), mapMaker = new JButton("Map Maker"), bindKeys = new JButton("Bind Keys");

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
            	
            	listPanel.add(list);
            	
            	JOptionPane.showMessageDialog(Menu.this, listPanel);
            	
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
                String ipString = ip.getText(), secretString = secret.getText();

                if (ipString.equals("")) {
                    JOptionPane.showMessageDialog(Menu.this, "You didn't enter an IP address.");
                    return;
                }

                if (secretString.equals("")) {
                    JOptionPane.showMessageDialog(Menu.this, "You didn't enter a secret number.");
                    return;
                }

                int secretInt;

                try {
                    secretInt = Integer.parseInt(secretString);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(Menu.this, "Invalid secret number.");
                    return;
                }

                if (!ServerConnector.getInstance().initConnection(ipString, secretInt)) return;

                setupGame();
            }
        });
        
        mapMaker.setAlignmentX(Component.CENTER_ALIGNMENT);
        mapMaker.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new MapMaker();
        	}
        });

        bindKeys.setAlignmentX(Component.CENTER_ALIGNMENT);
        bindKeys.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataManager.getInstance().setup();

                final Keys keys = DataManager.getInstance().getConfigurationFile(Keys.class);

                final JPanel menu = new JPanel();
                menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

                String[] strs = new String[] { "upID", "downID", "leftID", "rightID", "interactID" };

                for (final String key : strs) {
                    final JTextField set = new JTextField(String.valueOf(KeyStroke.getKeyStroke(Integer.parseInt(keys.getValue(key)), 0)).toLowerCase(), 15);
                    set.setEditable(false);

                    set.addKeyListener(new KeyAdapter() {
                        public void keyPressed(KeyEvent e) {
                            keys.set(key, e.getKeyCode());
                            set.setText(KeyStroke.getKeyStrokeForEvent(e).toString());
                        }
                    });

                    menu.add(createTitledPanel(key.toLowerCase().substring(0, key.length() - 2), set));
                }

                JOptionPane.showMessageDialog(null, menu);
            }
        });
        
        JPanel logoPanel = new JPanel();
        logoPanel.add(new JLabel(ImageUtil.getImage("logo")));

        JPanel panel = new JPanel();
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(400, 400));
        panel.add(logoPanel);
        panel.add(createTitledPanel(LocalizationManager.getInstance().getString("server"), ip));
        panel.add(createTitledPanel(LocalizationManager.getInstance().getString("secret"), secret));
        panel.add(createTitledPanel(null, connect));
        panel.add(createTitledPanel(null, local));
        panel.add(createTitledPanel(null, mapMaker));
        panel.add(createTitledPanel(null, bindKeys));
        
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