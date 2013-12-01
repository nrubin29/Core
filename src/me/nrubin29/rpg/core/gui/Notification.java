package me.nrubin29.rpg.core.gui;

import java.awt.*;

import javax.swing.*;

import me.nrubin29.rpg.core.Game;
import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.files.PlayerData;
import me.nrubin29.rpg.core.misc.Achievement;
import me.nrubin29.rpg.core.util.Data;
import me.nrubin29.rpg.core.util.FontUtil;
import me.nrubin29.rpg.core.util.TimerUtil;

public class Notification extends JPanel {

	private static final long serialVersionUID = 1L;

    public static void popupNotification(String title, String message) {
        Game.getGUI().add(new Notification(title, message, null), Data.ACHIEVEMENT_LAYER);
    }

	public static void popupAcheievment(Achievement ach) {
        Game.getGUI().add(new Notification("achievement unlocked", ach.getName(), ach), Data.ACHIEVEMENT_LAYER);
	}

    private Notification(String t, String m, Achievement ach) {
        JLabel title = new JLabel(t);
        title.setFont(FontUtil.getFont(20));
        title.setForeground(Data.FOREGROUND_COLOR_ON_BACKGROUND);

        add(title);

        JLabel message = new JLabel(m);
        message.setFont(FontUtil.getFont(12));
        message.setForeground(Data.FOREGROUND_COLOR_ON_BACKGROUND);

        add(message);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(new Rectangle(Data.GAME_DIMENSION.width - (Data.NOTIFICATION_DIMENSION.width) - Data.TILE_DIM, Data.TILE_DIM, Data.NOTIFICATION_DIMENSION.width, Data.NOTIFICATION_DIMENSION.height));
        setOpaque(false);

        if (ach != null) DataManager.getInstance().<PlayerData>getConfigurationFile(PlayerData.class).addAchievement(ach);

        TimerUtil.runTimer(5 * 1000, new Runnable() {
            public void run() {
                setVisible(false);
                Game.getGUI().remove(Notification.this);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Data.TRANSLUCENT);
        Rectangle r = g.getClipBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        super.paintComponent(g);
    }
}