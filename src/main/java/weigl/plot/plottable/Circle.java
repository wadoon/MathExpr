package weigl.plot.plottable;

import weigl.plot.CoordinateSystem;

public class Circle extends AbstractPlottable {
    private double x, y, radius;
    private boolean filled;

    public Circle(double x, double y, double radius,
	    boolean filled) {
	this.x = x;
	this.y = y;
	this.radius
	 = radius;
	this.filled = filled;
    }

    @Override
    protected void draw(CoordinateSystem p) {
	p.circle(x, y, radius, filled);
    }

    public double getX() {
	return x;
    }

    public void setX(double x) {
	this.x = x;
    }

    public double getY() {
	return y;
    }

    public void setY(double y) {
	this.y = y;
    }

    public double getRadius() {
	return radius;
    }

    public void setRadiusx(double radius) {
	this.radius= radius;
    }


    public boolean isFilled() {
	return filled;
    }

    public void setFilled(boolean filled) {
	this.filled = filled;
    }
}
