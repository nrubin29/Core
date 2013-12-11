package me.nrubin29.citrine.map;

import me.nrubin29.citrine.event.Event;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public enum Map {

    SAMPLE(MapType.CITY, "Sample City", new Tile[][] {
            new Tile[] { Tile.GRASS_SQUARE_TOP_LEFT, Tile.GRASS_SQUARE_TOP, Tile.GRASS_SQUARE_TOP_RIGHT },
            new Tile[] { Tile.GRASS_SQUARE_MIDDLE_LEFT, Tile.GRASS_SQUARE_MIDDLE, Tile.GRASS_SQUARE_MIDDLE_RIGHT },
            new Tile[] { Tile.GRASS_SQUARE_BOTTOM_LEFT, Tile.GRASS_SQUARE_BOTTOM, Tile.GRASS_SQUARE_BOTTOM_RIGHT }
    },
            new Event(0, 0) {
                @Override
                public void run() {
                    System.out.println("You found the developer's hideout.");
                }
            }
    );

    private MapType type;
    private String name;
    private Tile[][] tiles;
    private ArrayList<Event> events;

    Map(MapType type, String name, Tile[][] tiles, Event... events) {
        this.type = type;
        this.name = name;
        this.tiles = tiles;
        this.events = new ArrayList<Event>(Arrays.asList(events));
    }

    public MapType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Tile[][] getTiles() {
        return tiles.clone();
    }

    public void checkEvents(Point p) {
        for (Event event : events) {
            if (event.getTilePoint().equals(p)) event.run();
        }
    }
}