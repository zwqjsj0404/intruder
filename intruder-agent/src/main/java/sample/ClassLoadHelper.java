package sample;

/**
 * no use now, just for reference
 * 
 * @author zili.dengzl
 * 
 */
@Deprecated
public class ClassLoadHelper {
	public static Class<?> loadClassFromBytes(ClassLoader parentLoader, String name, byte[] classBytes) {
		Class<?> clazz = new ClassLoader(parentLoader) {
			public Class<?> loadClassFromBytes(String name, byte[] b) {
				return defineClass(name, b, 0, b.length);
			}
		}.loadClassFromBytes(name, classBytes);

		return clazz;
	}
}
