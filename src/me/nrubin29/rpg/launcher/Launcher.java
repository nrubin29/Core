package me.nrubin29.rpg.launcher;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

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
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileSystemView;

public class Launcher extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private WebTab web = new WebTab();
	private LogTab log = new LogTab();
	
	public Launcher() {
		super("RPG-Core Launcher");
		
		log.write("Loading...");
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Web", web);
		tabs.addTab("Log", log);
		
		Image icon = new ImageIcon(Launcher.class.getClassLoader().getResource("res/logo.png")).getImage();
		
		JLabel logo = new JLabel(new ImageIcon(icon.getScaledInstance(icon.getWidth(this) / 2, icon.getHeight(this) / 2, 0)));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton play = new JButton("Play");
		play.setAlignmentX(Component.CENTER_ALIGNMENT);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ProcessBuilder("java", "-jar", getFile("game.jar").getPath()).start();
					System.exit(0);
				}
				catch (Exception ex) { ex.printStackTrace(); }
			}
		});
		
		
		add(logo);
		add(tabs);
		add(play);
		
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
            catch (Exception e) { System.out.println("Could not create folder."); }

            if (!success) System.out.println("Could not create folder.");
        }
		
		File f = new File(rootFolder, name);
		
		return f;
	}
}