package sample.test;

/**
 * @author zili.dengzl
 * 
 */
public class TestClass {
	public static int flagFoo = 0;
	public static int flagBar = 0;
	static {
		System.out.println("TestClass init... TestClass's classloader is "
				+ TestClass.class.getClassLoader());
	}

	public void foo() {
		System.out.println("in method foo, flag=" + flagFoo++);
	}

	public void bar() {
		System.out.println("in method bar, flag=" + flagBar++);
	}
}
