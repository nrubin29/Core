package me.nrubin29.rpg.core.misc;

public enum Achievement {
	
	HUNT("found the second point!");

	private String name;
	
	Achievement(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}