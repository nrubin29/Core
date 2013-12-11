package me.nrubin29.rpg.gui;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import me.nrubin29.rpg.events.EventManager;
import me.nrubin29.rpg.events.Event.EventType;
import me.nrubin29.rpg.map.Map;
import me.nrubin29.rpg.map.Maps;
import me.nrubin29.rpg.sprite.Sprite;
import me.nrubin29.rpg.sprite.Sprites;
import me.nrubin29.rpg.tile.Row;
import me.nrubin29.rpg.tile.Tile;
import me.nrubin29.rpg.util.Constants;
import me.nrubin29.rpg.util.MapTileUtil.Direction;
import me.nrubin29.rpg.util.TimerUtil;

public class GUI extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	
	private JFrame rootFrame;
	private JLabel playerLabel;
	private boolean enableInput = true;

    public GUI(JFrame rootFrame) {
        this.rootFrame = rootFrame;
        
        setPreferredSize(Constants.PANEL_DIMENSION);
    }

    public void renderMap(final Maps maps) {
    	final Map map = maps.getInstance();
    	
        removeAll();

        for (int j = 0; j < Constants.NUM_ROWS; j++) {
        	
        	Row row = map.getRow(j);
        	for (int i = 0; i < Constants.TILES_PER_ROW; i++) {
        		
        		JLabel backgroundTile = new JLabel(map.getType().getBackgroundTile().getImage());
            	backgroundTile.setBounds(i * Constants.TILE_DIM, j * Constants.TILE_DIM, backgroundTile.getIcon().getIconWidth(), backgroundTile.getIcon().getIconHeight());
            	add(backgroundTile, Constants.BACKGROUND_LAYER);
        		
        		Tile t = row.tileAt(i);
        		if (t != Tile.EMPTY) {
        			JLabel tile = new JLabel(t.getImage());
                  	tile.setBounds(i * Constants.TILE_DIM, j * Constants.TILE_DIM, t.getImage().getIconWidth(), t.getImage().getIconHeight());
                  	add(tile, Constants.TILE_LAYER);
        		}
        	}
        }
        
        final Sprite player = Sprites.PLAYER.newInstance();

        playerLabel = new JLabel(player.getImage(Direction.DOWN, false));
        playerLabel.setBounds(5 * Constants.TILE_DIM, 5 * Constants.TILE_DIM, Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);
        add(playerLabel, Constants.SPRITE_LAYER);

        rootFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            		final Direction d = player.getCurrentDirection();
            		
            		EventManager.getInstance().checkEvents(maps, new Point(playerLabel.getX() + d.getMovement().x, playerLabel.getY() + d.getMovement().y), EventType.INTERACT);
            		
            		return;
            	}
            	
            	if (!enableInput) return;
            	
            	boolean didMove = false;
            	final Direction newDirection = Direction.valueOf(e);
            	
            	if (newDirection == null) return;
            	
            	playerLabel.setIcon(player.getImage(newDirection, true));
            	
            	final Point to = new Point(playerLabel.getX() + newDirection.getMovement().x, playerLabel.getY() + newDirection.getMovement().y);
            	
            	if (to.getX() >= 0 &&
            			to.getX() <= Constants.PANEL_DIMENSION.getWidth() - Constants.TILE_DIM &&
            			to.getY() >= 0 &&
            			to.getY() <= Constants.PANEL_DIMENSION.getHeight() - Constants.TILE_DIM &&
            			
            			!map.getRow(to.y / Constants.TILE_DIM).tileAt(to.x / Constants.TILE_DIM).isSolid()) {
            		
            		playerLabel.setLocation(to);
            		
            		didMove = true;
            	}
            	
            	TimerUtil.animate(new Runnable() {
                	public void run() {
                        playerLabel.setIcon(player.getImage(newDirection, false));
                	}
                });

                if (didMove) EventManager.getInstance().checkEvents(maps, playerLabel.getLocation(), EventType.MOVE);
            }
        });
    }
    
    public void setInputEnabled(boolean enableInput) {
    	this.enableInput = enableInput;
    }
    
    public JLabel getPlayerLabel() {
    	return playerLabel;
    }
}