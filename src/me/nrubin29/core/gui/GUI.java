package me.nrubin29.core.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import me.nrubin29.core.audio.AudioPlayer;
import me.nrubin29.core.audio.SoundEffect;
import me.nrubin29.core.entity.Player;
import me.nrubin29.core.event.EventManager;
import me.nrubin29.core.event.Event.EventType;
import me.nrubin29.core.keycommand.KeyCommandManager;
import me.nrubin29.core.map.Direction;
import me.nrubin29.core.map.Map;
import me.nrubin29.core.script.ScriptParser;
import me.nrubin29.core.server.ServerConnector;
import me.nrubin29.core.server.Session;
import me.nrubin29.core.server.packet.packet.PacketMove;
import me.nrubin29.core.tile.Row;
import me.nrubin29.core.tile.Tile;
import me.nrubin29.core.util.Constants;
import me.nrubin29.core.util.ThreadUtil;

public final class GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private GUI() { }
	
	private static GUI instance = new GUI();
	
	public static GUI getInstance() {
		return instance;
	}
	
	private JFrame frame;
	
	private Map map;
	
	private ArrayList<Notification> ncs = new ArrayList<Notification>();

    public void create() {
    	frame = new JFrame(Constants.NAME + " v" + Constants.VERSION);
        frame.setSize(Constants.GAME_DIMENSION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        Box box = new Box(BoxLayout.Y_AXIS);
        box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        box.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        box.add(Box.createVerticalGlue());
        box.add(this);
        box.add(Box.createVerticalGlue());
        frame.add(box);
    	
        setPreferredSize(Constants.GAME_DIMENSION);
		setMinimumSize(Constants.GAME_DIMENSION);
		setMaximumSize(Constants.GAME_DIMENSION);
    }

    public void setMap(Map map) {
    	this.map = map;
        
        KeyCommandManager.getInstance().setup();
        
        AudioPlayer.getInstance().setBackgroundMusic(map.getBackgroundMusic());
        
        ScriptParser.getInstance().parse("event move 0 0 popup show image message/achievement give hunt"); //TODO: Just a test - IT WORKS!
    }
    
    public void addNotification(Notification n) {
    	ncs.add(n);
    }
    
    public void removeNotification(Notification n) {
    	ncs.remove(n);
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	for (int j = 0; j < Constants.NUM_ROWS; j++) {
        	Row row = map.getRow(j);
        	for (int i = 0; i < Constants.TILES_PER_ROW; i++) {
        		g.drawImage(map.getType().getBackgroundTile().getImage().getImage(), i * Constants.TILE_DIM, j * Constants.TILE_DIM, this);
        		
        		Tile t = row.tileAt(i);
        		if (t != Tile.EMPTY) {
        			g.drawImage(t.getImage().getImage(), i * Constants.TILE_DIM, j * Constants.TILE_DIM, this);
        		}
        	}
        }
        
        for (Player player : Session.getInstance().getPlayers()) {
        	g.drawImage(player.getCurrentImage().getImage(), player.getLocation().x, player.getLocation().y, this);
        }
        
        for (int i = 0; i < ncs.size(); i++) {
        	Notification nc = ncs.get(i);
        	
        	g.setColor(Constants.TRANSLUCENT);
        	g.fillRect(Constants.GAME_DIMENSION.width - Constants.NOTIFICATION_DIMENSION.width, i * Constants.NOTIFICATION_DIMENSION.height, Constants.NOTIFICATION_DIMENSION.width, Constants.NOTIFICATION_DIMENSION.height);
        	
        	g.setColor(Color.WHITE);
        	
        	g.drawString(nc.getTitle(), Constants.GAME_DIMENSION.width - Constants.NOTIFICATION_DIMENSION.width, (i == 0 ? g.getFontMetrics().getHeight() : i * Constants.NOTIFICATION_DIMENSION.height));
        	g.drawString(nc.getMessage(), Constants.GAME_DIMENSION.width - Constants.NOTIFICATION_DIMENSION.width, g.getFontMetrics().getHeight() + (g.getFontMetrics().getHeight() / 2) + (i == 0 ? g.getFontMetrics().getHeight() : i * Constants.NOTIFICATION_DIMENSION.height));
        }
        
        repaint();
    }
    
    public JFrame getFrame() {
    	return frame;
    }
    
    public Map getCurrentMap() {
    	return map;
    }
    
    public void movement(final Player player, final Direction d, boolean local) {
    	boolean didMove = false;
        player.setCurrentImage(d, true);
    	
    	final Point to = new Point(player.getLocation().x + d.getMovement().x, player.getLocation().y + d.getMovement().y);
    	
    	if (to.getX() >= 0 &&
    			to.getX() <= Constants.GAME_DIMENSION.getWidth() - Constants.TILE_DIM &&
    			to.getY() >= 0 &&
    			to.getY() <= Constants.GAME_DIMENSION.getHeight() - Constants.TILE_DIM &&
    			
    			!map.getRow(to.y / Constants.TILE_DIM).tileAt(to.x / Constants.TILE_DIM).isSolid()) {
    		
    		player.setLocation(to);
    		
    		didMove = true;
    	}
    	
    	ThreadUtil.animate(new Runnable() {
        	public void run() {
                player.setCurrentImage(d, false);
        	}
        });

        if (local) {
            ServerConnector.getInstance().sendPacket(new PacketMove(Session.getInstance().getLocalPlayer().getName(), d, to.x, to.y));
            
            if (didMove) {
            	EventManager.getInstance().checkEvents(map, player.getLocation(), EventType.MOVE);
            }
            
            else {
            	AudioPlayer.getInstance().playSoundEffect(SoundEffect.BUMP);
            }
        }
    }
}