package sample.test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * @author zili.dengzl
 * 
 */
public class InstrumentTest {

	/**
	 * 测试方法： 先在D:\learn\dzl\java-instrument\target\classes下 java InstrumentTest 然后在java -jar
	 * java-instrument-0.0.1-SNAPSHOT.jar java-instrument-0.0.1-SNAPSHOT.jar 6832
	 * D:\learn\dzl\java-instrument\target\classes\agent.properties
	 */

	public static void main(String[] args) throws Exception {
		// try {
		// Class.forName("TestNewClass");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		System.out.println("Appclassloader=" + InstrumentTest.class.getClassLoader());
		System.out.println("Appclassloader's classpath is "
				+ Arrays.asList(((URLClassLoader) InstrumentTest.class.getClassLoader()).getURLs()));

		File jar = new File("intruder-agent-0.1.jar");
		URL url = new URL("file:" + jar.getAbsolutePath());

		System.out.println("url=" + url.getPath());
		MyClassLoader loader = new MyClassLoader(new URL[] { url }, InstrumentTest.class.getClassLoader());

		System.out.println("new classloader=" + loader);
		Class<?> c = loader.loadClass("sample.test.TestClass");
		Object o = c.newInstance();
		Method m = c.getMethod("foo");

		for (;;) {
			m.invoke(o);
			Thread.sleep(1000L * 10);
		}

	}

}

class MyClassLoader extends URLClassLoader {

	ClassLoader parent;

	public MyClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
		this.parent = parent;
	}

	protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name);
		if (c == null) {
			try {
				c = findClass(name);
			} catch (Exception e) {
				//System.out.println(name + " is not load by MyClassLoader");
			}

			if (c == null) {
				if (parent != null) {
					c = parent.loadClass(name);
				}
			}
		}
		if (resolve) {
			resolveClass(c);
		}
		return c;
	}

}
