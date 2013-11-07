package me.nrubin29.rpg;

import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import me.nrubin29.rpg.gui.ErrorGUI;
import me.nrubin29.rpg.gui.GUI;
import me.nrubin29.rpg.gui.SplashScreen;
import me.nrubin29.rpg.gui.StatusBar;
import me.nrubin29.rpg.map.Maps;
import me.nrubin29.rpg.quest.Quests;
import me.nrubin29.rpg.tile.TilesheetManager;
import me.nrubin29.rpg.util.Constants;

/*
 * TODO:
 * - Satisfy all TODOs
 * - Audio System
 * 		- Background Music
 * 		- SFX
 * - Improve Walk Cycle
 * - NPCs
 * 		- Talking
 * - Localization
 * - Pause Screen
 * - Items
 * 		- Shops
 * - Multiplayer
 * - Background Tasks (food depletes, etc.)
 * - Debug Menu
 * 
 * DONE:
 * - Tile rendering system
 * 		- Parses tilesheet
 * - Tile event system
 * - Map system
 * - Sprite loading and basic animation
 * - GUI rendering
 * - Splash Screen
 * - Text System (With multi-part)
 * - Custom Font
 * - Status Bar
 * 		- Health Bar
 * 		- Hunger Bar
 * - Enabling / Disabling Input
 * - Achievements
 * - Utility method for making events that jump to other maps
 * - Error Handling
 */
public class Main {

    private static GUI gui;
    private static StatusBar sb;

    public static void main(String[] args) {
    	TilesheetManager.getInstance().setup();
    	
    	SplashScreen splash = new SplashScreen();
    	
        final JFrame frame = new JFrame("RPG Engine v" + Constants.VERSION);
        
        sb = new StatusBar();
        frame.add(sb);
        
        gui = new GUI(frame);
        frame.add(gui);
        
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(Constants.ACTUAL_DIMENSION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        getGUI().renderMap(Maps.SAMPLE_CITY); //TODO: Temp
        Quests.SAMPLE.getInstance().startQuest();
        
        splash.dispose();
        
        frame.setVisible(true);
        
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			public void uncaughtException(Thread th, Throwable e) {
				frame.dispose();
				new ErrorGUI(e);
			}
    	});
    }

    public static GUI getGUI() {
        return gui;
    }
    
    public static StatusBar getStatusBar() {
    	return sb;
    }
}