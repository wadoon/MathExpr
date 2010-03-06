package weigl.plot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedList;

import org.python.core.PyDictionary;
import org.python.core.PyObject;

import weigl.plot.plottable.Arc;
import weigl.plot.plottable.Coordinates;
import weigl.plot.plottable.Cross;
import weigl.plot.plottable.Function;
import weigl.plot.plottable.Grid;
import weigl.plot.plottable.Line;
import weigl.plot.plottable.Rectangle;

/**
 * 
 * @author Alexander Weigl <alexweigl@gmail.com>
 * @date 2010-03-04
 */
public class PlotConfig extends LinkedList<Plottable> {
    private static final long serialVersionUID = -2033931005840544573L;
    Point center = new Point(200, 200);
    Dimension area = new Dimension(400, 400);
    Color background = Color.WHITE;
    
    int dpu = 72;

    public PlotConfig() {

    }

    public PlotConfig(PyObject[] args, PyDictionary kwargs) {
	for (PyObject pyObject : args)
	    add((Plottable) pyObject.__tojava__(Plottable.class));
//	setArea((PyTuple) kwargs.get("area"));
//	setCenter((PyTuple) kwargs.get("center"));
	setDpu((Integer) kwargs.get("dpu"));
    }

    @Override
    public String toString() {
	return "PlotConfig (" + super.toString() + ", area=" + getArea()
		+ ", center=" + getCenter() + ", dpu=" + getDpu() + ")";
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Grid grid() {
	Grid g = new Grid();
	add(g);
	return g;
    }

    public Grid grid(double x, double y) {
	Grid g = grid();
	g.setDeltaX(x);
	g.setDeltaY(y);
	return g;
    }

    public Coordinates coordinates(double deltaX, double deltaY) {
	Coordinates c = coordinates();
	c.setDeltaX(deltaX);
	c.setDeltaY(deltaY);
	return c;
    }

    public Coordinates coordinates() {
	Coordinates c = new Coordinates();
	add(c);
	return c;
    }

    public Arc arc(double x, double y, double radiusx, double radiusy,
	    int startAngle, int arcAngle, boolean filled) {
	Arc arc = new Arc(x, y, radiusx, radiusy, startAngle, arcAngle, filled);
	add(arc);
	return arc;
    }

    public Line line(double x1, double y1, double x2, double y2) {
	Line line = new Line(x1, y1, x2, y2);
	add(line);
	return line;
    }

    public Rectangle rect(double x1, double y1, double x2, double y2,
	    boolean filled) {
	Rectangle rect = new Rectangle(x1, y1, x2, y2);
	add(rect);
	return rect;
    }

    public Rectangle rect(double x1, double y1, double x2, double y2) {
	return rect(x1, y1, x2, y2, true);
    }

    public Cross cross(double x, double y, double sz) {
	Cross c = new Cross(x, y, sz);
	add(c);
	return c;
    }

    public Function fn(String expr) {
	Function f = new Function(expr);
	add(f);
	return f;
    }

//    public PyTuple getCenter() {
//	return new PyTuple(new PyInteger(center.x), new PyInteger(center.y));
//    }
//
//    public void setCenter(PyTuple center) {
//	int x = ((Integer) center.get(0));
//	int y = ((Integer) center.get(1));
//	this.center = new Point(x, y);
//    }
//
//    public PyTuple getArea() {
//	return new PyTuple(new PyInteger(area.width),
//		new PyInteger(area.height));
//    }
//
//    public void setArea(PyTuple size) {
//	int x = ((Integer) size.get(0));
//	int y = ((Integer) size.get(1));
//	this.area = new Dimension(x, y);
//    }

    
    
    public int getDpu() {
	return dpu;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public Dimension getArea() {
        return area;
    }

    public void setArea(Dimension area) {
        this.area = area;
    }

    public void setDpu(int dpu) {
	this.dpu = dpu;
    }
}
