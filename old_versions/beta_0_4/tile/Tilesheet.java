package me.nrubin29.rpg.tile;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import me.nrubin29.rpg.Main;
import me.nrubin29.rpg.util.Constants;

public class Tilesheet {

	private String name;
	
	private HashMap<Point, ImageIcon> tiles = new HashMap<Point, ImageIcon>();
	
	public Tilesheet(String name) {
		this.name = name.substring(0, name.lastIndexOf("."));
		
		try {
			BufferedImage image = ImageIO.read(Main.class.getClassLoader().getResource("res/tilesheet/" + name.toLowerCase())); // I don't use ImageUtil because I need a BufferedImage.
			
			int xTiles = image.getWidth() / Constants.TILE_DIM;
			int yTiles = image.getHeight() / Constants.TILE_DIM;
			
			for (int x = 0; x < xTiles; x++) {
				for (int y = 0; y < yTiles; y++) {
					
					BufferedImage tileImage = new BufferedImage(Constants.TILE_DIM, Constants.TILE_DIM, BufferedImage.TYPE_INT_ARGB);
					
					tileImage.setRGB(0, 0, Constants.TILE_DIM, Constants.TILE_DIM, image.getRGB(x * Constants.TILE_DIM, y * Constants.TILE_DIM, Constants.TILE_DIM, Constants.TILE_DIM, null, 0, Constants.TILE_DIM), 0, Constants.TILE_DIM);
					
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