package me.nrubin29.core.audio;

public enum Music implements Audio {

	TEST("test");
	
	private String path;
	
	Music(String name) {
		this.path = "audio/music/" + name + ".mp3";
	}
	
	public String getPath() {
		return path;
	}
}