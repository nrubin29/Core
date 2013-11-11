package me.nrubin29.rpg.gui;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import me.nrubin29.rpg.Main;
import me.nrubin29.rpg.data.DataManager;
import me.nrubin29.rpg.data.files.Player;
import me.nrubin29.rpg.misc.Achievement;
import me.nrubin29.rpg.util.Constants;
import me.nrubin29.rpg.util.FontUtil;
import me.nrubin29.rpg.util.TimerUtil;

public class AchievementPopup extends TranslucentPanel {

	private static final long serialVersionUID = 1L;

	public static void popupAcheievment(Achievement ach) {
		Main.getGUI().add(new AchievementPopup(ach), Constants.ACHIEVEMENT_LAYER);
	}
	
	private AchievementPopup(Achievement ach) {
		JLabel unlocked = new JLabel("achievement unlocked");
		unlocked.setFont(FontUtil.getFont().deriveFont(Font.PLAIN, 20));
		unlocked.setForeground(Constants.FOREGROUND_COLOR_ON_BACKGROUND);
		
		add(unlocked);
		
		JLabel label = new JLabel(ach.getName());
		label.setFont(FontUtil.getFont().deriveFont(Font.PLAIN, 12));
		label.setForeground(Constants.FOREGROUND_COLOR_ON_BACKGROUND);
		
		add(label);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBounds(new Rectangle(Constants.PANEL_DIMENSION.width - (Constants.ACHIEVEMENT_POPUP_DIMENSION.width) - Constants.TILE_DIM, Constants.TILE_DIM, Constants.ACHIEVEMENT_POPUP_DIMENSION.width, Constants.ACHIEVEMENT_POPUP_DIMENSION.height));
		
		DataManager.getInstance().<Player>getConfigurationFile(Player.class).addAchievement(ach);
		
		TimerUtil.runTimer(5 * 1000, new Runnable() {
			public void run() {
				setVisible(false);
				Main.getGUI().remove(AchievementPopup.this);
			}
		});
	}
}