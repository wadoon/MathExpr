package weigl.plot.plottable;

import weigl.plot.CoordinateSystem;

public class Arc extends AbstractPlottable {
    private int startAngle, arcAngle;
    private double x, y, radiusx, radiusy;
    private boolean filled;

    public Arc(double x, double y, double radiusx, double radiusy,
	    int startAngle, int arcAngle, boolean filled) {
	this.startAngle = startAngle;
	this.arcAngle = arcAngle;
	this.x = x;
	this.y = y;
	this.radiusx = radiusx;
	this.radiusy = radiusy;
	this.filled = filled;
    }

    @Override
    protected void draw(CoordinateSystem p) {
	p.arc(x, y, radiusx, radiusy, startAngle, arcAngle, filled);
    }

    public int getStartAngle() {
	return startAngle;
    }

    public void setStartAngle(int startAngle) {
	this.startAngle = startAngle;
    }

    public int getArcAngle() {
	return arcAngle;
    }

    public void setArcAngle(int arcAngle) {
	this.arcAngle = arcAngle;
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

    public double getRadiusx() {
	return radiusx;
    }

    public void setRadiusx(double radiusx) {
	this.radiusx = radiusx;
    }

    public double getRadiusy() {
	return radiusy;
    }

    public void setRadiusy(double radiusy) {
	this.radiusy = radiusy;
    }

    public boolean isFilled() {
	return filled;
    }

    public void setFilled(boolean filled) {
	this.filled = filled;
    }
}
