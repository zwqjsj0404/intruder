package sample.test;

public class SimpleClassForTransformedCall {
	public static void call(Object o, int flag) {
		System.out.println("SimpleClassForTransformedCall::loader="
				+ SimpleClassForTransformedCall.class.getClassLoader());
		System.out.println("SimpleClassForTransformedCall::obj=" + o);
		System.out.println("SimpleClassForTransformedCall::flag=" + flag);
	}

}

class Caller {
	public void foo() {
		SimpleClassForTransformedCall.call(this, 1);
	}
}
