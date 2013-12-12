package me.nrubin29.rpg.core;

import java.awt.Window;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthLookAndFeel;

import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.LocalizationManager;
import me.nrubin29.rpg.core.data.files.PlayerData;
import me.nrubin29.rpg.core.entity.Player;
import me.nrubin29.rpg.core.gui.ErrorPopup;
import me.nrubin29.rpg.core.gui.Menu;
import me.nrubin29.rpg.core.item.Apple;
import me.nrubin29.rpg.core.laf.LookAndFeelFactory;
import me.nrubin29.rpg.core.script.ScriptParser;
import me.nrubin29.rpg.core.server.Session;
import me.nrubin29.rpg.core.server.packet.handler.PacketHandlerManager;
import me.nrubin29.rpg.core.tile.TilesheetManager;
import me.nrubin29.rpg.core.util.Constants;
import me.nrubin29.rpg.core.util.FontUtil;

public class Main {
	
    public Main(String name) {
    	Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			public void uncaughtException(Thread th, Throwable e) {
				e.printStackTrace();
				
				System.gc();
				for (Window window : Window.getWindows()) {
				    window.dispose();
				}
				
				new ErrorPopup(e);
			}
        });
    	
    	try {
    		UIManager.setLookAndFeel(new SynthLookAndFeel());
    		SynthLookAndFeel.setStyleFactory(new LookAndFeelFactory());
    	}
    	catch (Exception e) { e.printStackTrace(); }
    	
    	if (name == null) System.exit(0);

        LocalizationManager.getInstance().setCurrentLanguage(LocalizationManager.Language.ENGLISH);
        PacketHandlerManager.getInstance().setup();
        DataManager.getInstance().setup();
        TilesheetManager.getInstance().setup();
        ScriptParser.getInstance().setup();
        FontUtil.getFont();
        
        for (int i = 0; i < 5; i++) DataManager.getInstance().<PlayerData>getConfigurationFile(PlayerData.class).addItem(new Apple());
        
        Session.getInstance().setLocalPlayer(new Player(name, Constants.START_LOCATION));
        
        new Menu();
    }
    
    public static void main(String[] args) {
    	new Main(args[0]);
    }
}