package weigl.plot.plottable;

import java.awt.BasicStroke;
import java.awt.Color;

import weigl.plot.CoordinateSystem;

public class Coordinates extends Grid {

    public Coordinates() {
	setStroke(new BasicStroke(1.8F));
	setColor(Color.DARK_GRAY);
    }

    @Override
    protected void draw(CoordinateSystem p) {
	// Horizontal
	p.line(p.getMinX(), 0, p.getMaxX(), 0);
	// Vertical
	p.line(0, p.getMinY(), 0, p.getMaxY());

	double length = 1 / 4D;
	
	for (double i = getDeltaX(); i <= p.getMaxX(); i+=getDeltaX()) {
	    p.line(i, length, i, -length);
	    p.text(i - 0.1, -length * 2, "+" + i);
	}

	for (double i = getDeltaX(); i >= p.getMinX(); i-=getDeltaX()) {
	    p.line(i, length, i, -length);
	    p.text(i - 0.1, -length * 2, "" + i);
	}

	for (double i = getDeltaY(); i <= p.getMaxY(); i+=getDeltaY()) {
	    p.line(-length, i, length, i);
	    p.text(-length * 3, i, "+" + i);
	}

	for (double i = getDeltaY(); i >= p.getMinY(); i-=getDeltaY()) {
	    p.line(-length, i, length, i);
	    p.text(-length * 3, i, "" + i);
	}
    }
}