package me.nrubin29.rpg.audio;

import javazoom.jl.player.Player;
import me.nrubin29.rpg.Main;

public class AudioPlayer {
	
	private AudioPlayer() { }
	
	private static AudioPlayer instance = new AudioPlayer();
	
	public static AudioPlayer getInstance() {
		return instance;
	}
	
	private Player bg;
	
	public void setBackgroundMusic(final Music audio) {
		new Thread(new Runnable() { // TODO: SwingWorker
			public void run() {
				try {
					bg = createPlayer(audio);
					bg.play();
					bg.close();
				}
				catch (Exception e) { e.printStackTrace(); }
			}
		}).start();
	}
	
	public void stopBackgroundMusic() {
		if (bg != null) bg.close();
	}
	
	public void playSoundEffect(SoundEffect audio) {
		try {
			Player player = createPlayer(audio);
			player.play();
			player.close();
		}
		catch (Exception e) { }
	}
	
	public Player createPlayer(Audio audio) {
		try { return new Player(Main.class.getClassLoader().getResource(audio.getPath()).openStream()); }
		catch (Exception e) { return null; }
	}
}