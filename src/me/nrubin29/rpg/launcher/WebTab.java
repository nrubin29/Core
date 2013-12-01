package me.nrubin29.rpg.launcher;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class WebTab extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public WebTab() {
        final JWebBrowser browser = new JWebBrowser();
        browser.setBarsVisible(false);
        browser.setButtonBarVisible(false);
        browser.setLocationBarVisible(false);
        browser.setMenuBarVisible(false);
        browser.setStatusBarVisible(false);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                browser.navigate("http://rpg-core-news.tumblr.com/");
            }
        });
	    
	    JScrollPane scroll = new JScrollPane(browser);
	    
	    scroll.setPreferredSize(new Dimension(610 + 160, 320));
		
		add(scroll);
	}
}