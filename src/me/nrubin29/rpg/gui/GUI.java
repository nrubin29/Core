package me.nrubin29.rpg.gui;

import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import me.nrubin29.rpg.audio.AudioPlayer;
import me.nrubin29.rpg.events.Event.EventType;
import me.nrubin29.rpg.events.EventManager;
import me.nrubin29.rpg.keycommands.KeyCommandManager;
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
	
	private Map map;
	private Maps mapsEnumConst;
	private JLabel playerLabel;
	private Sprite player;
	private boolean enableInput = true;

    public GUI() {
        setPreferredSize(Constants.PANEL_DIMENSION);
		setMinimumSize(Constants.PANEL_DIMENSION);
		setMaximumSize(Constants.PANEL_DIMENSION);
    }

    public void renderMap(final Map map) {
    	this.map = map;
    	this.mapsEnumConst = map.getMapsEnumConstant();
    	
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
        
        player = Sprites.PLAYER.newInstance();
        playerLabel = new JLabel(player.getImage(Direction.DOWN, false));
        playerLabel.setBounds(5 * Constants.TILE_DIM, 5 * Constants.TILE_DIM, Constants.TILE_DIM, Constants.TILE_DIM);
        add(playerLabel, Constants.SPRITE_LAYER);
        
        KeyCommandManager.getInstance().setup();
        
        AudioPlayer.getInstance().setBackgroundMusic(map.getBackgroundMusic());
    }
    
    public void setInputEnabled(boolean enableInput) {
    	this.enableInput = enableInput;
    }
    
    public boolean isInputEnabled() {
    	return enableInput;
    }
    
    public Maps getCurrentMapsEnumConst() {
    	return mapsEnumConst;
    }
    
    public JLabel getPlayerLabel() {
    	return playerLabel;
    }
    
    public Sprite getPlayer() {
    	return player;
    }
    
    public void movement(int keyID) {
    	if (!enableInput) return;
    	
    	boolean didMove = false;
    	final Direction newDirection = Direction.valueOf(keyID);
    	
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

        if (didMove) EventManager.getInstance().checkEvents(mapsEnumConst, playerLabel.getLocation(), EventType.MOVE);
    }
}