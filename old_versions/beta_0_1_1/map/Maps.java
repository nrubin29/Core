package me.nrubin29.rpg.map;

public enum Maps {

	SAMPLE_CITY("SampleCity");
	
	private Map instance;
	
	Maps(String className) {
		try {
			instance = (Map) Class.forName("me.nrubin29.rpg.map.maps." + className).newInstance();
		}
		catch (Exception e) { e.printStackTrace(); }
	}
	
	public Map getInstance() {
		return instance;
	}
}