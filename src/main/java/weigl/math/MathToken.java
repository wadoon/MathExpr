package weigl.math;

import java.util.regex.Pattern;

import weigl.grammar.lltck.rt.TokenDefinition;

public enum MathToken implements TokenDefinition<MathToken> {
    // tokens
    $whitespaces("[ ]+",true),
    constant("[A-Z]+", false), digit("\\d*\\.?\\d+", false), div("[/]", false), func(
	    "[a-z]+[(]", false), id("[a-z]+", false), lp("[(]", false), minus(
	    "[-]", false), mod("[%]", false), mult("[*]", false), power(
	    "\\Q^\\E",false), plus("[+]",false), rp("[)]", false), EPSILON(true),
    // rules
    FUNC(true), POW(true), PUNKT(true), PUNKT_(true), START(true), STRICH_(true), VALUE(
	    true), VALUE_(true);

    private Pattern pattern;
    private boolean hidden, rule;

    private MathToken(String regex) {
	pattern = Pattern.compile(regex);
    }

    private MathToken(String regex, boolean h) {
	this(regex);
	hidden = h;
    }

    private MathToken(boolean r) {
	this("", true);
	rule = r;
    }

    public Pattern getPattern() {
	return pattern;
    }

    public MathToken getType() {
	return this;
    }

    public boolean isHidden() {
	return hidden;
    }

    public boolean isRule() {
	return rule;
    }
}
