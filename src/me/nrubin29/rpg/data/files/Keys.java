package me.nrubin29.rpg.data.files;

import me.nrubin29.rpg.data.DataFile;

public class Keys extends DataFile {

	private int upID, downID, leftID, rightID, interactID;
	
	public Keys() {
		super("keys");
		
		this.upID = Integer.parseInt(this.<String>getValue("upID"));
		this.downID = Integer.parseInt(this.<String>getValue("downID"));
		this.leftID = Integer.parseInt(this.<String>getValue("leftID"));
		this.rightID = Integer.parseInt(this.<String>getValue("rightID"));
		this.interactID = Integer.parseInt(this.<String>getValue("interactID"));
		
		set("upID", 10, upID);
		save();
	}
	
	@Override
	public String toString() {
		return upID + " | " + downID + " | " + leftID + " | " + rightID + " | " + interactID;
	}
}