package me.nrubin29.rpg.gui;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import me.nrubin29.rpg.Main;
import me.nrubin29.rpg.util.Constants;
import me.nrubin29.rpg.util.FontUtil;

public class Text extends TranslucentPanel {

	private static final long serialVersionUID = 1L;
	
	public static class TextFactory {
		 private ArrayList<Text> texts = new ArrayList<Text>();
		 private Runnable whenDone;
		 private int cursor;
		 
		 public TextFactory addText(String text) {
			 texts.add(createText(text, new Runnable() {
					public void run() {
						cursor++;
						if (texts.size() <= cursor) {
							if (whenDone != null) whenDone.run();
							return;
						}
						texts.get(cursor).add();
					}
				}));
			 return this;
		 }
		 
		 public TextFactory addCustom(JPanel panel) {
			 texts.add(new Text(panel, new Runnable() {
					public void run() {
						cursor++;
						if (texts.size() <= cursor) {
							if (whenDone != null) whenDone.run();
							return;
						}
						texts.get(cursor).add();
					}
				}));
			 return this;
		 }
		 
		 public void setWhenDone(Runnable whenDone) {
			 this.whenDone = whenDone;
		 }
		 
		 public void show() {
			 texts.get(0).add();
		 }
	}
	
	private static Text createText(String text, final Runnable whenDone) {
		JPanel panel = new JPanel();
		panel.setBackground(Constants.ON_TOP_OF_TRANSLUCENT);
		
		JLabel label = new JLabel(text);
		label.setFont(FontUtil.getFont().deriveFont(Font.PLAIN, 20));
		label.setForeground(Constants.FOREGROUND_COLOR_ON_BACKGROUND);
		
		panel.add(label);
		
		return new Text(panel, whenDone);
	}
	
	private Text(JPanel panel, final Runnable whenDone) {
		add(panel);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Main.getGUI().remove(Text.this);
				Main.getGUI().setInputEnabled(true);
				
				if (whenDone != null) whenDone.run();
			}
		});
		
		setBounds(new Rectangle(0, Constants.ACTUAL_DIMENSION.height - (Constants.TEXT_HEIGHT * 2), Constants.PANEL_DIMENSION.width, Constants.TEXT_HEIGHT));
	}
	
	public void add() {
		Main.getGUI().add(this, Constants.TEXT_LAYER);
		Main.getGUI().setInputEnabled(false);
	}
}