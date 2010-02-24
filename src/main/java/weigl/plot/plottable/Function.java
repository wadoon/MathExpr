package weigl.plot.plottable;

import weigl.math.EvalMath;
import weigl.plot.CoordinateSystem;

public class Function extends AbstractPlottable {
    private String expr;

    public Function(String string) {
	expr = string;
    }

    public String getExpr() {
	return expr;
    }

    public Function setExpr(String expr) {
	this.expr = expr;
	return this;
    }

    @Override
    protected void draw(CoordinateSystem p) {
	EvalMath em = new EvalMath(expr);
	double[] x = p.getXValues();
	double[] y = p.getXValues();

	for (int i = 0; i < x.length; i++) {
	    em.set("x", x[i]);
	    y[i] = em.eval();
	}
	p.polygon(x, y);
    }
}
