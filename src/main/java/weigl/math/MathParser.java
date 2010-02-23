package weigl.math;

import weigl.grammar.lltck.rt.DefaultAbstractSyntaxTree;
import weigl.grammar.lltck.rt.Token;
import weigl.grammar.lltck.rt.TokenParserFather;
import weigl.grammar.lltck.rt.Tokenizer;
import weigl.grammar.lltck.rt.interfaces.Node;

/**
 * 
 * generated Parser class for grammar:
 * 
 * <pre>
 * digit    = \d*\.?\d+
 * id       = [a-z]+
 * constant = [A-Z]+
 * func     = [a-z]+[(] 
 * 
 * power = [^]plus  = [+]
 * minus = [-]
 * mult  = [*]
 * div   = [/]
 * mod   = [%]
 * 
 * lp    = [(]
 * rp    = [)]
 * 
 * 
 * 
 * 
 * # START: lp START rp 
 * #	 | START + START 
 * #	 | START * START 
 * #	 | START START 
 * #	 | START / START 
 * #	 | START mod START
 * #	 | id | digit | constant
 * 	 	 
 * 	 
 * START  :  PUNKT STRICH_  
 * STRICH_ :  plus START | minus STRICH | €
 * 
 * PUNKT   :  VALUE PUNKT_
 * PUNKT_  :  div PUNKT | mult PUNKT | mod PUNKT  | €
 * 
 * 
 * VALUE   : minus POW | POW
 * POW     : VALUE_ power START   
 * VALUE_  : id | digit | constant | lp START rp | FUNC
 * 
 * FUNC    : func START rp
 * </pre>
 */
@SuppressWarnings("unchecked")
public class MathParser extends TokenParserFather<MathToken> {
    private RuleTParserCallback ruleListener;
    private MathTokenCallback tokListener;

    public MathParser() {
	super(MathToken.values());
	setRuleListener(new RuleTParserCallbackAdapter());
    }

    public void setRuleListener(RuleTParserCallback listener) {
	this.ruleListener = listener;
    }

    public void setTokenListener(MathTokenCallback listener) {
	this.tokListener = listener;
    }

    /**
     * <pre>
     * FUNC -> Array[func, START, rp]
     * </pre>
     */
    public Node<MathToken> FUNC() {
	final Node<MathToken> n = newNode(MathToken.FUNC);
	boolean matched = false;
	if (lookahead(MathToken.func)) {
	    matched = true;
	    n.add(match(MathToken.func));
	    n.add(START());
	    n.add(match(MathToken.rp));
	}

	if (!matched)
	    error(/* ... */);
	return ruleListener.FUNC(n);
    }

    /**
     * <pre>
     * POW -> Array[VALUE_, power, START]
     * </pre>
     */
    public Node<MathToken> POW() {
	final Node<MathToken> n = newNode(MathToken.POW);
	boolean matched = false;
	if (lookahead(MathToken.id, MathToken.digit, MathToken.func,
		MathToken.constant)) {
	    matched = true;
	    n.add(VALUE_());
	    
	    if(lookahead(MathToken.power))
	    {
		n.add(match(MathToken.power));
		n.add(START());
	    }
	}

	if (!matched)
	    error(MathToken.id, MathToken.digit, MathToken.func,
		    MathToken.constant);
	return ruleListener.POW(n);
    }

    /**
     * <pre>
     * PUNKT -> Array[VALUE, PUNKT_]
     * </pre>
     */
    public Node<MathToken> PUNKT() {
	final Node<MathToken> n = newNode(MathToken.PUNKT);
	boolean matched = false;
	if (lookahead(MathToken.id, MathToken.digit, MathToken.func,
		MathToken.constant, MathToken.minus)) {
	    matched = true;
	    n.add(VALUE());
	    n.add(PUNKT_());
	}

	if (!matched)
	    error(MathToken.id, MathToken.digit, MathToken.func, MathToken.minus,
		    MathToken.constant);
	return ruleListener.PUNKT(n);
    }

    /**
     * <pre>
     * PUNKT_ -> Array[div, PUNKT]
     * PUNKT_ -> Array[mod, PUNKT]
     * PUNKT_ -> Array[mult, PUNKT]
     * PUNKT_ -> Array[€]
     * </pre>
     */
    public Node<MathToken> PUNKT_() {
	final Node<MathToken> n = newNode(MathToken.PUNKT_);
	boolean matched = false;
	if (lookahead(MathToken.div)) {
	    matched = true;
	    n.add(match(MathToken.div));
	    n.add(PUNKT());
	} else if (lookahead(MathToken.mod)) {
	    matched = true;
	    n.add(match(MathToken.mod));
	    n.add(PUNKT());
	} else if (lookahead(MathToken.mult)) {
	    matched = true;
	    n.add(match(MathToken.mult));
	    n.add(PUNKT());
	} else if (true) {
	    matched = true;
	    n.add(match(MathToken.EPSILON));
	}

	if (!matched)
	    error(/* ... */);
	return ruleListener.PUNKT_(n);
    }

    /**
     * <pre>
     * START -> Array[PUNKT, STRICH_]
     * </pre>
     */
    public Node<MathToken> START() {
	final Node<MathToken> n = newNode(MathToken.START);
	boolean matched = false;
	if (lookahead(MathToken.id, MathToken.digit, MathToken.func,
		MathToken.constant, MathToken.minus)) {
	    matched = true;
	    n.add(PUNKT());
	    n.add(STRICH_());
	}

	if (!matched)
	    error(MathToken.id, MathToken.digit, MathToken.func,
		    MathToken.constant, MathToken.minus);
	return ruleListener.START(n);
    }

