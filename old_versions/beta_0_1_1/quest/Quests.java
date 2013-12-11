package me.nrubin29.rpg.quest;

public enum Quests {

	SAMPLE("Sample");
	
	private Quest instance;
	
	Quests(String className) {
		try {
			instance = (Quest) Class.forName("me.nrubin29.rpg.quest.quests." + className).newInstance();
		}
		catch (Exception e) { e.printStackTrace(); }
	}
	
	public Quest getInstance() {
		return instance;
	}
}