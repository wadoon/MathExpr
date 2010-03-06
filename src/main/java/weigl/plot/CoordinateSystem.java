package weigl.plot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

public class CoordinateSystem {
    public static CoordinateSystem create(Graphics2D graphics, int width,
	    int height, Point center, int dpu) {
	return new CoordinateSystem(graphics, width, height, dpu, center);
    }

    private int dpu;
    private Graphics2D root;
    private Graphics2D graphics;

    private Point center;
    private int height;

    private int width;

    public CoordinateSystem(Graphics2D graphic, int width, int height, int dpu, Point c) {
	this.dpu = dpu;
	this.width = width;
	this.height = height;
	center = c;
	root = graphic;
	init();
    }

    public void arc(double x, double y, double radiusx, double radiusy,
	    int startAngle, int arcAngle, boolean filled) {
	int _x = px(x - radiusx / 2);
	int _y = py(y - radiusy / 2);

	int rx = (int) (radiusx * dpu);
	int ry = (int) (radiusy * dpu);

	if (filled)
	    graphics.fillArc(_x, _y, rx, ry, startAngle, arcAngle);
	else
	    graphics.drawArc(_x, _y, rx, ry, startAngle, arcAngle);
    }

    /**
     * draw a circle
     * 
     * @param x
     *            coord of the middlepoint
     * @param y
     *            coord of the middlepoint
     * @param radius
     *            the radius in units
     * @param filled
     *            - true if circle should draw out
     */
    public void circle(double x, double y, double radius, boolean filled) {
	oval(x, y, radius, radius, filled);
    }

    /**
     * draw an oval
     * 
     * @see Graphics#drawOval(int, int, int, int)
     * 
     * @param x
     *            coord of the middlepoint
     * @param y
     *            coord of the middlepoint
     * @param radius
     *            the radius in units
     * @param filled
     *            - true if circle should draw out
     */
    public void oval(double x, double y, double radiusx, double radiusy,
	    boolean filled) {

	int x_ = px(x - radiusx / 2);
	int y_ = py(y + radiusy / 2);

	int rx = (int) (radiusx * dpu);
	int ry = (int) (radiusy * dpu);

	if (filled)
	    graphics.fillOval(x_, y_, rx, ry);
	else
	    graphics.drawOval(x_, y_, rx, ry);
    }

    /**
     * @return current graphics context
     */
    public Graphics2D getGraphics() {
	return graphics;
    }

    /**
     * @return maximum visible x value
     */
    public double getMaxX() {
	return (width - center.x) / dpu;
    }

    /**
     * @return maximum visible y value
     */
    public double getMaxY() {
	return center.y / dpu;
    }

    /**
     * @return minimum visible x value
     */
    public double getMinX() {
	return -(center.x / dpu);
    }

    /**
     * @return minimum visible y value
     */
    public double getMinY() {
	return -(height - center.y) / dpu;
    }

    /**
     * creates an array with all visible x values points on screen
     * 
     * @return array of visible x values
     */
    public double[] getXValues() {
	double values[] = new double[width + 1];
	for (int i = 0; i <= width; i++) {
	    values[i] = valx(i);
	}
	return values;
    }

    private void init() {
	newGraphics();
	graphics.setColor(Color.white);
	graphics.fillRect(0, 0, width, height);
	newGraphics();
    }

    /**
     * draw a line between x1/y1 and y2/x2
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void line(double x1, double y1, double x2, double y2) {
	graphics.drawLine(px(x1), py(y1), px(x2), py(y2));
    }

    /**
     * creates a new graphics context
     */
    public void newGraphics() {
	graphics = (Graphics2D) root.create();
	graphics.setColor(Color.black);

	graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
    }

    /**
     * draw a polygon with the given x and y coordinates <br>
     * useful for functions
     * 
     * @param x
     *            coords
     * @param y
     *            coords
     */
    public void polygon(double[] x, double[] y) {
	int sz = Math.min(x.length, y.length);
	int _x[] = new int[sz];
	int _y[] = new int[sz];

	for (int i = 0; i < sz; i++) {
	    _x[i] = px(x[i]);
	    _y[i] = py(y[i]);

	    // graphics.drawLine(_x[i],_y[i],_x[i],_y[i]);
	}
	graphics.drawPolyline(_x, _y, sz);
    }

    /**
     * @param pos
     *            value
     * @return pixel position in horizontal
     */
    public int px(double pos) {
	return (int) (pos * dpu) + center.x;
    }

    /**
     * convert the given value coordinate to pixel position
     * 
     * @param pos
     * @return y-pixel position
     */
    public int py(double pos) {
	return (int) -(pos * dpu) + center.y;
    }

    /**
     * draw a rectangle with the given points
     * 
     * @param _x1
     * @param _y1
     * @param _x2
     * @param _y2
     * @param fill
     *            the rect out
     * 
     * @see Graphics#drawRect(int, int, int, int)
     * @see Graphics#fillRect(int, int, int, int)
     */
    public void rect(double _x1, double _y1, double _x2, double _y2,
	    boolean fill) {
	_x1 = px(_x1);
	_y1 = py(_y1);
	_x2 = px(_x2);
	_y2 = py(_y2);

	int x1 = (int) Math.min(_x1, _x2);
	int y1 = (int) Math.min(_y1, _y2);
	int x2 = (int) Math.max(_x1, _x2);
	int y2 = (int) Math.max(_y1, _y2);

	if (fill)
	    graphics.fillRect(x1, y1, (x2 - x1), (y2 - y1));
	else
	    graphics.drawRect(x1, y1, (x2 - x1), (y2 - y1));
    }

    public void setColor(Color c) {
	graphics.setColor(c);

    }

    public void text(double x, double y, String text) {
	int a = px(x);
	int b = py(y);
	graphics.drawString(text, a, b);
    }

    /**
     * pixel to value in horizontal
     * 
     * @param x
     *            pixel position
     * @return value position
     */
    double valx(int x) {
	return (x - center.x) / (double) dpu;
    }

    /**
     * pixel to value in vertical
     * 
     * @param y
     *            pixel position
     * @return value position
     */
    double valy(int y) {
	return -(y - center.y) / (double) dpu;
    }

    /**
     * <pre>
     * \  / 
     *  \/ 
     *  /\ 
     * /  \
     * </pre>
     * @param x
     * @param y
     * @param size
     */
    public void cross(double x, double y, double size) {
	final double _rt = (Math.toRadians(45));

	line(   x+(Math.cos(_rt)*size),
		y+(Math.sin(_rt)*size),
		x-(Math.cos(_rt)*size),
		y-(Math.sin(_rt)*size));

	line(   x+(Math.cos(_rt)*size),
		y-(Math.sin(_rt)*size),
		x-(Math.cos(_rt)*size),
		y+(Math.sin(_rt)*size));
    }

}