    /**
     * <pre>
     * STRICH_ -> Array[minus, STRICH]
     * STRICH_ -> Array[plus, START]
     * STRICH_ -> Array[€]
     * </pre>
     */
    public Node<MathToken> STRICH_() {
	final Node<MathToken> n = newNode(MathToken.STRICH_);
	boolean matched = false;
	if (lookahead(MathToken.minus)) {
	    matched = true;
	    n.add(match(MathToken.minus));
	    n.add(START());
	} else if (lookahead(MathToken.plus)) {
	    matched = true;
	    n.add(match(MathToken.plus));
	    n.add(START());
	} else if (true) {
	    matched = true;
	    n.add(match(MathToken.EPSILON));
	}

	if (!matched)
	    error(/* ... */);
	return ruleListener.STRICH_(n);
    }

    /**
     * <pre>
     * VALUE -> Array[POW]
     * VALUE -> Array[minus, POW]
     * </pre>
     */
    public Node<MathToken> VALUE() {
	final Node<MathToken> n = newNode(MathToken.VALUE);
	boolean matched = false;
	if (lookahead(MathToken.id, MathToken.digit, MathToken.func,
		MathToken.constant)) {
	    matched = true;
	    n.add(POW());
	} else if (lookahead(MathToken.minus)) {
	    matched = true;
	    n.add(match(MathToken.minus));
	    n.add(POW());
	}

	if (!matched)
	    error(MathToken.id, MathToken.digit, MathToken.func, MathToken.minus,
		    MathToken.constant);
	return ruleListener.VALUE(n);
    }

    /**
     * <pre>
     * VALUE_ -> Array[FUNC]
     * VALUE_ -> Array[constant]
     * VALUE_ -> Array[digit]
     * VALUE_ -> Array[id]
     * VALUE_ -> Array[lp, START, rp]
     * </pre>
     */
    public Node<MathToken> VALUE_() {
	final Node<MathToken> n = newNode(MathToken.VALUE_);
	boolean matched = false;
	if (lookahead(MathToken.func)) {
	    matched = true;
	    n.add(FUNC());
	} else if (lookahead(MathToken.constant)) {
	    matched = true;
	    n.add(match(MathToken.constant));
	} else if (lookahead(MathToken.digit)) {
	    matched = true;
	    n.add(match(MathToken.digit));
	} else if (lookahead(MathToken.id)) {
	    matched = true;
	    n.add(match(MathToken.id));
	} else if (lookahead(MathToken.lp)) {
	    matched = true;
	    n.add(match(MathToken.lp));
	    n.add(START());
	    n.add(match(MathToken.rp));
	}

	if (!matched)
	    error(/* ... */);
	return ruleListener.VALUE_(n);
    }

    @Override
    public Node<MathToken> start() {
	return START();
    }

    public void run(String source) {
	reset();
	input = new Tokenizer<MathToken>(source, tokListener, getTokens());
	consume();
	syntaxTree = new DefaultAbstractSyntaxTree<MathToken>(start());
    }

    public static void main(String argc[]) throws java.io.IOException {
	java.io.BufferedReader br = new java.io.BufferedReader(
		new java.io.InputStreamReader(System.in));
	MathParser parser = new MathParser();
	parser.run(br.readLine());
	System.out.println(parser.getParseTree());
    }

    public static interface RuleTParserCallback {
	public Node<MathToken> FUNC(Node<MathToken> node);

	public Node<MathToken> POW(Node<MathToken> node);

	public Node<MathToken> PUNKT(Node<MathToken> node);

	public Node<MathToken> PUNKT_(Node<MathToken> node);

	public Node<MathToken> START(Node<MathToken> node);

	public Node<MathToken> STRICH_(Node<MathToken> node);

	public Node<MathToken> VALUE(Node<MathToken> node);

	public Node<MathToken> VALUE_(Node<MathToken> node);
    }

    public static class RuleTParserCallbackAdapter implements
	    RuleTParserCallback {
	@Override
	public Node<MathToken> FUNC(Node<MathToken> node) {
	    return node;
	}

	@Override
	public Node<MathToken> POW(Node<MathToken> node) {
	    return node;
	}

	@Override
	public Node<MathToken> PUNKT(Node<MathToken> node) {
	    return node;
	}

	@Override
	public Node<MathToken> PUNKT_(Node<MathToken> node) {
	    return node;
	}

	@Override
	public Node<MathToken> START(Node<MathToken> node) {
	    return node;
	}

	@Override
	public Node<MathToken> STRICH_(Node<MathToken> node) {
	    return node;
	}

	@Override
	public Node<MathToken> VALUE(Node<MathToken> node) {
	    return node;
	}

	@Override
	public Node<MathToken> VALUE_(Node<MathToken> node) {
	    return node;
	}
    }

    public interface MathTokenCallback {
	public Object constant(Token<MathToken> tok);

	public Object digit(Token<MathToken> tok);

	public Object div(Token<MathToken> tok);

	public Object func(Token<MathToken> tok);

	public Object id(Token<MathToken> tok);

	public Object lp(Token<MathToken> tok);

	public Object minus(Token<MathToken> tok);

	public Object mod(Token<MathToken> tok);

	public Object mult(Token<MathToken> tok);

	public Object power(Token<MathToken> tok);

	public Object rp(Token<MathToken> tok);

	Object plus(Token<MathToken> tok);
    }
}
