package weigl.plot.plottable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.geom.Point2D;

import weigl.plot.CoordinateSystem;

/**
 * 
 * @author Alexander Weigl <alex953@gmail.com>
 * @date 2010-02-25
 */
public class GradientRectangle extends Rectangle {

    public GradientRectangle() {
	setColor(new Color(234, 61, 123, 150));
	setStroke(new BasicStroke(2.0F));
    }

    public GradientRectangle(double x1, double y1, double x2, double y2) {
	super(x1, y1, x2, y2);
	fill = true;
    }

    @Override
    protected void draw(CoordinateSystem p) {
	
	Point2D p1 = new Point2D.Double(p.px(getX1()), p.py(getY1()));
	Point2D p2= new Point2D.Double(p.px(getX2()), p.py(getY2()));
	
	GradientPaint paint = new GradientPaint(p1, new Color(255, 255, 255, 150), p2,
		getColor());
	
	p.setColor(Color.RED);
	p.getGraphics().setPaint(paint);
	
	p.rect(getX1(), getY1(), getX2(), getY2(), fill);
    }
}
