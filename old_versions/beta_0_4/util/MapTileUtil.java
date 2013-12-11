package me.nrubin29.rpg.util;

import java.awt.Point;
import java.awt.event.KeyEvent;

public class MapTileUtil {
	
	public enum Direction {
		LEFT, UP, RIGHT, DOWN;
		
		public static Direction valueOf(int keyCode) {
			if (keyCode < KeyEvent.VK_LEFT || keyCode > KeyEvent.VK_DOWN) return null;
			return values()[keyCode - KeyEvent.VK_LEFT];
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
}