/**
 * 
 * @author zili.dengzl
 *
 */
public class TestClass {
	public static int  flag = 0;
	static {
		System.out.println("static TestClass's cl===" + TestClass.class.getClassLoader());
	}

	static public void f() {
		System.out.println("get flag="+flag++);
		System.out.println("in method f, TestClass's cl===" + TestClass.class.getClassLoader());
	}
}
