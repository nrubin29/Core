package me.nrubin29.rpg.core.laf;

import java.awt.Color;
import java.awt.Font;

import javax.swing.plaf.synth.ColorType;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthStyle;

import me.nrubin29.rpg.core.util.FontUtil;

public class DefaultStyle extends SynthStyle {

	@Override
	protected Color getColorForState(SynthContext c, ColorType color) {
		if (color == ColorType.BACKGROUND) return Color.GRAY;
		else return Color.WHITE;
	}

	@Override
	protected Font getFontForState(SynthContext c) {
		return FontUtil.getFont();
	}
}