package me.nrubin29.rpg.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.filechooser.FileSystemView;

import me.nrubin29.rpg.util.Constants;

public class DataFile {

	private final String fileName;
	private final HashMap<String, Object> contents = new HashMap<String, Object>();
	
	public DataFile(String fileName) {
		this.fileName = fileName;
		
		try {
			File file = getFile(fileName + ".config");
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			while (reader.ready()) {
				String currentLine = reader.readLine();
				contents.put(currentLine.substring(0, currentLine.indexOf(":")), currentLine.substring(currentLine.indexOf(":") + 2));
			}
			
			reader.close();
		}
		catch (Exception ignored) { }
	}
	
	public void save() {
		try {
			File file = getFile(fileName + ".config");
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for (Entry<String, Object> entry : contents.entrySet()) {
				writer.write(entry.getKey() + ": " + entry.getValue());
				writer.newLine();
			}
			
			writer.close();
		}
		catch (Exception ignored) { }
	}
	
	@SuppressWarnings("unchecked")
	public final <E> E getValue(String key) {
		return (E) contents.get(key);
	}
	
	public final void set(String key, Object value) {
		contents.put(key, value);
	}
	
	private File getFile(String name) {
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
            catch (Exception e) { System.out.println("Could not create folder."); }

            if (!success) System.out.println("Could not create folder.");
        }
		
		File f = new File(rootFolder, name);
		
		if (!f.exists()) {
			boolean s = false;
		    	
		    try { s = f.createNewFile(); }
		    catch (Exception e) { System.out.println("Could not create file."); }
		    	
		    if (!s) System.out.println("Could not create file.");
		}
		
		return f;
	}
}