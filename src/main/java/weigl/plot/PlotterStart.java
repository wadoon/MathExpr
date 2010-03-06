package weigl.plot;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.python.core.PyDictionary;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.InteractiveConsole;
import org.python.util.InteractiveInterpreter;

public class PlotterStart {
    private static final String STARTUP_FILE = "startup.py";
    private static Map<String, PyObject> map = new HashMap<String, PyObject>();
    static {

    }

    public static void main(String[] args) throws IOException {
	Properties props = new Properties();
	InteractiveInterpreter.initialize(System.getProperties(), props, args);
	PyDictionary dict = createNamespace(map);
	InteractiveConsole ic = new InteractiveConsole(dict);
	ic.exec(startupFile());
	ic.interact();
    }

    private static String startupFile() throws IOException {
	File f = new File(STARTUP_FILE);
	Reader r = new FileReader(STARTUP_FILE);

	char[] cbuf = new char[(int) f.length()];
	int i = r.read(cbuf);
	return new String(cbuf, 0, i);
    }

    private static PyDictionary createNamespace(Map<String, PyObject> map) {
	Map<PyObject, PyObject> t = new HashMap<PyObject, PyObject>();
	for (Entry<String, PyObject> e : map.entrySet())
	    t.put(new PyString(e.getKey()), e.getValue());
	return new PyDictionary(t);
    }
}
