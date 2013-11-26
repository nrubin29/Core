package me.nrubin29.rpg.core.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import me.nrubin29.rpg.core.Game;
import me.nrubin29.rpg.core.misc.Image;
import me.nrubin29.rpg.core.util.Data;
import me.nrubin29.rpg.core.util.FontUtil;

public class Popup extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static class PopupFactory {
		 private ArrayList<Popup> popups = new ArrayList<Popup>();
		 private Runnable whenDone;
		 private int cursor;
		 
		 public PopupFactory addPopup(Image image, String text) {
			JPanel panel = new JPanel();
			panel.setFocusable(false);
			panel.setBackground(Data.BACKGROUND_COLOR);
			panel.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			JTextPane textArea = new JTextPane();
			textArea.setText(text);
			textArea.setEditable(false);
			textArea.setFocusable(false);
			textArea.setFont(FontUtil.getFont(14));
			textArea.setForeground(Data.FOREGROUND_COLOR_ON_BACKGROUND);
			textArea.setBackground(Data.BACKGROUND_COLOR);
			textArea.setPreferredSize(new Dimension(Data.POPUP_DIMENSION.width, 40));
			textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			StyledDocument doc = textArea.getStyledDocument();
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, doc.getLength(), center, false);
				
			panel.add(textArea);
			 
			 popups.add(new Popup(image, panel, new Runnable() {
					public void run() {
						cursor++;
						if (popups.size() <= cursor) {
							if (whenDone != null) whenDone.run();
							return;
						}
						popups.get(cursor).add();
					}
				}));
			 return this;
		 }
		 
		 public PopupFactory addCustom(Image image, JComponent comp) {
			 popups.add(new Popup(image, comp, new Runnable() {
					public void run() {
						cursor++;
						if (popups.size() <= cursor) {
							if (whenDone != null) whenDone.run();
							return;
						}
						popups.get(cursor).add();
					}
				}));
			 return this;
		 }
		 
		 public void setWhenDone(Runnable whenDone) {
			 this.whenDone = whenDone;
		 }
		 
		 public void show() {
			 popups.get(0).add();
		 }
	}
	
	private Popup(Image image, JComponent comp, final Runnable whenDone) {
		if (image != null) {
			JLabel img = new JLabel(image.getImage(75, 60));
			img.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			img.setAlignmentX(Component.CENTER_ALIGNMENT);
			img.setFocusable(false);
			add(img);
		}
		
		JLabel x = new JLabel("X");
		x.setForeground(Color.RED);
		x.setCursor(new Cursor(Cursor.HAND_CURSOR));
		x.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Popup.this.setVisible(false);
				Game.getGUI().remove(Popup.this);
				Game.getGUI().setInputEnabled(true);
				
				if (whenDone != null) whenDone.run();
			}
		});
		
		add(comp);
		add(x);
		
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		setBackground(Data.BACKGROUND_COLOR);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBounds(new Rectangle(
				(Data.DIMENSION.width / 2) - (Data.POPUP_DIMENSION.width / 2),
				(Data.DIMENSION.height / 2) - (Data.POPUP_DIMENSION.height / 2),
				Data.POPUP_DIMENSION.width,
				Data.POPUP_DIMENSION.height
				));
	}
	
	public void add() {
		Game.getGUI().add(this, Data.POPUP_LAYER);
		Game.getGUI().setInputEnabled(false);
	}
}