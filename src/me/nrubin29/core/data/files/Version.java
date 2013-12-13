package me.nrubin29.core.data.files;

import me.nrubin29.core.data.DataFile;
import me.nrubin29.core.util.Constants;

public class Version extends DataFile {

	public Version() {
		super("version");
		
		set("version", Constants.VERSION);
	}
}