package me.nrubin29.rpg.core.data;

public class LocalizationManager {
	
	public enum Language { ENGLISH, SPANISH; }
	
	private LocalizationManager() { }
	
	private static LocalizationManager instance = new LocalizationManager();
	
	public static LocalizationManager getInstance() {
		return instance;
	}

    private Language currentLanguage;
    private DataFile currentDataFile;

    public Language getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(Language language) {
        this.currentLanguage = language;
        this.currentDataFile = new DataFile("lang", language.toString().toLowerCase());
    }

    public String getString(String key) {
        return currentDataFile.getValue(key);
    }
}