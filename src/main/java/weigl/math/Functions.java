package weigl.math;

import java.util.Map;
import java.util.TreeMap;

public class Functions {
    
    private static final Function F_SINUS = new Function() {
        @Override
        public double call(double d) {
            return Math.sin(( d));
        }
    };

    private static final Function F_COSINUS = new Function() {
	@Override
	public double call(double d) {
	    return Math.cos(d);
	}
    };

    private static final Function F_TANGENS= new Function() {
	@Override
	public double call(double d) {
	    return Math.tan(d);
	}
    };

    private static final Function F_ASINUS = new Function() {
	@Override
	public double call(double d) {
	    return Math.asin(d);
	}
    };
    
    private static final Function F_ACOSINUS = new Function() {
	@Override
	public double call(double d) {
	    return Math.acos(d);
	}
    };
    
    private static final Function F_ATANGENS= new Function() {
	@Override
	public double call(double d) {
	    return Math.atan(d);
	}
    };

    private static final Function F_SINUSH = new Function() {
	@Override
	public double call(double d) {
	    return Math.sinh(d);
	}
    };
    
    private static final Function F_COSINUSH = new Function() {
	@Override
	public double call(double d) {
	    return Math.cosh(d);
	}
    };
    
    private static final Function F_TANGENSH= new Function() {
	@Override
	public double call(double d) {
	    return Math.tanh(d);
	}
    };

    private static final Function F_RAND = new Function() {
        @Override
        public double call(double d) {
            return Math.random();
        }
    };;;

    private static final Function F_SIGN = new Function() {
        
        @Override
        public double call(double d) {

        	return Math.signum(d);
        	}
    };

    private static final Function F_ABS = new Function() {
        @Override
        public double call(double d) {
    	return Math.abs(d);
        }
    };

    private static final Function F_LOG = new Function() {
        
        @Override
        public double call(double d) {
    	return Math.log(d);
        }
    };

    private static final Function F_LOGE = new Function() {
        @Override
        public double call(double d) {
return Math.log(d);
        }
    };

    private static final Function F_LOG2 = new Function() {
	    final double LOG2 = Math.log(2);

        @Override
        public double call(double d) {
            return Math.log(d)/LOG2;
        }
    };

    private static final Function F_ROUND = new Function() {
        @Override
        public double call(double d) {
    	return Math.round(d);
        }
    };

    private static final Function F_KW = new Function() {
        @Override
        public double call(double d) {
    	return 1/d;
        }
    };

    private static final Function F_POW3 = new Function() {
        
        @Override
        public double call(double d) {
        	return d*d*d;
        	}
    };

    private static final Function F_POW2 = new Function() {
        @Override
        public double call(double d) {            
    	return d*d;
        }
    };
    private static final Function F_POW5 = new Function() {
	@Override
	public double call(double d) {            
	    return d*d*d*d*d;
	}
    };

    private static final Function F_POW7 = new Function() {
	@Override
	public double call(double d) {            
	    return Math.pow(d, 7);
	}
    };
    
    

    private static Map<String, Function> map = new TreeMap<String, Function>();
    static {
	map.put("sin", F_SINUS);
	map.put("cos", F_COSINUS);
	map.put("tan", F_TANGENS);

	map.put("sinh", F_SINUSH);
	map.put("cosh", F_COSINUSH);
	map.put("tanh", F_TANGENSH);

	map.put("asin", F_ASINUS);
	map.put("acos", F_ACOSINUS
		);
	map.put("atan", F_ATANGENS);

	map.put("abs", F_ABS);
	map.put("sign", F_SIGN);
	map.put("rand", F_RAND);

	map.put("log", F_LOG);
	map.put("ln", F_LOGE);
	map.put("ld", F_LOG2);
	
	map.put("round", F_ROUND);
	
	map.put("pow2", F_POW2);
	map.put("pow3", F_POW3);
	map.put("pow3", F_POW5);
	map.put("pow3", F_POW7);

	map.put("kw", F_KW);
    }
    

    public static void register(String name, Function f) {
	map.put(name, f);
    }

    public static Function lookup(String name) {
	return map.get(name);
    }

    public static interface Function {
	double call(double d);
    }
}
