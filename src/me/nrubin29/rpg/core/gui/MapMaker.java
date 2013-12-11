package me.nrubin29.rpg.core.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import me.nrubin29.rpg.core.audio.Music;
import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.map.MapType;
import me.nrubin29.rpg.core.tile.Tile;
import me.nrubin29.rpg.core.util.Constants;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MapMaker extends JFrame {

	private static final long serialVersionUID = 1L;
	
	Tile selection;
	private ArrayList<GridRow> rows = new ArrayList<GridRow>();
	
	JPanel grid;
	private JTextField name;
	private JComboBox type, bg;
	
	public MapMaker() {
		super("Map Maker");
		
		grid = new JPanel(new GridLayout(Constants.TILES_PER_COLUMN, Constants.TILES_PER_ROW));
		
		for (int i = 0; i < Constants.NUM_ROWS; i++) rows.add(new GridRow(this));
		
		add(grid);
		
		/* */
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		JPanel tilesheet = new JPanel(new GridLayout(8, 5));
		tilesheet.setBorder(BorderFactory.createTitledBorder("Tilesheet"));
		
		for (final Tile t : Tile.values()) {
			if (t == Tile.EMPTY) continue;
			JButton tileButton = new JButton(t.getImage());
			tileButton.setBorderPainted(false);
			tileButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selection = t;
				}
			});
			tilesheet.add(tileButton);
		}
		
		rightPanel.add(new JScrollPane(tilesheet, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		
		/* */
		
		JPanel info = new JPanel();
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		info.setBorder(BorderFactory.createTitledBorder("Map Settings"));
		
		name = new JTextField(8);
		info.add(Menu.createTitledPanel("Map Name", name));
		
		DefaultComboBoxModel model1 = new DefaultComboBoxModel();
		type = new JComboBox(model1);
		for (MapType t : MapType.values()) model1.addElement(t.toString().toLowerCase());
		info.add(Menu.createTitledPanel("Map Type", type));
		
		DefaultComboBoxModel model2 = new DefaultComboBoxModel();
		bg = new JComboBox(model2);
		for (Music m : Music.values()) model2.addElement(m.toString().toLowerCase());
		info.add(Menu.createTitledPanel("Background Music", bg));
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(MapMaker.this, "Do you want to save this map to single player?");
				
				File path = null;
				
				if (res == JOptionPane.YES_OPTION) path = DataManager.getInstance().getFolder("maps", true);
				
				else if (res == JOptionPane.NO_OPTION) path = FileSystemView.getFileSystemView().getHomeDirectory();
				
				if (path != null) {
					File file = DataManager.getInstance().getFile(path.getName(), name.getText() + ".map", true);
					
					exportMap(file);
				}
			}
		});
		info.add(save);
		
		JButton script = new JButton("Script Editor");
		info.add(script);
		
		rightPanel.add(info);
		
		/* */
		
		add(rightPanel);
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		setSize(875, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	private void exportMap(File file) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	        Document doc = docBuilder.newDocument();
	        Element rootElement = doc.createElement("map");
	        doc.appendChild(rootElement);

	        Attr name = doc.createAttribute("name");
	        name.setValue(MapMaker.this.name.getText());
	        rootElement.setAttributeNode(name);
	        
	        Element type = doc.createElement("type");
	        type.setTextContent(MapMaker.this.type.getSelectedItem().toString().toUpperCase());
	        rootElement.appendChild(type);
	        
	        Element bg = doc.createElement("bg");
	        bg.setTextContent(MapMaker.this.bg.getSelectedItem().toString().toUpperCase());
	        rootElement.appendChild(bg);

	        for (GridRow row : rows) {
	        	StringBuffer rowBuffer = new StringBuffer();
	        	
	        	for (GridTile tile : row.getTiles()) {
	        		rowBuffer.append(tile.getTile().getID() + " ");
	        	}
	        	
	            Element questionElement = doc.createElement("row");
	            questionElement.appendChild(doc.createTextNode(rowBuffer.toString()));

	            rootElement.appendChild(questionElement);
	        }

	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result =  new StreamResult(new StringWriter());
	        transformer.transform(source, result);
	        
	        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	        writer.write(result.getWriter().toString());
	        writer.close();
		}
		
		catch (Exception e) { e.printStackTrace(); }
	}
}

class ScriptEditor extends JFrame {

	private static final long serialVersionUID = 1L;
	
}

class GridRow {
	
	public GridRow(MapMaker maker) {
		for (int i = 0; i < Constants.TILES_PER_ROW; i++) {
			GridTile tile = new GridTile(maker);
			tiles.add(tile);
			maker.grid.add(tile.getLabel());
		}
	}
	
	private ArrayList<GridTile> tiles = new ArrayList<GridTile>();
	
	public ArrayList<GridTile> getTiles() {
		return tiles;
	}
}

class GridTile {
	
	private Tile tile;
	private JLabel label;
	
	public GridTile(final MapMaker maker) {
		this.label = new JLabel(MapType.CITY.getBackgroundTile().getImage());
		this.tile = Tile.EMPTY;
		
		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (maker.selection == null) return;
				
				tile = maker.selection;
				
				label.setIcon(GridTile.this.tile.getImage());
				maker.validate();
			}
		});
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public JLabel getLabel() {
		return label;
	}
}