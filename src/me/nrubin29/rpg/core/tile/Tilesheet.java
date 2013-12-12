package me.nrubin29.rpg.core.tile;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.ImageIcon;

import me.nrubin29.rpg.core.misc.Image;
import me.nrubin29.rpg.core.util.Constants;
import me.nrubin29.rpg.core.util.ResourceUtil;

public class Tilesheet implements Image {

	private String name;
	private BufferedImage image;
	
	private HashMap<Point, ImageIcon> tiles = new HashMap<Point, ImageIcon>();
	
	public Tilesheet(String name) {
		this.name = name.substring(0, name.lastIndexOf("."));
		
		try {
			image = ResourceUtil.getBufferedImage("tilesheet/" + this.name.toLowerCase());
			
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
	
	public ImageIcon getImage() {
		return new ImageIcon(image);
	}
	
	public ImageIcon getImage(int width, int height) {
		return ResourceUtil.resizeImage(getImage(), width, height);
	}
}