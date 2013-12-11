package me.nrubin29.rpg.core.script.cmds;

import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.files.PlayerData;
import me.nrubin29.rpg.core.item.Item;
import me.nrubin29.rpg.core.script.ScriptCommand;

public class ItemCommand extends ScriptCommand {

	/*
	 * item add apple
	 * LATER: item drop apple 0 0
	 */
	public void parse(String[] args) {
		if (args.length < 2) return;
		
		Item item;
		String str = Character.toUpperCase(args[1].charAt(0)) + args[1].substring(1);
		
		try {
			item = (Item) Class.forName("me.nrubin29.rpg.core.item." + str).newInstance();
		}
		catch (Exception e) { return; }
		
		if (args[0].equalsIgnoreCase("add")) {
			DataManager.getInstance().<PlayerData>getConfigurationFile(PlayerData.class).addItem(item);
		}
	}
}