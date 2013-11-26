package me.nrubin29.rpg.core.gui;

import java.awt.Point;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import me.nrubin29.rpg.core.audio.AudioPlayer;
import me.nrubin29.rpg.core.entity.Player;
import me.nrubin29.rpg.core.events.Event.EventType;
import me.nrubin29.rpg.core.events.EventManager;
import me.nrubin29.rpg.core.keycommands.KeyCommandManager;
import me.nrubin29.rpg.core.map.Map;
import me.nrubin29.rpg.core.server.ServerConnector;
import me.nrubin29.rpg.core.server.Session;
import me.nrubin29.rpg.core.server.packet.packet.PacketMove;
import me.nrubin29.rpg.core.tile.Row;
import me.nrubin29.rpg.core.tile.Tile;
import me.nrubin29.rpg.core.util.Data;
import me.nrubin29.rpg.core.util.MapTileUtil.Direction;
import me.nrubin29.rpg.core.util.TimerUtil;

public class GUI extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	
	private Map map;
	private boolean enableInput = true;

    private HashMap<Player, JLabel> playerLabels = new HashMap<Player, JLabel>();

    public GUI() {
        setPreferredSize(Data.DIMENSION);
		setMinimumSize(Data.DIMENSION);
		setMaximumSize(Data.DIMENSION);
    }

    public void renderMap(final Map map) {
    	this.map = map;
    	
        removeAll();

        for (int j = 0; j < Data.NUM_ROWS; j++) {
        	
        	Row row = map.getRow(j);
        	for (int i = 0; i < Data.TILES_PER_ROW; i++) {
        		JLabel backgroundTile = new JLabel(map.getType().getBackgroundTile().getImage());
            	backgroundTile.setBounds(i * Data.TILE_DIM, j * Data.TILE_DIM, backgroundTile.getIcon().getIconWidth(), backgroundTile.getIcon().getIconHeight());
            	add(backgroundTile, Data.BACKGROUND_LAYER);
        		
        		Tile t = row.tileAt(i);
        		if (t != Tile.EMPTY) {
        			JLabel tile = new JLabel(t.getImage());
                  	tile.setBounds(i * Data.TILE_DIM, j * Data.TILE_DIM, t.getImage().getIconWidth(), t.getImage().getIconHeight());
                  	add(tile, Data.TILE_LAYER);
        		}
        	}
        }

        for (Player p : Session.getInstance().getPlayers()) {
            JLabel playerLabel = new JLabel(p.getImage(Direction.DOWN, false));
            playerLabel.setBounds(5 * Data.TILE_DIM, 5 * Data.TILE_DIM, Data.TILE_DIM, Data.TILE_DIM);
            add(playerLabel, Data.SPRITE_LAYER);
            playerLabels.put(p, playerLabel);
        }
        
        KeyCommandManager.getInstance().setup();
        
        AudioPlayer.getInstance().setBackgroundMusic(map.getBackgroundMusic());
    }
    
    public void setInputEnabled(boolean enableInput) {
    	this.enableInput = enableInput;
    }
    
    public boolean isInputEnabled() {
    	return enableInput;
    }
    
    public Map getCurrentMap() {
    	return map;
    }

    public JLabel getPlayerLabel(Player player) {
        return playerLabels.get(player);
    }
    
    public void movement(final Player player, int keyID) {
    	if (!enableInput) return;
    	
    	boolean didMove = false;
    	final Direction newDirection = Direction.valueOf(keyID);
        final JLabel playerLabel = playerLabels.get(player);
    	
    	if (newDirection == null) return;
    	
    	playerLabel.setIcon(player.getImage(newDirection, true));
    	
    	final Point to = new Point(playerLabel.getX() + newDirection.getMovement().x, playerLabel.getY() + newDirection.getMovement().y);
    	
    	if (to.getX() >= 0 &&
    			to.getX() <= Data.DIMENSION.getWidth() - Data.TILE_DIM &&
    			to.getY() >= 0 &&
    			to.getY() <= Data.DIMENSION.getHeight() - Data.TILE_DIM &&
    			
    			!map.getRow(to.y / Data.TILE_DIM).tileAt(to.x / Data.TILE_DIM).isSolid()) {
    		
    		playerLabel.setLocation(to);
    		
    		didMove = true;
    	}
    	
    	TimerUtil.animate(new Runnable() {
        	public void run() {
                playerLabel.setIcon(player.getImage(newDirection, false));
        	}
        });

        if (didMove) {
        	EventManager.getInstance().checkEvents(map, playerLabel.getLocation(), EventType.MOVE);
        	ServerConnector.getInstance().sendPacket(new PacketMove("player", to.x, to.y));
        }
    }
}