package weigl.plot;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ListItemRender extends DefaultListCellRenderer implements
	ListCellRenderer {
    private static final long serialVersionUID = -2492838743701259426L;

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
	    int index, boolean isSelected, boolean cellHasFocus) {
	JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value,
		index, isSelected, cellHasFocus);
	ListItem item = (ListItem) value;
	lbl.setText("f(x)= " + item.getExprText());
	lbl.setForeground(item.getPlotColor());
	return lbl;
    }

}
