package me.nrubin29.rpg.core.data;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

import me.nrubin29.rpg.core.data.files.Keys;
import me.nrubin29.rpg.core.data.files.PlayerData;
import me.nrubin29.rpg.core.util.Constants;

public final class DataManager {
	
	private DataManager() { }
	
	private static DataManager instance = new DataManager();
	
	public static DataManager getInstance() {
		return instance;
	}
	
	private ArrayList<DataFile> files = new ArrayList<DataFile>();
	
	public void setup() {
		files.add(new Keys());
		files.add(new PlayerData());
	}
	
	@SuppressWarnings("unchecked")
	public <E extends DataFile> E getConfigurationFile(Class<? extends DataFile> target) {
		for (DataFile config : files) {
			if (config.getClass().equals(target)) return (E) config;
		}
		
		return null;
	}
	
	public File getRootFolder() {
		String homedir = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
		String osname = System.getProperty("os.name").toLowerCase();
		File rootFolder;
			
		if (osname.startsWith("mac")) rootFolder = new File(homedir + "/Library/Application Support/" + Constants.NAME);
		else if (osname.startsWith("linux")) rootFolder = new File(homedir + "/." +  Constants.NAME + "/");
		else if (osname.startsWith("win")) rootFolder = new File(System.getenv("APPDATA") + "\\." + Constants.NAME + "\\");
		else throw new RuntimeException("Unsupported OS: " + osname);

        if (!rootFolder.exists()) {

            boolean success = false;

            try { success = rootFolder.mkdir(); }
            catch (Exception e) { System.out.println("Could not create folder."); } //TODO: Using SOP.

            if (!success) System.out.println("Could not create folder.");
        }
        
        return rootFolder;
	}
	
	public File getFile(String folder, String name, boolean createIfNotExists) {
		File rootFolder = DataManager.getInstance().getRootFolder();

        if (!folder.equals("")) DataManager.getInstance().getFolder(folder, true);
		
		File f = new File((!folder.equals("") ? new File(rootFolder, folder) : rootFolder), name);
		
		if (!f.exists() && createIfNotExists) {
			boolean s = false;
		    	
		    try { s = f.createNewFile(); }
		    catch (Exception e) { System.out.println("Could not create file."); }
		    	
		    if (!s) System.out.println("Could not create file.");
		}
		
		return f;
	}
	
	public File getFolder(String name, boolean createIfNotExists) {
		File folder = new File(getRootFolder(), name);

        if (!folder.exists()) folder.mkdir();
        
        return folder;
	}
}