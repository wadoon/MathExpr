package weigl.plot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import weigl.plot.plottable.Circle;
import weigl.plot.plottable.Coordinates;
import weigl.plot.plottable.Cross;
import weigl.plot.plottable.Function;
import weigl.plot.plottable.Grid;
import weigl.plot.plottable.Arc;
import weigl.plot.plottable.Line;
import weigl.plot.plottable.Rectangle;

public class GraphicsPlotter {
    private List<Plottable> plotObject = new LinkedList<Plottable>();
    private int dpu;
    private int height;
    private int width;
    private Graphics2D graphics;

    public GraphicsPlotter(Graphics2D graphics, int width, int height, int dpu) {
	this.width = width;
	this.height = height;
	this.dpu = dpu;
	this.graphics = graphics;
    }

    public void plot() {
	CoordinateSystem csys = CoordinateSystem.create(graphics, width,
		height, dpu);
	for (Plottable p : plotObject) {
	    csys.newGraphics();
	    p.plot(csys);
	}
    }

    private void add(Plottable grid) {
	plotObject.add(grid);
    }

    public static void main(String[] args) {
	final int height = 600;
	final int width = 450;

	BufferedImage bi = new BufferedImage(width, height,
		BufferedImage.TYPE_INT_ARGB);
	GraphicsPlotter ip = new GraphicsPlotter(bi.createGraphics(), width,
		height, 50);
	ip.add(new Grid());
	ip.add(new Coordinates());

	ip.add(new Rectangle(1, 1, 0, 0).setColor(new Color(255, 0, 255, 127)));
	ip.add(new Rectangle(0, 0, -1, -1, false).setColor(Color.PINK));
	ip.add(new Function("5*x^3-2*x-2*x").setColor(Color.orange));

	ip.add(new Arc(5, -3, 2, 2, 0, 180, true).setColor(new Color(127, 127,
		255, 100)));

	ip
		.add(new Circle(-5, 3, 2, true).setColor(new Color(127, 0, 255,
			100)));

	ip.add(new Cross(2, 2, 0.1));
	ip.add(new Cross(-2, -2, 0.1));
	ip.add(new Cross(2, -2, 0.1));
	ip.add(new Cross(-2, 2, 0.1));

	ip.add(new Cross(3, 3, 0.1));
	ip.add(new Cross(-1, -1, 0.1));
	ip.add(new Cross(3, -1, 0.1));
	ip.add(new Cross(-1, 3, 0.1));
	
	ip.add(new Rectangle(3, 3, -1, -1,false));
	ip.add(new Rectangle(2, 2, -2, -2,false));

	ip.plot();
	PictureFrame pf = new PictureFrame(bi);
	pf.setVisible(true);
    }
}
