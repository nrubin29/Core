package me.nrubin29.rpg.demo;

import me.nrubin29.rpg.audio.Music;
import me.nrubin29.rpg.map.Map;
import me.nrubin29.rpg.map.MapType;

public class SampleCity extends Map {
	
	public SampleCity() {
		super(MapType.CITY, "Sample City", Music.TEST,
				"TTTL TTTR E CD E E E E E E E E E E E E E E E E",
				"TTML TTMR E CL E E E E E E E E E E E E E E E E",
				"TTBL TTBR E CR E E E E E E E E E E E E E E E E",
				"E    E    E CU E E E E E E E E E E E E E E E E",
				
				Map.EMPTY_ROW,
				Map.EMPTY_ROW,
				Map.EMPTY_ROW,
				Map.EMPTY_ROW,
				Map.EMPTY_ROW,
				Map.EMPTY_ROW,
				Map.EMPTY_ROW,
				Map.EMPTY_ROW,
				Map.EMPTY_ROW,
				Map.EMPTY_ROW,
				Map.EMPTY_ROW,
				Map.EMPTY_ROW
				);
	}
}