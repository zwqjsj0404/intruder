package sample.test;

/**
 * @author zili.dengzl
 * 
 */
public class TestClass {
	public static int flag = 0;
	static {
		System.out.println("TestClass init... TestClass's classloader is " + TestClass.class.getClassLoader());
	}

	static public void foo() {
		System.out.println("in method f, flag=" + flag++);
	}
}
