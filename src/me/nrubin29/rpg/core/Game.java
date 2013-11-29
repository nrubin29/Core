package me.nrubin29.rpg.core;

import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.files.Keys;
import me.nrubin29.rpg.core.entity.Player;
import me.nrubin29.rpg.core.gui.ErrorGUI;
import me.nrubin29.rpg.core.gui.GUI;
import me.nrubin29.rpg.core.server.ServerConnector;
import me.nrubin29.rpg.core.server.Session;
import me.nrubin29.rpg.core.server.packet.handler.PacketHandlerManager;
import me.nrubin29.rpg.core.tile.TilesheetManager;
import me.nrubin29.rpg.core.util.Data;
import me.nrubin29.rpg.core.util.FontUtil;
import me.nrubin29.rpg.core.util.TimerUtil;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.lang.Thread.UncaughtExceptionHandler;

public abstract class Game extends JFrame {

	public abstract void onPreEnable();
	
	public abstract void onEnable();
	
	public abstract void onDisable();
	
	private static JFrame frame;
    private static GUI gui;

    public Game(String name, String version) {
        super(name);

    	Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			public void uncaughtException(Thread th, Throwable e) {
				e.printStackTrace(); //TODO: Temp
				
				if (frame != null) {
					frame.setVisible(false);
					frame.dispose();
				}
				
				new ErrorGUI(e);
			}
        });

        onPreEnable();

        Data.NAME = name;
        Data.VERSION = version;

        final JTextField playerName = new JTextField(15), secret = new JTextField(10);
        final JButton connect = new JButton("Connect"), local = new JButton("Play Local"), bindKeys = new JButton("Bind Keys");

        local.setAlignmentX(Component.CENTER_ALIGNMENT);
        local.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = playerName.getText();

                if (name.equals("")) {
                    JOptionPane.showMessageDialog(Game.this, "You didn't enter a name.");
                    return;
                }

                Session.getInstance().setLocalPlayer(new Player(name, 5 * Data.TILE_DIM, 5 * Data.TILE_DIM));

                DataManager.getInstance().setup();
                TilesheetManager.getInstance().setup();
                FontUtil.getFont();
                PacketHandlerManager.getInstance().setup();

                frame = new JFrame(Data.NAME + " v" + Data.VERSION + " (" + Data.ENGINE_NAME + " v" + Data.ENGINE_VERSION + ")");
                frame.setBackground(Data.BACKGROUND_COLOR);
                frame.setSize(Data.GAME_DIMENSION);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        onDisable();
                        System.exit(0);
                    }
                });

                gui = new GUI();

                Box box = new Box(BoxLayout.Y_AXIS);
                box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                box.setAlignmentY(JComponent.CENTER_ALIGNMENT);
                box.add(Box.createVerticalGlue());
                box.add(gui);
                box.add(Box.createVerticalGlue());
                frame.add(box);

                TimerUtil.runTimer(0, new Runnable() {
                    public void run() {
                        dispose();
                        frame.setVisible(true);
                        onEnable();
                    }
                });
            }
        });

        connect.setAlignmentX(Component.CENTER_ALIGNMENT);
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = playerName.getText();
                String secretString = secret.getText();

                if (name.equals("")) {
                    JOptionPane.showMessageDialog(Game.this, "You didn't enter a name.");
                    return;
                }

                if (secretString.equals("")) {
                    JOptionPane.showMessageDialog(Game.this, "You didn't enter a secret number.");
                    return;
                }

                int secretInt;

                try {
                    secretInt = Integer.parseInt(secretString);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(Game.this, "Invalid secret number.");
                    return;
                }

                if (!ServerConnector.getInstance().initConnection("127.0.0.1:" + secretInt)) return;

                local.doClick();
            }
        });

        bindKeys.setAlignmentX(Component.CENTER_ALIGNMENT);
        bindKeys.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DataManager.getInstance().setup();

                final Keys keys = DataManager.getInstance().getConfigurationFile(Keys.class);

                final JPanel menu = new JPanel();
                menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

                String[] strs = new String[] { "upID", "downID", "leftID", "rightID", "interactID" };

                for (final String key : strs) {
                    final JTextField set = new JTextField(String.valueOf(KeyStroke.getKeyStroke(Integer.parseInt(keys.getValue(key)), 0)), 15);
                    set.setEditable(false);

                    set.addKeyListener(new KeyAdapter() {
                        public void keyPressed(KeyEvent e) {
                            keys.set(key, e.getKeyCode());
                            set.setText(KeyStroke.getKeyStrokeForEvent(e).toString());
                        }
                    });

                    menu.add(createTitledInputPanel(key.toLowerCase().substring(0, key.length() - 2), set, Color.BLACK));
                }

                JOptionPane.showMessageDialog(null, menu);
            }
        });

        JPanel panel = new JPanel();
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(400, 400));
        panel.add(createTitledInputPanel("Name", playerName));
        panel.add(createTitledInputPanel("Secret Number", secret));
        panel.add(connect);
        panel.add(local);
        panel.add(bindKeys);

        Box box = new Box(BoxLayout.Y_AXIS);
        box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        box.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        box.add(Box.createVerticalGlue());
        box.add(panel);
        box.add(Box.createVerticalGlue());
        add(box);

        setSize(Data.GAME_DIMENSION);
        setBackground(Data.BACKGROUND_COLOR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static JFrame getFrame() {
    	return frame;
    }

    public static GUI getGUI() {
        return gui;
    }

    private JPanel createTitledInputPanel(String title, JTextField field, Color color) {
        JPanel panel = new JPanel();

        JLabel text = new JLabel(title.toLowerCase() + ":");
        text.setFont(FontUtil.getFont());
        text.setForeground(color);

        ((AbstractDocument) field.getDocument()).setDocumentFilter(new LowercaseDocumentFilter());
        field.setFont(FontUtil.getFont());

        panel.add(text);
        panel.add(field);

        return panel;
    }

    private JPanel createTitledInputPanel(String title, JTextField field) {
        return createTitledInputPanel(title, field, Data.FOREGROUND_COLOR_ON_BACKGROUND);
    }
}

class LowercaseDocumentFilter extends DocumentFilter {

    public void insertString(DocumentFilter.FilterBypass fb, int offset,
                             String text, AttributeSet attr) throws BadLocationException {

        fb.insertString(offset, text.toLowerCase(), attr);
    }

    public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
                        String text, AttributeSet attrs) throws BadLocationException {

        fb.replace(offset, length, text.toLowerCase(), attrs);
    }
}