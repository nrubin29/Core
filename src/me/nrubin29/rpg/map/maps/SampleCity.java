package me.nrubin29.rpg.map.maps;

import me.nrubin29.rpg.audio.Music;
import me.nrubin29.rpg.map.Map;
import me.nrubin29.rpg.map.MapType;
import me.nrubin29.rpg.map.Maps;

public class SampleCity extends Map {
	
	public SampleCity() {
		super(Maps.SAMPLE_CITY, MapType.CITY, "Sample City", Music.TEST,
				"TTTL  TTTR  E CD E E E E E E E E E E E E E E E E",
				"TTMTL TTMTR E CL E E E E E E E E E E E E E E E E",
				"TTMBL TTMBR E CR E E E E E E E E E E E E E E E E",
				"TTBL  TTBR  E CD E E E E E E E E E E E E E E E E",
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