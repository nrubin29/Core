package me.nrubin29.rpg.core.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.filechooser.FileSystemView;

import me.nrubin29.rpg.core.Game;
import me.nrubin29.rpg.core.util.Data;

public class DataFile {

	private final String fileName;
	private final HashMap<String, String> contents = new HashMap<String, String>();
	
	public DataFile(String fileName) {
		this.fileName = fileName;
		
		try {
			File file = getFile(fileName + ".config", false);
			
			if (!file.exists()) {
                file.createNewFile();
				
				File template = new File(Game.class.getClassLoader().getResource("res/files/" + fileName + ".config").toURI());
				
				ArrayList<String> lines = new ArrayList<String>();
				
				BufferedReader reader = new BufferedReader(new FileReader(template));
				
				while (reader.ready()) {
					lines.add(reader.readLine());
				}
				
				reader.close();
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				
				for (String line : lines) {
					writer.write(line);
					writer.newLine();
				}
				
				writer.close();
			}
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			while (reader.ready()) {
				String currentLine = reader.readLine();
				contents.put(currentLine.substring(0, currentLine.indexOf(":")), currentLine.substring(currentLine.indexOf(":") + 2));
			}
			
			reader.close();
		}
		catch (Exception ignored) { }
	}
	
	public final void save() {
		try {
			File file = getFile(fileName + ".config", false);
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for (Entry<String, String> entry : contents.entrySet()) {
				writer.write(entry.getKey() + ": " + entry.getValue());
				writer.newLine();
			}
			
			writer.close();
		}
		catch (Exception ignored) { }
	}
	
	public final String getValue(String key) {
		return contents.get(key);
	}
	
	public final void set(String key, Object value) {
		contents.put(key, String.valueOf(value));
        save();
	}
	
	private final File getFile(String name, boolean createIfNotExists) {
		String homedir = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
		String osname = System.getProperty("os.name").toLowerCase();
		File rootFolder;
			
		if (osname.startsWith("mac")) rootFolder = new File(homedir + "/Library/Application Support/" + Data.NAME);
		else if (osname.startsWith("linux")) rootFolder = new File(homedir + "/." +  Data.NAME + "/");
		else if (osname.startsWith("win")) rootFolder = new File(System.getenv("APPDATA") + "\\." + Data.NAME + "\\");
		else throw new RuntimeException("Unsupported OS: " + osname);

        if (!rootFolder.exists()) {

            boolean success = false;

            try { success = rootFolder.mkdir(); }
            catch (Exception e) { System.out.println("Could not create folder."); }

            if (!success) System.out.println("Could not create folder.");
        }
		
		File f = new File(rootFolder, name);
		
		if (!f.exists() && createIfNotExists) {
			boolean s = false;
		    	
		    try { s = f.createNewFile(); }
		    catch (Exception e) { System.out.println("Could not create file."); }
		    	
		    if (!s) System.out.println("Could not create file.");
		}
		
		return f;
	}
}