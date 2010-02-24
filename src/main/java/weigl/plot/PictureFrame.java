package weigl.plot;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class PictureFrame extends JFrame {
    private static final long serialVersionUID = 155550786241422200L;
    private BufferedImage image;
    public static final int DELTA = 10;

    public PictureFrame(BufferedImage ri) {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	image = ri;

	setSize(image.getWidth() + DELTA, image.getHeight() + DELTA);
    }

    @Override
    public void paint(Graphics g) {
	super.paintComponents(g);
	Graphics2D g2 = (Graphics2D) g;
	g2.drawImage(image, DELTA, DELTA, this);
    }
}
