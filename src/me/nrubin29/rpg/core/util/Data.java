package me.nrubin29.rpg.core.util;

import java.awt.Color;
import java.awt.Dimension;

public class Data {
	
	/* GUI */
	
	public static final Dimension
			SPLASH_SCREEN_DIMENSION = new Dimension(550, 100),
			DIMENSION = new Dimension(640, 500),
			POPUP_DIMENSION = new Dimension(300, 130),
			ACHIEVEMENT_POPUP_DIMENSION = new Dimension(250, 40),
			ERROR_POPUP_DIMENSION = new Dimension(300, 300);

    public static final int
    		NUM_ROWS = 15,
    		NUM_COLUMNS = 20,
    		TILES_PER_ROW = 20,
    		TILES_PER_COLUMN = 15;
    
    public static final int
    		TILE_DIM = 32,
    		STATUSBAR_OBJECT_DIM = 16;
    
    public static final Color
			TRANSLUCENT = new Color(0, 0, 0, 50),
			ON_TOP_OF_TRANSLUCENT = new Color(0, 0, 0, 1),
			BACKGROUND_COLOR = Color.GRAY,
			FOREGROUND_COLOR_ON_BACKGROUND = Color.WHITE;

    public static final Integer
			BACKGROUND_LAYER = Integer.valueOf(0),
			TILE_LAYER = Integer.valueOf(1),
			SPRITE_LAYER = Integer.valueOf(2),
			POPUP_LAYER = Integer.valueOf(3),
			ACHIEVEMENT_LAYER = Integer.valueOf(4);

    /* Misc. */
    
    public static final String
    		ENGINE_NAME = "RPG-Core",
    		ENGINE_VERSION = "0.6";
    
    public static String
    		NAME,
    		VERSION;
    
    public static final int ANIMATION_DURATION = 75;
 
    /* Player */
    
    public static final int
    		MAX_HEALTH = 3,
    		MAX_FOOD = 5;
}