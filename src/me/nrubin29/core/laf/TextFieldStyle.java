package me.nrubin29.core.laf;

import java.awt.Color;
import java.awt.Font;

import javax.swing.plaf.synth.ColorType;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthStyle;

import me.nrubin29.core.util.FontUtil;

public class TextFieldStyle extends SynthStyle {

	@Override
	protected Color getColorForState(SynthContext c, ColorType color) {
		if (color == ColorType.BACKGROUND) return Color.DARK_GRAY;
		else return Color.WHITE;
	}

	@Override
	protected Font getFontForState(SynthContext c) {
		return FontUtil.getFont();
	}
	
	@Override
	public void installDefaults(SynthContext c) {
		super.installDefaults(c);
		c.getComponent().setBorder(new BlockBorder(Color.DARK_GRAY, 8));
	}
}