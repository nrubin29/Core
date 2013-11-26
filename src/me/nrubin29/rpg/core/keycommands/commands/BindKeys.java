package me.nrubin29.rpg.core.keycommands.commands;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.files.Keys;
import me.nrubin29.rpg.core.gui.Popup.PopupFactory;
import me.nrubin29.rpg.core.keycommands.Key;
import me.nrubin29.rpg.core.keycommands.KeyCommand;
import me.nrubin29.rpg.core.util.Data;
import me.nrubin29.rpg.core.util.FontUtil;

public class BindKeys extends KeyCommand {

	public BindKeys() {
		super(
				new Key(KeyEvent.VK_K, false, true, false)
				);
	}

	public void run(Key k) {
		final Keys keys = DataManager.getInstance().getConfigurationFile(Keys.class);
		
		final JPanel menu = new JPanel();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		
		String[] strs = new String[] { "upID", "downID", "leftID", "rightID", "interactID" };
		
		for (final String key : strs) {
			JPanel line = new JPanel();
			
			JLabel label = new JLabel(key.toLowerCase().substring(0, key.length() - 2) + ":");
			label.setFont(FontUtil.getFont(15));
			label.setForeground(Data.FOREGROUND_COLOR_ON_BACKGROUND);
			
			final JTextField set = new JTextField(String.valueOf(KeyStroke.getKeyStroke(Integer.parseInt(keys.getValue(key)), 0)), 15);
			set.setEditable(false);
			
			set.addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent e) {
							keys.set(key, KeyStroke.getKeyStroke(e.getKeyChar()).getKeyCode());
							set.setText(KeyStroke.getKeyStrokeForEvent(e).toString());
						}
					});
			
			line.add(label); line.add(set);
			menu.add(line);
		}
		
		new PopupFactory().addCustom(null, new JScrollPane(menu)).show();
	}
}