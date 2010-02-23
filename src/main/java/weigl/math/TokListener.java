package weigl.math;
import weigl.grammar.lltck.rt.Token;
import weigl.math.MathParser.MathTokenCallback;

public class TokListener implements MathTokenCallback {
    @Override
    public Object constant(Token<MathToken> tok) {
	return tok.getValue();
    }

    @Override
    public Object digit(Token<MathToken> tok) {
	return Double.parseDouble((String) tok.getValue());
    }

    @Override
    public Object div(Token<MathToken> tok) {
	return tok.getValue();
    }

    @Override
    public Object func(Token<MathToken> tok) {
	String fnname = (String) tok.getValue();
	fnname = fnname.substring(0, fnname.length() - 1);
	return fnname;
    }

    @Override
    public Object id(Token<MathToken> tok) {
	return tok.getValue();
    }

    @Override
    public Object lp(Token<MathToken> tok) {
	return tok.getValue();
    }

    @Override
    public Object minus(Token<MathToken> tok) {
	return tok.getValue();
    }

    @Override
    public Object mod(Token<MathToken> tok) {
	return tok.getValue();
    }

    @Override
    public Object mult(Token<MathToken> tok) {
	return tok.getValue();
    }

    @Override
    public Object plus(Token<MathToken> tok) {
	return tok.getValue();
    }

    @Override
    public Object rp(Token<MathToken> tok) {
	return tok.getValue();
    }

    @Override
    public Object power(Token<MathToken> tok) {
	return tok.getValue();
    }
}
