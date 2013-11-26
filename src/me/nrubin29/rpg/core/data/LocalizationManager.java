package me.nrubin29.rpg.core.data;

public class LocalizationManager {
	
	public enum Language { ENGLISH; }
	
	private LocalizationManager() { }
	
	private static LocalizationManager instance = new LocalizationManager();
	
	public static LocalizationManager getInstance() {
		return instance;
	}
}