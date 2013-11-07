package me.nrubin29.citrine.map;

import me.nrubin29.citrine.util.Constants;
import me.nrubin29.citrine.util.ImageUtil;

import javax.swing.*;

public enum Tile {

    GRASS_SQUARE_BOTTOM,
    GRASS_SQUARE_BOTTOM_LEFT,
    GRASS_SQUARE_BOTTOM_RIGHT,
    GRASS_SQUARE_MIDDLE,
    GRASS_SQUARE_MIDDLE_LEFT,
    GRASS_SQUARE_MIDDLE_RIGHT,
    GRASS_SQUARE_TOP,
    GRASS_SQUARE_TOP_LEFT,
    GRASS_SQUARE_TOP_RIGHT,

    /* Divider */

    ;

    private ImageIcon image;
    private boolean isSolid;

    Tile() {
        this(false);
        image = ImageUtil.getImage("tiles/" + name().toLowerCase(), Constants.TILE_DIM, Constants.TILE_DIM);
    }

    Tile(boolean isSolid) {
        this.isSolid = isSolid;
    }

    public ImageIcon getImage() {
        return image;
    }

    public boolean isSolid() {
        return isSolid;
    }
}