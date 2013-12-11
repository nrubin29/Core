package me.nrubin29.rpg.util;

import java.awt.Color;
import java.awt.Dimension;

public class Constants {
	
	public static final Dimension
			PANEL_DIMENSION = new Dimension(640, 480),
			STATUSBAR_DIMENSION = new Dimension(640, 20),
			ACTUAL_DIMENSION = new Dimension(640, 520),
			
			ACHIEVEMENT_POPUP_DIMENSION = new Dimension(250, 40);

    public static final int TILE_DIM = 32;
    
    public static final int
    		NUM_ROWS = 15,
    		NUM_COLUMNS = 20,
    		TILES_PER_ROW = 20,
    		TILES_PER_COLUMN = 15;
    
    public static final int
    		SPRITE_HEIGHT = 32, // 30
    		SPRITE_WIDTH = 32; // 25
    
    public static final int TEXT_HEIGHT = 40;

    public static final int ANIMATION_DURATION = 75;
    
    public static final int
    		MAX_HEALTH = 3,
    		MAX_FOOD = 5;
    
    public static final int STATUSBAR_OBJECT_DIM = 15;
    
    public static final Color
    		TRANSLUCENT = new Color(0, 0, 0, 50),
    		ON_TOP_OF_TRANSLUCENT = new Color(0, 0, 0, 1),
    		BACKGROUND_COLOR = Color.GRAY,
    		FOREGROUND_COLOR_ON_BACKGROUND = Color.WHITE;
    
    public static final Integer
    		BACKGROUND_LAYER = new Integer(0),
    		TILE_LAYER = new Integer(1),
    		SPRITE_LAYER = new Integer(2),
    		TEXT_LAYER = new Integer(3),
    		ACHIEVEMENT_LAYER = new Integer(4);
    
    public static final String VERSION = "0.0.0.8";
}