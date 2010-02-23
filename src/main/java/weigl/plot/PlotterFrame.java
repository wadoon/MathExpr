package weigl.plot;

import static java.awt.Color.black;
import static java.awt.Color.cyan;
import static java.awt.Color.green;
import static java.awt.Color.magenta;
import static java.awt.Color.orange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import weigl.math.EvalMath;

public class PlotterFrame extends JFrame implements ActionListener,
	PropertyChangeListener {
    private static final long serialVersionUID = -5841281170608474983L;

    private JPanel pNorth = new JPanel(new BorderLayout());

    private JButton btnAdd = new JButton("+");

    private JTextField txtField = new JTextField();

    private JButton btnDelete = new JButton("-");

    private JList lsFunc = new JList();

    private DefaultListModel model = new DefaultListModel();

    private Color[] plotCol = { black, Color.BLUE, green, magenta,
	    orange, cyan };

    private short plotColCnt = 0;

    private Box pStatus = new Box(BoxLayout.X_AXIS);

    private JLabel lblEinheit = new JLabel();


    public PlotterFrame() {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(500, 500);

	Box b = new Box(BoxLayout.X_AXIS);
	b.add(new JLabel("f(x)= "));
	b.add(txtField);
	b.add(btnAdd);

	pNorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	pNorth.add(b, BorderLayout.NORTH);

	JPanel p = new JPanel(new BorderLayout());
	p.add(new JScrollPane(lsFunc), BorderLayout.CENTER);
	p.add(btnDelete, BorderLayout.EAST);

	pNorth.add(p, BorderLayout.CENTER);

	add(pNorth, BorderLayout.NORTH);

	lsFunc.setModel(model);
	
	Plotter p1 = new Plotter(model);
	p1.addPropertyChangeListener(this);
	add(p1);

	pStatus.add(lblEinheit);
	add(pStatus, BorderLayout.SOUTH);

	btnAdd.addActionListener(this);
	btnDelete.addActionListener(this);

	lsFunc.setCellRenderer(new ListItemRender());

    }

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == btnAdd) {
	    if (plotCol.length == plotColCnt)
		plotColCnt = 0;
	    try {
		EvalMath ee = new EvalMath(txtField.getText());
		model.addElement(new ListItem(ee, plotCol[plotColCnt++],
			txtField.getText()));
	    } catch (Exception e1) {
		JOptionPane.showMessageDialog(this, e1.getMessage(),
			"Fehler beim Parsen", JOptionPane.ERROR_MESSAGE);
	    }
	} else {
	    model.remove(lsFunc.getSelectedIndex());
	}
	repaint();
    }

    public void propertyChange(PropertyChangeEvent evt) {
	System.out.println("PlotterFrame.propertyChange()");
	if (evt.getPropertyName().equals("pxMm")) {
	    int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
	    lblEinheit.setText("1 Einheit = "
		    + (10 * Double.parseDouble(evt.getNewValue().toString()))
		    / dpi * 2.54 + " cm");
	}
    }
}
