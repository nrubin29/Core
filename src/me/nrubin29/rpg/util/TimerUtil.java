package me.nrubin29.rpg.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingWorker;
import javax.swing.Timer;

public class TimerUtil {

	public static void runTimer(int duration, final Runnable run) {
		Timer t = new Timer(duration, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run.run();
			}
		});
		
		t.setRepeats(false);
		t.start();
	}
	
	public static void animate(Runnable run) {
		runTimer(Constants.ANIMATION_DURATION, run);
	}
	
	public static void runInBackground(final Runnable run) {
		SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {
				run.run();
				return null;
			}
		};
		
		sw.execute();
	}
}