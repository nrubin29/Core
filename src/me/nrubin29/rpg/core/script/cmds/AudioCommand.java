package me.nrubin29.rpg.core.script.cmds;

import me.nrubin29.rpg.core.audio.AudioPlayer;
import me.nrubin29.rpg.core.audio.SoundEffect;
import me.nrubin29.rpg.core.script.ScriptCommand;

public class AudioCommand extends ScriptCommand {

	/*
	 * audio play name
	 */
	public void parse(String[] args) {
		if (args.length < 2) return;
		
		SoundEffect sound;
		
		try {
			sound = SoundEffect.valueOf(args[1]);
		}
		catch (Exception e) { return; }
		
		if (args[0].equalsIgnoreCase("play")) {
			AudioPlayer.getInstance().playSoundEffect(sound);
		}
	}
}