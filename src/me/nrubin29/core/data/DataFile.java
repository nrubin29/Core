package me.nrubin29.core.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import me.nrubin29.core.util.ResourceUtil;

public class DataFile {

	private final String fileName, folderName;
	private final HashMap<String, String> contents = new HashMap<String, String>();

    public DataFile(String fileName) {
        this("", fileName);
    }

	public DataFile(String folderName, String fileName) {
        this.folderName = folderName;
		this.fileName = fileName;
		
		try {
            File file = DataManager.getInstance().getFile(folderName, fileName + ".config", false);
			
			if (!file.exists()) {
				file.createNewFile();
				
				InputStream in = ResourceUtil.getResourceAsStream("files/" + (!folderName.equals("") ? folderName + "/" : "") + fileName + ".config");
				
				if (in == null) return;

				ArrayList<String> lines = new ArrayList<String>();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				
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
				contents.put(currentLine.substring(0, currentLine.indexOf(":")), currentLine.substring(currentLine.indexOf(":") + 2).replaceAll("%20", " "));
			}
			
			reader.close();
		}
		catch (Exception ignored) { }
	}
	
	public final void save() {
		try {
			File file = DataManager.getInstance().getFile(folderName, fileName + ".config", false);
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for (Entry<String, String> entry : contents.entrySet()) {
				writer.write(entry.getKey() + ": " + entry.getValue());
				writer.newLine();
			}
			
			writer.close();
		}
		catch (Exception ignored) { }
	}
	
	public final String get(String key) {
		return contents.get(key);
	}
	
	public final void set(String key, Object value) {
		contents.put(key, String.valueOf(value));
        save();
	}
}