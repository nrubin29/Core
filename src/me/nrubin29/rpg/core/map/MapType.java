package me.nrubin29.rpg.core.map;

import me.nrubin29.rpg.core.tile.Tile;

public enum MapType {

    CITY(Tile.GRASS),
    ROUTE(Tile.GRASS),
    CAVE(Tile.GRASS),
    BUILDING(Tile.GRASS);
    
    private Tile backgroundTile;
    
    MapType(Tile tile) {
    	this.backgroundTile = tile;
    }
    
    public Tile getBackgroundTile() {
    	return backgroundTile;
    }
}