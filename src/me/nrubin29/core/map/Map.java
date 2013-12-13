package me.nrubin29.core.map;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import me.nrubin29.core.audio.Music;
import me.nrubin29.core.gui.GUI;
import me.nrubin29.core.tile.Row;
import me.nrubin29.core.tile.Tile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public final class Map {
	
	private MapType type;
	private String name;
	private Music backgroundMusic;
	private ArrayList<Row> rows = new ArrayList<Row>();
	
	public Map(String xml) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
            doc.getDocumentElement().normalize();
            
            Element root = (Element) doc.getFirstChild();
            
            this.name = root.getAttribute("name");
            
            this.type = MapType.valueOf(root.getElementsByTagName("type").item(0).getTextContent());
            this.backgroundMusic = Music.valueOf(root.getElementsByTagName("bg").item(0).getTextContent());

            NodeList rowsList = root.getElementsByTagName("row");

            for (int j = 0; j < rowsList.getLength(); j++) {
            	Node rNode = rowsList.item(j);
                
            	if (rNode.getNodeType() == Node.ELEMENT_NODE) {
            		Row r = new Row();
                    Element rowElement = (Element) rNode;
                    
                    String row = rowElement.getTextContent();
                    
                    for (String str : row.split(" ")) {
        				str = str.trim();
        				if (!str.equals("")) {
        					r.addTile(Tile.byID(str));
        				}
        			}
                    
                    rows.add(r);
                }
            }
        }
    	
    	catch (Exception ex) { ex.printStackTrace(); }
	}
	
	public MapType getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public Music getBackgroundMusic() {
		return backgroundMusic;
	}
	
	public Row getRow(int row) {
		return rows.get(row);
	}
	
	public void display() {
		GUI.getInstance().setMap(this);
	}
}