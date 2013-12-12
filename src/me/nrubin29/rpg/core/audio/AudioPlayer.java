package me.nrubin29.rpg.core.audio;

import javazoom.jl.player.Player;
import me.nrubin29.rpg.core.util.ResourceUtil;
import me.nrubin29.rpg.core.util.ThreadUtil;

public class AudioPlayer {
	
	private AudioPlayer() { }
	
	private static AudioPlayer instance = new AudioPlayer();
	
	public static AudioPlayer getInstance() {
		return instance;
	}
	
	private Player bg;
	
	public void setBackgroundMusic(final Music audio) {
		ThreadUtil.runThreadInBackground(new Runnable() {
			public void run() {
				try {
					bg = createPlayer(audio);
					bg.play();
					bg.close();
				}
				catch (Exception e) { e.printStackTrace(); }
			}
		}, true);
	}
	
	public void stopBackgroundMusic() {
		if (bg != null) bg.close();
	}
	
	public void playSoundEffect(final SoundEffect audio) {
		ThreadUtil.runThreadInBackground(new Runnable() {
			public void run() {
				try {
					Player player = createPlayer(audio);
					player.play();
					player.close();
				}
				catch (Exception e) { }
			}
		}, false);
	}
	
	public Player createPlayer(Audio audio) {
		try { return new Player(ResourceUtil.getResource(audio.getPath()).openStream()); }
		catch (Exception e) { return null; }
	}
}