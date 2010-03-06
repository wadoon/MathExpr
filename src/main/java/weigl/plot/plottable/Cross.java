package weigl.plot.plottable;

import java.awt.BasicStroke;
import java.awt.Color;

import weigl.plot.CoordinateSystem;

public class Cross extends AbstractPlottable {

    private double y;
    private double x;
    private double size;

    public Cross() {
	setColor(Color.BLUE);
	setStroke(new BasicStroke(2.0F));
    }

    public Cross(double x, double y) {
	this();
	this.y = y;
	this.x = x;
    }

    public Cross(double x, double y, double sz) {
	this(x, y);
	size = sz;
    }

    @Override
    protected void draw(CoordinateSystem p) {
	p.cross(x, y, size);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

}
