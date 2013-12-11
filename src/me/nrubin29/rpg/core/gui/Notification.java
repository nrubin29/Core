package me.nrubin29.rpg.core.gui;

import me.nrubin29.rpg.core.misc.Achievement;
import me.nrubin29.rpg.core.util.ThreadUtil;

public class Notification {

    public static void popupNotification(String title, String message) {
        new Notification(title, message);
    }

	public static void popupAcheievment(Achievement ach) {
		new Notification("achievement unlocked", ach.getName());
	}
	
	private String title, message;

    private Notification(String title, String message) {
    	this.title = title;
    	this.message = message;
    	
    	GUI.getInstance().addNotification(this);

        ThreadUtil.runTimer(5 * 1000, new Runnable() {
            public void run() {
                GUI.getInstance().removeNotification(Notification.this);
            }
        });
    }
    
    public String getTitle() {
    	return title;
    }
    
    public String getMessage() {
    	return message;
    }
}