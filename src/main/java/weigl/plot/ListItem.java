package weigl.plot;

import java.awt.Color;

import weigl.math.EvalMath;

public class ListItem {
    private Color plotColor;
    private String exprText;
    private EvalMath expr;

    public ListItem(EvalMath ee, Color color, String string) {
	setExpr(ee);
	setPlotColor(color);
	setExprText(string);
    }

    private void setExpr(EvalMath ee) {
	expr = ee;
    }

    public Color getPlotColor() {
	return plotColor;
    }

    public void setPlotColor(Color plotColor) {
	this.plotColor = plotColor;
    }

    public String getExprText() {
	return exprText;
    }

    public void setExprText(String exprText) {
	this.exprText = exprText;
    }

    public EvalMath getEval() {
	return expr;
    }
}
