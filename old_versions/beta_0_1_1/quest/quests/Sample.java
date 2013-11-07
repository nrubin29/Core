package me.nrubin29.rpg.quest.quests;

import me.nrubin29.rpg.events.Event;
import me.nrubin29.rpg.events.Event.EventType;
import me.nrubin29.rpg.gui.AchievementPopup;
import me.nrubin29.rpg.gui.Text.TextFactory;
import me.nrubin29.rpg.map.Maps;
import me.nrubin29.rpg.misc.Achievement;
import me.nrubin29.rpg.quest.Quest;

public class Sample extends Quest {

	public void startQuest() {
		final Event second = new Event(EventType.MOVE, 6, 6, false) {
			public void run() {
				AchievementPopup.popupAcheievment(Achievement.HUNT);
				finishQuest();
			}
		};
		
		final Event first = new Event(EventType.MOVE, 7, 7, true) {
			public void run() {
				new TextFactory().addText("you found the first point, now find the second!").show();
				second.setEnabled(true);
				setShouldRemove();
			}
		};
		
		super.registerEvent(Maps.SAMPLE_CITY, first);
		super.registerEvent(Maps.SAMPLE_CITY, second);
	}
}