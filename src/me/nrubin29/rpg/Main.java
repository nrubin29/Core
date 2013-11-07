package me.nrubin29.rpg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import me.nrubin29.rpg.gui.ErrorGUI;
import me.nrubin29.rpg.gui.GUI;
import me.nrubin29.rpg.gui.SplashScreen;
import me.nrubin29.rpg.gui.StatusBar;
import me.nrubin29.rpg.keycommands.KeyCommandManager;
import me.nrubin29.rpg.map.Maps;
import me.nrubin29.rpg.quest.Quests;
import me.nrubin29.rpg.tile.TilesheetManager;
import me.nrubin29.rpg.util.Constants;

public class Main {

	private static JFrame frame;
    private static GUI gui;
    private static StatusBar sb;

    public static void main(String[] args) {
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
    	
    	TilesheetManager.getInstance().setup();
    	KeyCommandManager.getInstance().setup();
    	
    	final SplashScreen splash = new SplashScreen();
    	
        frame = new JFrame(Constants.NAME + " v" + Constants.VERSION);
        
        frame.setBackground(Constants.BACKGROUND_COLOR);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(Constants.ACTUAL_DIMENSION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        sb = new StatusBar();
        gui = new GUI();
        
        Box box = new Box(BoxLayout.Y_AXIS);
        box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        box.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        box.add(Box.createVerticalGlue());
        box.add(sb);
        box.add(gui);
        box.add(Box.createVerticalGlue());
        frame.add(box);

        getGUI().renderMap(Maps.SAMPLE_CITY); //TODO: Temp
        Quests.SAMPLE.getInstance().startQuest(); //TODO: Temp?
        
        new Timer(3 * 1000, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		splash.dispose();
        		frame.setVisible(true);
        	}
        }).start();
    }
    
    public static JFrame getFrame() {
    	return frame;
    }

    public static GUI getGUI() {
        return gui;
    }
    
    public static StatusBar getStatusBar() {
    	return sb;
    }
}