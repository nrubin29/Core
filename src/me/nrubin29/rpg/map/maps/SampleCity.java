package me.nrubin29.rpg.map.maps;

import me.nrubin29.rpg.map.Map;
import me.nrubin29.rpg.map.MapType;

public class SampleCity extends Map {
	
	public SampleCity() {
		super(MapType.CITY, "Sample City", 
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