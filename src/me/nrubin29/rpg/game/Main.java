package me.nrubin29.rpg.game;

import me.nrubin29.rpg.core.Game;
import me.nrubin29.rpg.core.map.MapManager;
import me.nrubin29.rpg.core.quest.QuestManager;
import me.nrubin29.rpg.game.maps.SampleCity;
import me.nrubin29.rpg.game.quests.SampleQuest;

public class Main extends Game {
	
	public Main() { super("RPG-Game", "0.0.1"); }
	
	@Override
	public void onPreEnable() {
		System.out.println("Pre enabling!");
	}
	
	@Override
	public void onEnable() {
		System.out.println("Done enabling!");
		
		MapManager.getInstance().registerMap(new SampleCity());
		MapManager.getInstance().getMap("Sample City").display();
		
		QuestManager.getInstance().registerQuest(new SampleQuest());
		QuestManager.getInstance().getQuest("Sample Quest").startQuest();
	}
	
	@Override
	public void onDisable() {
		System.out.println("Disabling!");
	}
	
	public static void main(String[] args) {
		new Main();
	}
}