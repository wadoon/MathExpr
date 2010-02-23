package weigl.math;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.TreeMap;

import weigl.grammar.lltck.rt.TokenDefinition;
import weigl.grammar.lltck.rt.interfaces.Leaf;
import weigl.grammar.lltck.rt.interfaces.Node;
import weigl.math.MathParser.MathTokenCallback;

public class EvalMath {
    private static final EnumSet<MathToken> REMOVABLE_NODES= EnumSet.of(MathToken.rp,
	    MathToken.lp, MathToken.EPSILON);

    private static final MathTokenCallback TOK_LISTENER = new TokListener();
    
    private TreeMap<String, Double> map = new TreeMap<String, Double>();;
    private EvalNode root;

    public EvalMath(String expr) {
	map.put("PI", Math.PI);
	map.put("E", Math.E);
	map.put("G", 9.80665D);
	
	final MathParser p = new MathParser();
	p.setTokenListener(TOK_LISTENER);
	p.run(expr);
	
	Node<MathToken> root = p.getParseTree().getRoot();
	compress(root);
	this.root = convert(root);
    }

    public double eval() {
	return root.value();
    }

    private EvalNode convert(Leaf<MathToken> root) {
	MathToken mt = root.getTerminalSymbol().getType();
	Object value = root.getTerminalSymbol().getValue();

	Node<MathToken> n;
	EvalNode left, right;

	switch (mt) {
	case constant:
	    return new Id(this, (String) value);
	case id:
	    return new Id(this, (String) value);
	case digit:
	    return new Constant(value);
	case FUNC:
	    n = (Node<MathToken>) root;
	    String name = (String) n.get(0).getTerminalSymbol().getValue();
	    EvalNode leaf = convert(n.get(1));
	    return new Func(name, leaf);
	case START: // strich
	    n = (Node<MathToken>) root;
	    if (n.size() > 1) {
		left = convert(n.get(0));
		right = convert(((Node<MathToken>) n.get(1)).get(1));
		if (((Node<MathToken>) n.get(1)).get(0).getTerminalSymbol()
			.getType() == MathToken.plus)
		    return new Plus(left, right);
		else
		    return new Minus(left, right);
	    } else {
		return convert(n.get(0));
	    }

	case PUNKT: // strich
	    n = (Node<MathToken>) root;
	    left = convert(n.get(0));
	    right = convert(((Node<MathToken>) n.get(1)).get(1));
	    if (((Node<MathToken>) n.get(1)).get(0).getTerminalSymbol()
		    .getType() == MathToken.mult)
		return new Mult(left, right);
	    else if (((Node<MathToken>) n.get(1)).get(0).getTerminalSymbol()
		    .getType() == MathToken.mod)
		return new Mod(left, right);
	    else
		return new Div(left, right);

	case POW: // TODO will change sometimes
	    n = (Node<MathToken>) root;
	    left = convert(n.get(0));
	    right = convert(n.get(2));
	    return new Pow(left, right);
	}

	return null;
    }

    public double get(String name) {
	Double d = map.get(name);
	if (d == null)
	    return 0;
	return d;
    }

    public void set(String name, double d) {
	map.put(name, d);
    }

    public static <E extends TokenDefinition<E>> Leaf<E> compress(Node<E> n) {
	ArrayList<Leaf<E>> ccopy = new ArrayList<Leaf<E>>(n.getElements());

	for (Leaf<E> leaf : ccopy) {
	    if (REMOVABLE_NODES.contains(leaf.getTerminalSymbol().getType())) {
		n.getElements().remove(leaf);
		continue;
	    }

	    if (leaf.hasChildren()) {
		Leaf<E> replacement = compress((Node<E>) leaf);
		if (replacement == null) {
		    n.getElements().remove(leaf);
		} else {
		    int pos = n.getElements().indexOf(leaf);
		    n.getElements().set(pos, replacement);
		}
	    }
	}

	if (n.getElements().size() == 1)
	    return (Leaf<E>) n.getElements().get(0);
	if (n.getElements().size() == 0)
	    return null;
	return n;
    }
}

interface EvalNode {
    double value();
}

abstract class BinaryOp implements EvalNode {
    EvalNode left, right;

    public BinaryOp(EvalNode left, EvalNode right) {
	this.left = left;
	this.right = right;
    }

}

class Minus extends BinaryOp {
    public Minus(EvalNode left, EvalNode right) {
	super(left, right);
    }

    @Override
    public double value() {
	return left.value() - right.value();
    }

}

class Plus extends BinaryOp {
    public Plus(EvalNode left, EvalNode right) {
	super(left, right);
    }

    @Override
    public double value() {
	return left.value() + right.value();
    }
}

class Mult extends BinaryOp {
    public Mult(EvalNode left, EvalNode right) {
	super(left, right);
    }

    @Override
    public double value() {
	return left.value() * right.value();
    }
}

class Div extends BinaryOp {
    public Div(EvalNode left, EvalNode right) {
	super(left, right);
    }

    @Override
    public double value() {
	return left.value() / right.value();
    }
}

class Mod extends BinaryOp {
    public Mod(EvalNode left, EvalNode right) {
	super(left, right);
    }

    @Override
    public double value() {
	return left.value() % right.value();
    }
}

class Func implements EvalNode {
    String name;
    EvalNode value;

    public Func(String name, EvalNode value) {
	this.name = name;
	this.value = value;
    }

    @Override
    public double value() {
	return Functions.lookup(name).call(value.value());
    }

}

class Id implements EvalNode {
    EvalMath ctx;
    String name;

    public Id(EvalMath ctx, String name) {
	this.ctx = ctx;
	this.name = name;
    }

    @Override
    public double value() {
	return ctx.get(name);
    }
}

class Constant implements EvalNode {
    double value;

    public Constant(Object value) {
	this.value = (Double) value;
    }

    @Override
    public double value() {
	return value;
    }
}

class Pow extends BinaryOp {

    public Pow(EvalNode left, EvalNode right) {
	super(left, right);
    }

    @Override
    public double value() {
	return Math.pow(left.value(), right.value());
    }

}