package me.nrubin29.core.script.cmds;

import javax.swing.ImageIcon;

import me.nrubin29.core.gui.Popup.PopupFactory;
import me.nrubin29.core.script.ScriptCommand;
import me.nrubin29.core.server.Session;

public class PopupCommand extends ScriptCommand {

	/*
	 * popup show image text
	 */
	public void parse(String[] args) {
		if (args.length < 3) return;
		
		ImageIcon image = null;
		String text;
		
		try {
			image = Session.getInstance().getLocalPlayer().getImage(); //TODO: This needs to be changed.
		}
		catch (Exception e) { }
		
		text = argsToString(args, 3);
		
		if (args[0].equalsIgnoreCase("show")) {
			new PopupFactory().addPopup(image, text).show();
		}
	}
}