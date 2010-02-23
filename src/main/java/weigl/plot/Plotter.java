package weigl.plot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.Enumeration;

import javax.swing.DefaultListModel;

import weigl.math.EvalMath;

public class Plotter extends javax.swing.JPanel implements MouseWheelListener {
    private static final long serialVersionUID = -45666360130428152L;

    private double pxMm;

    private DefaultListModel model;


    public Plotter(DefaultListModel m) {
	int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
	pxMm = dpi / 25.4;

	addMouseWheelListener(this);
	model = m;
    }

    @Override
    public void paint(Graphics g) {
	super.paint(g);

	Graphics2D g2 = (Graphics2D) g;

	g2.setColor(Color.black);

	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	g2.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_SPEED);

	int width = getWidth();
	int height = getHeight();

	Point p = new Point(width / 2, height / 2);

	double length = 2 * pxMm;

	drawGrid(g2, p);

	g2.setStroke(new BasicStroke(1.8F));

	// Horizontal
	g.drawLine(0, height / 2, width, height / 2);
	// Vertical
	g.drawLine(width / 2, 0, width / 2, height);

	int x = 0;
	for (double i = p.x; i <= width; i += (10 * pxMm)) {
	    g2.draw(new Line2D.Double(i, height / 2 - length, i, height / 2
		    + length));
	    g2
		    .drawString("+" + x, (float) i,
			    (float) (height / 2 + length * 2));
	    x++;
	}

	x = 0;
	for (double i = p.x; i >= 0; i -= (10 * pxMm)) {
	    g2.draw(new Line2D.Double(i, height / 2 - length, i, height / 2
		    + length));
	    if (x > 0)
		g2.drawString("-" + x, (float) i,
			(float) (height / 2 + length * 2));
	    x++;
	}

	x = 0;
	for (double i = p.y; i <= height; i += (10 * pxMm)) {
	    g2.draw(new Line2D.Double(width / 2 - length, i,
		    width / 2 + length, i));
	    if (x > 0)
		g2.drawString("+" + x, (float) (width / 2 - length * 5),
			(float) i);
	    x++;
	}
	x = 0;
	for (double i = p.y; i >= 0; i -= (10 * pxMm)) {
	    g2.draw(new Line2D.Double(width / 2 - length, i,
		    width / 2 + length, i));
	    if (x > 0)
		g2.drawString("+" + x, (float) (width / 2 - length * 5),
			(float) i);
	    x++;
	}

	g2.setStroke(new BasicStroke(1.5F));

	Enumeration<?> list = model.elements();
	while (list.hasMoreElements()) {
	    ListItem node = (ListItem) list.nextElement();
	    Path2D pol = new Path2D.Double(Path2D.WIND_EVEN_ODD);
	    g2.setColor(node.getPlotColor());

	    double val = calc(0, p, node.getEval());
	    pol.moveTo(0, val);
	    for (int i = 1; i < width; i++) {
		val = calc(i, p, node.getEval());
		pol.lineTo(i, val);
	    }
	    g2.draw(pol);
	}
    }

    public double calc(int px, Point nul, EvalMath ee) {
	double val = (px - nul.x) / pxMm;

	ee.set("x", val);
	double y = 0;
	try {
	    y = ee.eval();
	    System.out.println(y);
	} catch (ArithmeticException e) {
	    e.printStackTrace();
	}
	return nul.y - y * pxMm;
    }

    private void drawGrid(Graphics2D g2, Point p) {
	g2.setColor(Color.GRAY);

	for (double i = p.x; i <= getWidth(); i += (5 * pxMm))
	    g2.draw(new Line2D.Double(i, 0, i, getHeight()));

	for (double i = p.x; i >= 0; i -= (5 * pxMm))
	    g2.draw(new Line2D.Double(i, 0, i, getHeight()));

	for (double i = p.y; i <= getHeight(); i += (5 * pxMm))
	    g2.draw(new Line2D.Double(0, i, getWidth(), i));

	for (double i = p.y; i >= 0; i -= (5 * pxMm))
	    g2.draw(new Line2D.Double(0, i, getWidth(), i));

	g2.setColor(Color.BLACK);
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
	// System.out.println("Plotter.mouseWheelMoved() " + pxMm);
	if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
	    int unitsToScroll = e.getUnitsToScroll();
	    double d = pxMm + unitsToScroll / 10.0;
	    setPxMm(d);
	    // System.out.println("NEW: " + pxMm + " : " + unitsToScroll);
	    repaint();
	}
    }

    public double getPxMm() {
	return pxMm;
    }

    public void setPxMm(double pxMm) {
	double old = getPxMm();
	this.pxMm = pxMm;
	firePropertyChange("pxMm", old, pxMm);
    }
}