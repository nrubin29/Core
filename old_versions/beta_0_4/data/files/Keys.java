package me.nrubin29.rpg.data.files;

import me.nrubin29.rpg.data.DataFile;

public class Keys extends DataFile {

	private int upID, downID, leftID, rightID, interactID;
	
	public Keys() {
		super("keys");
		
		this.upID = Integer.parseInt(getValue("upID"));
		this.downID = Integer.parseInt(getValue("downID"));
		this.leftID = Integer.parseInt(getValue("leftID"));
		this.rightID = Integer.parseInt(getValue("rightID"));
		this.interactID = Integer.parseInt(getValue("interactID"));
	}
	
	@Override
	public String toString() {
		return upID + " | " + downID + " | " + leftID + " | " + rightID + " | " + interactID;
	}
}