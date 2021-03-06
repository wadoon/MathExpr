package weigl.plot.plottable;

import java.awt.BasicStroke;
import java.awt.Color;

import weigl.plot.CoordinateSystem;

/**
 * 
 * @author Alexander Weigl <alex953@gmail.com>
 * @date 2010-02-23
 */
public class Rectangle extends AbstractPlottable {

    private double y1;
    private double x1;
    private double x2;
    private double y2;

    public Rectangle() {
	setColor(Color.BLUE);
	setStroke(new BasicStroke(2.0F));
    }

    public Rectangle(double x1, double y1, double x2, double y2) {
	this();
	this.y1 = y1;
	this.x1 = x1;
	this.x2 = x2;
	this.y2 = y2;
    }

    public Rectangle(double x1, double y1, double x2, double y2, boolean b) {
	this(x1,y1,x2,y2);
	fill = b;
    }

    @Override
    protected void draw(CoordinateSystem p) {
	p.rect(x1, y1, x2, y2,fill);
    }

    public double getX1() {
	return x1;
    }

    public double getX2() {
	return x2;
    }

    public double getY1() {
	return y1;
    }

    public double getY2() {
	return y2;
    }

    public void setX1(double x1) {
	this.x1 = x1;
    }

    public void setX2(double x2) {
	this.x2 = x2;
    }

    public void setY1(double y1) {
	this.y1 = y1;
    }

    public void setY2(double y2) {
	this.y2 = y2;
    }
}
