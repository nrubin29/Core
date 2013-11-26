package me.nrubin29.rpg.core.audio;

public enum SoundEffect implements Audio {

	WALK("walk");
	
	private String path;
	
	SoundEffect(String name) {
		this.path = "res/audio/sfx/" + name + ".mp3";
	}
	
	public String getPath() {
		return path;
	}
}