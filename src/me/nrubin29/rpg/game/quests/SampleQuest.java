package me.nrubin29.rpg.game.quests;

import me.nrubin29.rpg.core.events.Event;
import me.nrubin29.rpg.core.events.Event.EventType;
import me.nrubin29.rpg.core.gui.Notification;
import me.nrubin29.rpg.core.gui.Popup.PopupFactory;
import me.nrubin29.rpg.core.misc.Achievement;
import me.nrubin29.rpg.core.quest.Quest;
import me.nrubin29.rpg.core.server.Session;

public class SampleQuest extends Quest {

	public SampleQuest() { super("Sample Quest"); }
	
	public void startQuest() {
		final Event second = new Event(EventType.MOVE, 6, 6, false) {
			public void run() {
				Notification.popupAcheievment(Achievement.HUNT);
				finishQuest();
			}
		};
		
		final Event first = new Event(EventType.MOVE, 7, 7, true) {
			public void run() {
				new PopupFactory().addPopup(Session.getInstance().getLocalPlayer(), "you found the first point, now find the second!").show();
				second.setEnabled(true);
				requestRemoval();
			}
		};
		
		super.registerEvent("Sample City", first);
		super.registerEvent("Sample City", second);
	}
}