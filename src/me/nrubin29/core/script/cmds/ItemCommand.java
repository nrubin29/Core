package me.nrubin29.core.script.cmds;

import me.nrubin29.core.item.Inventory;
import me.nrubin29.core.item.Item;
import me.nrubin29.core.script.ScriptCommand;

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
			item = (Item) Class.forName("me.nrubin29.core.item." + str).newInstance();
		}
		catch (Exception e) { return; }
		
		if (args[0].equalsIgnoreCase("add")) {
			Inventory.getInstance().addItem(item);
		}
	}
}