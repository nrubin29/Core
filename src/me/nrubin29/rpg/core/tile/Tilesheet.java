package me.nrubin29.rpg.core.tile;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import me.nrubin29.rpg.core.Game;
import me.nrubin29.rpg.core.util.Data;

public class Tilesheet {

	private String name;
	
	private HashMap<Point, ImageIcon> tiles = new HashMap<Point, ImageIcon>();
	
	public Tilesheet(String name) {
		this.name = name.substring(0, name.lastIndexOf("."));
		
		try {
			BufferedImage image = ImageIO.read(Game.class.getClassLoader().getResource("res/tilesheet/" + name.toLowerCase())); // I don't use ImageUtil because I need a BufferedImage.
			
			int xTiles = image.getWidth() / Data.TILE_DIM;
			int yTiles = image.getHeight() / Data.TILE_DIM;
			
			for (int x = 0; x < xTiles; x++) {
				for (int y = 0; y < yTiles; y++) {
					
					BufferedImage tileImage = new BufferedImage(Data.TILE_DIM, Data.TILE_DIM, BufferedImage.TYPE_INT_ARGB);
					
					tileImage.setRGB(0, 0, Data.TILE_DIM, Data.TILE_DIM, image.getRGB(x * Data.TILE_DIM, y * Data.TILE_DIM, Data.TILE_DIM, Data.TILE_DIM, null, 0, Data.TILE_DIM), 0, Data.TILE_DIM);
					
					tiles.put(new Point(x, y), new ImageIcon(tileImage));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public ImageIcon getTile(Point p) {
		return tiles.get(p);
	}
}