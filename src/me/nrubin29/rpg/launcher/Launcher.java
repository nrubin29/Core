package me.nrubin29.rpg.launcher;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class Launcher extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private LogWindow log = new LogWindow();
	
	public Launcher() {
		super("RPG-Core Launcher");
		
		log.write("Loading...");
		
		Image icon = new ImageIcon(Launcher.class.getClassLoader().getResource("res/logo.png")).getImage();
		
		JLabel logo = new JLabel(new ImageIcon(icon.getScaledInstance(icon.getWidth(this) / 2, icon.getHeight(this) / 2, 0)));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JTextArea news = new JTextArea();
		news.setEditable(false);
		
		final JTextField name = new JTextField();
		
		JButton play = new JButton("Play");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Process p = new ProcessBuilder("java", "-jar", getFile("game.jar").getPath(), name.getText()).start();
					
					log.setProcess(p);
					
					//System.exit(0);
				}
				catch (Exception ex) { ex.printStackTrace(); }
			}
		});
		
		JButton logButton = new JButton("Log");
		logButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.setVisible(true);
			}
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(play);
		buttonPanel.add(logButton);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.setMaximumSize(new Dimension(800, 40));
		bottomPanel.add(name);
		bottomPanel.add(buttonPanel);
		
		add(logo);
		add(new JScrollPane(news));
		add(bottomPanel);
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(new Dimension(800, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
        log.write("Done!");
	}
	
	private final File getFile(String name) {
		String homedir = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
		String osname = System.getProperty("os.name").toLowerCase();
		File rootFolder;
			
		if (osname.startsWith("mac")) rootFolder = new File(homedir + "/Library/Application Support/RPG-Engine");
		else if (osname.startsWith("linux")) rootFolder = new File(homedir + "/.RPG-Engine/");
		else if (osname.startsWith("win")) rootFolder = new File(System.getenv("APPDATA") + "\\.RPG-Engine\\");
		else throw new RuntimeException("Unsupported OS: " + osname);

        if (!rootFolder.exists()) {

            boolean success = false;

            try { success = rootFolder.mkdir(); }
            catch (Exception e) { log.write("Could not create folder."); }

            if (!success) log.write("Could not create folder.");
        }
		
		File f = new File(rootFolder, name);
		
		return f;
	}
	
	public static void main(String[] args) {
        new Launcher();
    }
}