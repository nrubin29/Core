package me.nrubin29.rpg.core.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingWorker;
import javax.swing.Timer;

public class ThreadUtil {

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
		runTimer(75, run);
	}
	
	public static void runThreadInBackground(final Runnable run, final boolean loop) {
		Thread th = new Thread(new Runnable() {
			public void run() {
				run.run();
				
				if (loop) run();
			}
		});
		
		th.start();
	}
	
	public static void runGUITaskInBackground(final Runnable run) {
		SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {
				run.run();
				return null;
			}
		};
		
		sw.execute();
	}
}