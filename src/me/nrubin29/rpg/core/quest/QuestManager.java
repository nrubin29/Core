package me.nrubin29.rpg.core.quest;

import java.util.ArrayList;

public class QuestManager {

	private QuestManager() { }
	
	private static QuestManager instance = new QuestManager();
	
	public static QuestManager getInstance() {
		return instance;
	}
	
	private ArrayList<Quest> quests = new ArrayList<Quest>();
	
	public void registerQuest(Quest quest) {
		quests.add(quest);
	}
	
	public Quest getQuest(String name) {
		for (Quest quest : quests) {
			if (quest.getName().equalsIgnoreCase(name)) return quest;
		}
		
		return null;
	}
}