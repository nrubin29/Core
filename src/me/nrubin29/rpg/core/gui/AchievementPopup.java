package me.nrubin29.rpg.core.gui;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import me.nrubin29.rpg.core.Game;
import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.files.Player;
import me.nrubin29.rpg.core.misc.Achievement;
import me.nrubin29.rpg.core.util.Data;
import me.nrubin29.rpg.core.util.FontUtil;
import me.nrubin29.rpg.core.util.TimerUtil;

public class AchievementPopup extends TranslucentPanel {

	private static final long serialVersionUID = 1L;

	public static void popupAcheievment(Achievement ach) {
		Game.getGUI().add(new AchievementPopup(ach), Data.ACHIEVEMENT_LAYER);
	}
	
	private AchievementPopup(Achievement ach) {
		JLabel unlocked = new JLabel("achievement unlocked");
		unlocked.setFont(FontUtil.getFont().deriveFont(Font.PLAIN, 20));
		unlocked.setForeground(Data.FOREGROUND_COLOR_ON_BACKGROUND);
		
		add(unlocked);
		
		JLabel label = new JLabel(ach.getName());
		label.setFont(FontUtil.getFont().deriveFont(Font.PLAIN, 12));
		label.setForeground(Data.FOREGROUND_COLOR_ON_BACKGROUND);
		
		add(label);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBounds(new Rectangle(Data.DIMENSION.width - (Data.ACHIEVEMENT_POPUP_DIMENSION.width) - Data.TILE_DIM, Data.TILE_DIM, Data.ACHIEVEMENT_POPUP_DIMENSION.width, Data.ACHIEVEMENT_POPUP_DIMENSION.height));
		
		DataManager.getInstance().<Player>getConfigurationFile(Player.class).addAchievement(ach);
		
		TimerUtil.runTimer(5 * 1000, new Runnable() {
			public void run() {
				setVisible(false);
				Game.getGUI().remove(AchievementPopup.this);
			}
		});
	}
}