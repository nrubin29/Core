package me.nrubin29.core.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public class Constants {
	
	/* GUI */
	
	public static final Dimension
			GAME_DIMENSION = new Dimension(640, 500),
			POPUP_DIMENSION = new Dimension(300, 130),
			NOTIFICATION_DIMENSION = new Dimension(250, 40),
			ERROR_DIMENSION = new Dimension(300, 250);

    public static final int
    		NUM_ROWS = 15,
    		TILES_PER_ROW = 20,
    		TILES_PER_COLUMN = 15;
    
    public static final int TILE_DIM = 32;
    
    public static final Point START_LOCATION = new Point(5 * TILE_DIM, 5 * TILE_DIM);
    
    public static final Color
			TRANSLUCENT = new Color(0, 0, 0, 50),
			ON_TOP_OF_TRANSLUCENT = new Color(0, 0, 0, 1);

    /* Information */
    
    public static final String
    		NAME = "Core",
    		VERSION = "0.9.4.1",
    		AUTHOR = "Noah Rubin";
}