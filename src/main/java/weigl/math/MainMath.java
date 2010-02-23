package weigl.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMath {

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String expr = br.readLine();

	EvalMath ee = new EvalMath(expr);

	System.out.format("%10s | %10s%n", "x", "f(x)");
	System.out.format("-----------------------%n");

//	for (double i = 0; i <= Math.PI * 2; i += 0.25) {
//	    ee.set("x", i);
//	    double d = ee.eval();
//	    System.out.format("%10f | %10f%n", i, d);
//	}

	 for (double i = -5D; i <=5; i+=0.5) {
	 ee.set("x",i);
	 double d =ee.eval();
	 System.out.format("%10.2f | %10.2f%n", i,d);
	 }
    }
}
