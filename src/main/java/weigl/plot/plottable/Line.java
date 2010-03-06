package weigl.plot.plottable;

import java.awt.BasicStroke;
import java.awt.Color;

import weigl.plot.CoordinateSystem;

public class Line extends Rectangle {
    public Line() {
	setColor(Color.PINK);
	setStroke(new BasicStroke(2.0F));
    }

    public Line(double x1, double y1, double x2, double y2) {
	super(x1, y1, x2, y2);
    }

    @Override
    protected void draw(CoordinateSystem p) {
	p.line(getX1(), getY1(), getX2(), getY2());
    }

}