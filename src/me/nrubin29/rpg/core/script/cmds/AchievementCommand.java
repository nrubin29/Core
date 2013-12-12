package me.nrubin29.rpg.core.script.cmds;

import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.files.PlayerData;
import me.nrubin29.rpg.core.misc.Achievement;
import me.nrubin29.rpg.core.script.ScriptCommand;

public class AchievementCommand extends ScriptCommand {

	/*
	 * achievement give name
	 */
	public void parse(String[] args) {
		if (args.length < 2) return;
		
		String name = args[1];
		
		if (args[0].equalsIgnoreCase("give")) {
			DataManager.getInstance().<PlayerData>getConfigurationFile(PlayerData.class).addAchievement(Achievement.valueOf(name.toUpperCase()));
		}
	}
}