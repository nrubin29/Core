package me.nrubin29.citrine.event;

import me.nrubin29.citrine.util.Constants;

import java.awt.*;

public abstract class Event {

    private final Point tilePoint;

    public Event(int xTile, int yTile) {
        this.tilePoint = new Point(xTile * Constants.TILES_PER_ROW, yTile * Constants.TILES_PER_COLUMN);
    }

    public final Point getTilePoint() {
        return tilePoint;
    }

    public abstract void run();
}