package me.nrubin29.rpg.demo;

import me.nrubin29.rpg.map.MapManager;
import me.nrubin29.rpg.quest.QuestManager;

public class Demo {

	public static void registerDemoComponents() {
		MapManager.getInstance().registerMap(new SampleCity());
		MapManager.getInstance().getMap("Sample City").display();
		
		QuestManager.getInstance().registerQuest(new SampleQuest());
		QuestManager.getInstance().getQuest("Sample Quest").startQuest();
	}
}