package me.nrubin29.rpg.core.laf;

import javax.swing.JComponent;
import javax.swing.plaf.synth.Region;
import javax.swing.plaf.synth.SynthStyle;
import javax.swing.plaf.synth.SynthStyleFactory;

public class LookAndFeelFactory extends SynthStyleFactory {

	private SynthStyle
			buttonStyle = new ButtonStyle(),
			scrollBarStyle = new ScrollBarStyle(),
			scrollBarThumbStyle = new ScrollBarThumbStyle(),
			textFieldStyle = new TextFieldStyle(),
			defaultStyle = new DefaultStyle()
	;
	
	@Override
	public SynthStyle getStyle(JComponent c, Region r) {
		if (r == Region.BUTTON) {
			return buttonStyle;
		}
		
		if (r == Region.SCROLL_BAR) {
			return scrollBarStyle;
		}
		
		if (r == Region.SCROLL_BAR_THUMB) {
			return scrollBarThumbStyle;
		}
		
		if (r == Region.TEXT_FIELD) {
			return textFieldStyle;
		}
		
		else {
			System.out.println(r.getName() + " does not have a style.");
			return defaultStyle;
		}
	}
}