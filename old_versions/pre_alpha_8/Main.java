package me.nrubin29.rpg;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import me.nrubin29.rpg.gui.GUI;
import me.nrubin29.rpg.gui.SplashScreen;
import me.nrubin29.rpg.gui.StatusBar;
import me.nrubin29.rpg.map.Maps;
import me.nrubin29.rpg.quest.Quests;
import me.nrubin29.rpg.tile.TilesheetLoader;
import me.nrubin29.rpg.util.Constants;

/*
 * TODO:
 * - Satisfy all TODOs
 * - Audio System
 * 		- Background Music
 * 		- SFX
 * - Achievements
 * - More Tiles
 * - Improve Walk Cycle
 * - NPCs
 * 		- Talking
 * - Localization
 * - Utility method for making events that jump to other maps
 * - Pause Screen
 * - Items
 * 		- Shops
 * - Multiplayer
 * - Background Tasks (food depletes, etc.)
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
 */
public class Main {

    private static GUI gui;
    private static StatusBar sb;

    public static void main(String[] args) {
    	TilesheetLoader.loadTiles();
    	
    	SplashScreen splash = new SplashScreen();
    	
        JFrame frame = new JFrame("RPG Tech Demo v" + Constants.VERSION);
        
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
    }

    public static GUI getGUI() {
        return gui;
    }
    
    public static StatusBar getStatusBar() {
    	return sb;
    }
}