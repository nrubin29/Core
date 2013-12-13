package me.nrubin29.rpg.core.data.files;

import me.nrubin29.rpg.core.data.DataFile;
import me.nrubin29.rpg.core.util.Constants;

public class Version extends DataFile {

	public Version() {
		super("version");
		
		set("version", Constants.VERSION);
	}
}