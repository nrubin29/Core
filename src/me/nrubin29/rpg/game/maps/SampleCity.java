package me.nrubin29.rpg.game.maps;

import me.nrubin29.rpg.core.audio.Music;
import me.nrubin29.rpg.core.map.Map;
import me.nrubin29.rpg.core.map.MapType;

public class SampleCity extends Map {
	
	public SampleCity() {
		super(MapType.CITY, "Sample City", Music.TEST,
				"TTTL TTTR E CD E E E E WV E E E E E E E E E E E",
				"TTML TTMR E CL E E E E WH E E E E E E E E E E E",
				"TTBL TTBR E CR E E E E FH E E E E E E E E E E E",
				"E    E    E CU E E E E FV E E E E E E E E E E E",
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