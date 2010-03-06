package weigl.plot.plottable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

import weigl.plot.CoordinateSystem;
import weigl.plot.Plottable;

public abstract class AbstractPlottable implements Plottable {
    protected Color color = Color.BLACK;
    protected Stroke stroke = null;
    protected boolean fill = true;

    public Stroke getStroke() {
	return stroke;
    }

    public AbstractPlottable setStroke(Stroke stroke) {
	this.stroke = stroke;
	return this;
    }

    public AbstractPlottable setStroke(float sz) {
	this.stroke = new BasicStroke((float) sz);
	return this;
    }

    public Color getColor() {
	return color;
    }

    public AbstractPlottable setColor(Color color) {
	this.color = color;
	return this;
    }

    @Override
    public void plot(CoordinateSystem p) {
	p.setColor(color);
	if (stroke != null)
	    p.getGraphics().setStroke(stroke);
	draw(p);
    }

    protected abstract void draw(CoordinateSystem p);
}
