package me.nrubin29.rpg.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimerUtil {
	
	public static Timer runInBackground(int delay, boolean repeats, final Runnable run) {
		Timer t = new Timer(delay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run.run();
			}
		});
		
		t.setRepeats(repeats);
		t.start();
		
		return t;
	}

	public static void animate(final Runnable run) {
		runInBackground(Constants.ANIMATION_DURATION, false, run);
	}
}