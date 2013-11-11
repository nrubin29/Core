package me.nrubin29.rpg.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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

import me.nrubin29.rpg.Main;
import me.nrubin29.rpg.misc.Icon;
import me.nrubin29.rpg.misc.Image;
import me.nrubin29.rpg.util.Constants;
import me.nrubin29.rpg.util.FontUtil;

public class Popup extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static class PopupFactory {
		 private ArrayList<Popup> popups = new ArrayList<Popup>();
		 private Runnable whenDone;
		 private int cursor;
		 
		 public PopupFactory addPopup(Image image, String text) { 
			JPanel panel = new JPanel();
			panel.setFocusable(false);
			panel.setBackground(Constants.BACKGROUND_COLOR);
			panel.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			JTextPane textArea = new JTextPane();
			textArea.setText(text);
			textArea.setEditable(false);
			textArea.setFocusable(false);
			textArea.setFont(FontUtil.getFont().deriveFont(Font.PLAIN, 14));
			textArea.setForeground(Constants.FOREGROUND_COLOR_ON_BACKGROUND);
			textArea.setBackground(Constants.BACKGROUND_COLOR);
			textArea.setPreferredSize(new Dimension(Constants.POPUP_DIMENSION.width, 40));
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
		 
		 public PopupFactory addCustom(Icon image, JComponent comp) {
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
				Main.getGUI().remove(Popup.this);
				Main.getGUI().setInputEnabled(true);
				
				if (whenDone != null) whenDone.run();
			}
		});
		
		add(comp);
		add(x);
		
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		setBackground(Constants.BACKGROUND_COLOR);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBounds(new Rectangle(
				(Constants.ACTUAL_DIMENSION.width / 2) - (Constants.POPUP_DIMENSION.width / 2),
				(Constants.ACTUAL_DIMENSION.height / 2) - (Constants.POPUP_DIMENSION.height / 2),
				Constants.POPUP_DIMENSION.width,
				Constants.POPUP_DIMENSION.height
				));
	}
	
	public void add() {
		Main.getGUI().add(this, Constants.POPUP_LAYER);
		Main.getGUI().setInputEnabled(false);
	}
}