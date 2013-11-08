package me.nrubin29.rpg.data;

import java.util.ArrayList;

import me.nrubin29.rpg.data.files.Keys;

public class DataManager {
	
	private DataManager() { }
	
	private static DataManager instance = new DataManager();
	
	public static DataManager getInstance() {
		return instance;
	}
	
	private ArrayList<DataFile> files = new ArrayList<DataFile>();
	
	public void setup() {
		files.add(new Keys());
	}
	
	@SuppressWarnings("unchecked")
	public <E extends DataFile> E getConfigurationFile(Class<? extends DataFile> target) {
		for (DataFile config : files) {
			if (config.getClass().equals(target)) return (E) config;
		}
		
		return null;
	}
}