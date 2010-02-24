package weigl.math;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EvalMathTest {
    static interface Fn {
	double eval(double d);
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test1() {
	testMath("x^2", new Fn() {
	    @Override
	    public double eval(double d) {
		return Math.pow(d, 2);
	    }
	});

	testMath("x^3", new Fn() {
	    @Override
	    public double eval(double d) {
		return Math.pow(d, 3);
	    }
	});

	testMath("x^4", new Fn() {
	    @Override
	    public double eval(double d) {
		return Math.pow(d, 4);
	    }
	});


	testMath("5", new Fn() {
	    @Override
	    public double eval(double d) {
		return 5;
	    }
	});
	
	testMath("2^x", new Fn() {
	    @Override
	    public double eval(double d) {
		return Math.pow(2, d);
	    }
	});

	testMath("5*x+62^x", new Fn() {
	    @Override
	    public double eval(double d) {
		return Math.pow(62, d) + 5 * d;
	    }
	});

	testMath("sign(x)", new Fn() {
	    @Override
	    public double eval(double d) {
		return Math.signum(d);
	    }
	});

	testMath("sinh(x)", new Fn() {
	    @Override
	    public double eval(double d) {
		return Math.sinh(d);
	    }
	});

	testMath("2^cos(x)", new Fn() {
	    @Override
	    public double eval(double d) {
		return Math.pow(2, Math.cos(d));
	    }
	});

	testMath("sin(x)*cos(x)", new Fn() {
	    @Override
	    public double eval(double d) {
		return Math.sin(d) * Math.cos(d);
	    }
	});

    }

    @Test
    public void test2() {
	testMath("2*x^4+2*x", new Fn() {
	    @Override
	    public double eval(double d) {
		return 2 * Math.pow(d, 4)+ 2 * d ;//- 5 * Math.pow(d, 3) + 5;
	    }
	});
    }

    public void testMath(String expr, Fn fn) {
	EvalMath em = new EvalMath(expr);
	for (double d = -4; d <= 4; d += 0.1D) {
	    em.set("x", d);
	    assertEquals(fn.eval(d) ,em.eval(),0);
	}
    }

}
