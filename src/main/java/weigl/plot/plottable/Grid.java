package weigl.plot.plottable;

import java.awt.BasicStroke;
import java.awt.Color;

import weigl.plot.CoordinateSystem;

public class Grid extends AbstractPlottable {

    public Grid() {
	setColor(Color.LIGHT_GRAY);
	setStroke(new BasicStroke(0.5F));
    }

    @Override
    protected void draw(CoordinateSystem p) {
	for (double x = 0; x <= p.getMaxX(); x++)
	    p.line(x, p.getMinY(), x, p.getMaxY());

	for (double y = 0; y <= p.getMaxY(); y++)
	    p.line(p.getMinX(), y, p.getMaxX(), y);

	for (double x = 0; x >= p.getMinX(); x--)
	    p.line(x, p.getMinY(), x, p.getMaxY());

	for (double y = 0; y >= p.getMinY(); y--)
	    p.line(p.getMinX(), y, p.getMaxX(), y);
    }
}