package me.nrubin29.core.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import me.nrubin29.core.data.DataManager;
import me.nrubin29.core.data.LocalizationManager;
import me.nrubin29.core.data.LocalizationManager.Language;
import me.nrubin29.core.data.files.Settings;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public SettingsPanel() {
		JComboBox lang = new JComboBox(Language.values());
		JButton bindKeys = new JButton("Bind Keys"), reset = new JButton("Reset Game");
		
		lang.setAlignmentX(Component.CENTER_ALIGNMENT);
		lang.setSelectedItem(LocalizationManager.getInstance().getCurrentLanguage());
		lang.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				DataManager.getInstance().<Settings>getConfigurationFile(Settings.class).set("language", e.getItem());
			}
		});

        bindKeys.setAlignmentX(Component.CENTER_ALIGNMENT);
        bindKeys.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataManager.getInstance().setup();

                final Settings keys = DataManager.getInstance().getConfigurationFile(Settings.class);

                final JPanel menu = new JPanel();
                menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

                String[] strs = new String[] { "upID", "downID", "leftID", "rightID", "interactID" };

                for (final String key : strs) {
                    final JTextField set = new JTextField(String.valueOf(KeyStroke.getKeyStroke(Integer.parseInt(keys.get(key)), 0)).toLowerCase(), 15);
                    set.setEditable(false);

                    set.addKeyListener(new KeyAdapter() {
                        public void keyPressed(KeyEvent e) {
                            keys.set(key, e.getKeyCode());
                            set.setText(KeyStroke.getKeyStrokeForEvent(e).toString());
                        }
                    });

                    menu.add(Menu.createTitledPanel(key.toLowerCase().substring(0, key.length() - 2), set));
                }

                JOptionPane.showMessageDialog(null, menu);
            }
        });
        
        reset.setAlignmentX(Component.CENTER_ALIGNMENT);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(SettingsPanel.this, "Are you sure you want to reset the game? This will delete EVERYTHING.") == JOptionPane.YES_OPTION) {
                	File root = DataManager.getInstance().getRootFolder();
                	deleteAllFiles(root);
                	
                	JOptionPane.showMessageDialog(SettingsPanel.this, "Done resetting. Quitting. Please open the launcher to get a fresh copy of the game.");
                	System.exit(0);
                }
            }
        });
        
        add(Menu.createTitledPanel(null, lang));
        add(Menu.createTitledPanel(null, bindKeys));
        add(Menu.createTitledPanel(null, reset));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);
	}
	
	private void deleteAllFiles(File root) {
		for (File sub : root.listFiles()) {
			if (sub.isDirectory()) deleteAllFiles(sub);
			else sub.delete();
		}
		
		root.delete();
	}
}