package me.nrubin29.core;

import java.awt.Window;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthLookAndFeel;

import me.nrubin29.core.data.DataManager;
import me.nrubin29.core.data.LocalizationManager;
import me.nrubin29.core.data.LocalizationManager.Language;
import me.nrubin29.core.data.files.Settings;
import me.nrubin29.core.entity.Player;
import me.nrubin29.core.gui.ErrorPopup;
import me.nrubin29.core.gui.Menu;
import me.nrubin29.core.laf.LookAndFeelFactory;
import me.nrubin29.core.script.ScriptParser;
import me.nrubin29.core.server.Session;
import me.nrubin29.core.server.packet.handler.PacketHandlerManager;
import me.nrubin29.core.tile.TilesheetManager;
import me.nrubin29.core.util.Constants;
import me.nrubin29.core.util.FontUtil;

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

    	DataManager.getInstance().setup();
        LocalizationManager.getInstance().setCurrentLanguage(Language.valueOf(DataManager.getInstance().getConfigurationFile(Settings.class).get("language")));
        PacketHandlerManager.getInstance().setup();
        TilesheetManager.getInstance().setup();
        ScriptParser.getInstance().setup();
        FontUtil.getFont();
        
        Session.getInstance().setLocalPlayer(new Player(name, Constants.START_LOCATION));
        
        new Menu();
    }
    
    public static void main(String[] args) {
    	new Main(args[0]);
    }
}