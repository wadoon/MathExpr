package weigl.plot.plottable;

import java.awt.Font;

import weigl.plot.CoordinateSystem;

public class Text extends Cross {
    private static final Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 8);
    private String text;

    public Text(double x, double y, String string) {
	super(x, y);
	this.text = string;
    }

    @Override
    protected void draw(CoordinateSystem p) {
	p.getGraphics().setFont(FONT.deriveFont((float) getSize()));
	p.text(getX(), getY(), text);
    }
}
