package sample.test;
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
	 * 测试方法： 先在D:\learn\dzl\java-instrument\target\classes下 java InstrumentTest
	 * 然后在java -jar java-instrument-0.0.1-SNAPSHOT.jar
	 * java-instrument-0.0.1-SNAPSHOT.jar 6832
	 * D:\learn\dzl\java-instrument\target\classes\agent.properties
	 */

	public static void main(String[] args) throws Exception {
		// try {
		// Class.forName("TestNewClass");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		System.out
				.println("main's cl=" + InstrumentTest.class.getClassLoader());
		System.out.println("URLClassLoader's classpath is "
				+ Arrays.asList(((URLClassLoader) InstrumentTest.class
						.getClassLoader()).getURLs()));

		URL url = new URL("file:/D:/learn/dzl/java-instrument/target/classes/");
		MyClassLoader loader = new MyClassLoader(new URL[] { url },
				InstrumentTest.class.getClassLoader());
		System.out.println("new cl=" + loader);
		Class<?> c = loader.loadClass("TestClass");
		Object o = c.newInstance();
		Method m = c.getMethod("f");
		for (;;) {
			m.invoke(o);
			Thread.sleep(1000L * 5);
		}

	}

}

class MyClassLoader extends URLClassLoader {

	ClassLoader parent;

	public MyClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
		this.parent = parent;
	}

	protected synchronized Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name);
		if (c == null) {
			try {
				c = findClass(name);
			} catch (Exception e) {
				System.out.println(name + " is not load by MyClassLoader");
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
