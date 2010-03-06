package weigl.plot.plottable;

import java.awt.BasicStroke;
import java.awt.Color;

import weigl.plot.CoordinateSystem;

public class Grid extends AbstractPlottable {
    private double deltaY = 1;
    private double deltaX = 1;

    public Grid() {
	setColor(Color.LIGHT_GRAY);
	setStroke(new BasicStroke(0.5F));
    }

    @Override
    protected void draw(CoordinateSystem p) {
	for (double x = 0; x <= p.getMaxX(); x += deltaX)
	    p.line(x, p.getMinY(), x, p.getMaxY());

	for (double y = 0; y <= p.getMaxY(); y += deltaY)
	    p.line(p.getMinX(), y, p.getMaxX(), y);

	for (double x = 0; x >= p.getMinX(); x -= deltaX)
	    p.line(x, p.getMinY(), x, p.getMaxY());

	for (double y = 0; y >= p.getMinY(); y -= deltaY)
	    p.line(p.getMinX(), y, p.getMaxX(), y);
    }

    public double getDeltaY() {
	return deltaY;
    }

    public void setDeltaY(double deltaY) {
	this.deltaY = deltaY;
    }

    public double getDeltaX() {
	return deltaX;
    }

    public void setDeltaX(double deltaX) {
	this.deltaX = deltaX;
    }

}