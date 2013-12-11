package me.nrubin29.rpg.map;

import me.nrubin29.rpg.tile.Tile;

public enum MapType {

    CITY(Tile.GRASS_SQUARE_MIDDLE),
    ROUTE(Tile.GRASS_SQUARE_MIDDLE),
    CAVE(Tile.GRASS_SQUARE_MIDDLE),
    BUILDING(Tile.GRASS_SQUARE_MIDDLE);
    
    private Tile backgroundTile;
    
    MapType(Tile tile) {
    	this.backgroundTile = tile;
    }
    
    public Tile getBackgroundTile() {
    	return backgroundTile;
    }
}