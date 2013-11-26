package me.nrubin29.rpg.core;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.*;

import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.entity.Player;
import me.nrubin29.rpg.core.gui.ErrorGUI;
import me.nrubin29.rpg.core.gui.GUI;
import me.nrubin29.rpg.core.server.ServerConnector;
import me.nrubin29.rpg.core.server.Session;
import me.nrubin29.rpg.core.tile.TilesheetManager;
import me.nrubin29.rpg.core.util.Data;
import me.nrubin29.rpg.core.util.FontUtil;
import me.nrubin29.rpg.core.util.TimerUtil;

public abstract class Game {

	public abstract void onPreEnable();
	
	public abstract void onEnable();
	
	public abstract void onDisable();
	
	private static JFrame frame;
    private static GUI gui;

    public Game(String name, String version) {
    	Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			public void uncaughtException(Thread th, Throwable e) {
				e.printStackTrace(); //TODO: Temp
				
				if (frame != null) {
					frame.setVisible(false);
					frame.dispose();
				}
				
				new ErrorGUI(e);
			}
            	});

        String playerName = JOptionPane.showInputDialog(null, "Please enter your name.");
        String serverPort = JOptionPane.showInputDialog(null, "Please enter your server's secret number.");

        onPreEnable();

        Session.getInstance().setLocalPlayer(new Player(playerName));
        DataManager.getInstance().setup();
    	TilesheetManager.getInstance().setup();
    	FontUtil.getFont();
    	
    	Data.NAME = name;
    	Data.VERSION = version;
    	
        frame = new JFrame(Data.NAME + " v" + Data.VERSION + " (" + Data.ENGINE_NAME + " v" + Data.ENGINE_VERSION + ")");
        frame.setBackground(Data.BACKGROUND_COLOR);
        frame.setSize(Data.DIMENSION);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        frame.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		onDisable();
        		System.exit(0);
        	}
        });
        
        gui = new GUI();
        
        Box box = new Box(BoxLayout.Y_AXIS);
        box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        box.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        box.add(Box.createVerticalGlue());
        box.add(gui);
        box.add(Box.createVerticalGlue());
        frame.add(box);
        
        TimerUtil.runTimer(0, new Runnable() {
        	public void run() {
        		frame.setVisible(true);
        		onEnable();
        	}
        });
        
        ServerConnector.getInstance().initConnection("127.0.0.1:" + serverPort);
    }
    
    public static JFrame getFrame() {
    	return frame;
    }

    public static GUI getGUI() {
        return gui;
    }
}