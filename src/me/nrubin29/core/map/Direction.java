package me.nrubin29.core.map;

import java.awt.Point;

import me.nrubin29.core.data.DataFile;
import me.nrubin29.core.data.DataManager;
import me.nrubin29.core.data.files.Settings;
import me.nrubin29.core.util.Constants;

public enum Direction {
	LEFT, UP, RIGHT, DOWN;
	
	public static Direction valueOf(int keyCode) {
		DataFile keys = DataManager.getInstance().getConfigurationFile(Settings.class);

        if (keyCode == Integer.parseInt(keys.get("upID"))) return Direction.UP;
        if (keyCode == Integer.parseInt(keys.get("downID"))) return Direction.DOWN;
        if (keyCode == Integer.parseInt(keys.get("leftID"))) return Direction.LEFT;
        if (keyCode == Integer.parseInt(keys.get("rightID"))) return Direction.RIGHT;
        else return null;
	}
	
	public Point getMovement() {
		switch (values()[ordinal()]) {
		case LEFT: return new Point(Constants.TILE_DIM * -1, 0);
		case UP: return new Point(0, Constants.TILE_DIM * -1);
		case RIGHT: return new Point(Constants.TILE_DIM, 0);
		case DOWN: return new Point(0, Constants.TILE_DIM);
		default: return null;
		}
	}
	
	public Point getPointRelativeTo(Point p) {
		switch(values()[ordinal()]) {
		case LEFT: return new Point((int) p.getX() + Constants.TILE_DIM, (int) p.getY());
		case UP: return new Point((int) p.getX(), (int) p.getY() + Constants.TILE_DIM);
		case RIGHT: return new Point((int) p.getX() - Constants.TILE_DIM, (int) p.getY());
		case DOWN: return new Point((int) p.getX(), (int) p.getY() - Constants.TILE_DIM);
		default: return null;
		}
	}
}