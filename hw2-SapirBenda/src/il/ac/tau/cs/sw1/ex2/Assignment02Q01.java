package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q01 {

	public static void main(String[] args){
		int index;
		for (index = 0; index < args.length; index++){
			if (args[index].codePointAt(0) % 6 == 0)
				System.out.println(args[index].charAt(0));
		}
	}
}