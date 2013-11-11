package me.nrubin29.rpg.demo;

import me.nrubin29.rpg.events.Event;
import me.nrubin29.rpg.events.Event.EventType;
import me.nrubin29.rpg.gui.AchievementPopup;
import me.nrubin29.rpg.gui.Popup.PopupFactory;
import me.nrubin29.rpg.misc.Achievement;
import me.nrubin29.rpg.misc.Icon;
import me.nrubin29.rpg.quest.Quest;

public class SampleQuest extends Quest {

	public SampleQuest() { super("Sample Quest"); }
	
	public void startQuest() {
		final Event second = new Event(EventType.MOVE, 6, 6, false) {
			public void run() {
				AchievementPopup.popupAcheievment(Achievement.HUNT);
				finishQuest();
			}
		};
		
		final Event first = new Event(EventType.MOVE, 7, 7, true) {
			public void run() {
				new PopupFactory().addPopup(Icon.TESTPERSON, "you found the first point, now find the second!").show();
				second.setEnabled(true);
				requestRemoval();
			}
		};
		
		super.registerEvent("Sample City", first);
		super.registerEvent("Sample City", second);
	}
}