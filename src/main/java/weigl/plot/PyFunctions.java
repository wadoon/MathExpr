package weigl.plot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Alexander Weigl <alexweigl@gmail.com>
 * @date 2010-03-06
 */
public class PyFunctions {

    public static void save(BufferedImage image, String filename) throws IOException
    {
	File f = new File(filename);
	ImageIO.write(image, "png", f);
    }
    
    public static void show(BufferedImage image) {
	PictureFrame frame = new PictureFrame(image);
	frame.setSize(image.getWidth(), image.getHeight());
	frame.setVisible(true);
    }

    public static BufferedImage plot(PlotConfig config) {
	BufferedImage i = new BufferedImage(config.area.width,
		config.area.height, BufferedImage.TYPE_INT_ARGB);

	GraphicsPlotter plotter = new GraphicsPlotter();
	for (Plottable plottable : config) {
	    plotter.add(plottable);
	}
	plotter.plot(i.createGraphics(),
		config.area.width, config.area.height, config.center, config.getDpu());
	return i;
    }
}
