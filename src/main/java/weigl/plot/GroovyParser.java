package weigl.plot;

import groovy.util.FactoryBuilderSupport;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * size "300x300" dpi 300
 * 
 * gridx unit gridy function
 * 
 * 
 * output "dskfjsdafj"
 * 
 * @author alex
 */
public class GroovyParser extends FactoryBuilderSupport {
    public static void main(String[] args) {
	ScriptEngine se = new ScriptEngineManager().getEngineByName("groovy");
    }
}
