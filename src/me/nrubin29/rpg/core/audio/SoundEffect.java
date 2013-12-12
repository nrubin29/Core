package me.nrubin29.rpg.core.audio;

public enum SoundEffect implements Audio {

	BUMP("bump");
	
	private String path;
	
	SoundEffect(String name) {
		this.path = "audio/sfx/" + name + ".mp3";
	}
	
	public String getPath() {
		return path;
	}
}