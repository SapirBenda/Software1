package il.ac.tau.cs.sw1.ex9.riddles.second;

public class B2 extends A2 {

	public A2 getA(boolean randomBool) {
		if (!randomBool)
			return new A2();
		return new B2();
	}
	
	@Override
	public String foo(String s) {
		return s.toUpperCase();
	}
	
}
