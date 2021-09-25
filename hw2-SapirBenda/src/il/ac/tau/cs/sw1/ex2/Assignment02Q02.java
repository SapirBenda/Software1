package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q02{

	public static void main(String[] args){
		// do not change this part below
		double piEstimation = 0.0;
		double sum = 0;
		int index;
		int n = Integer.parseInt(args[0]); // according the given assumptions
		
		
		for(index = 0; index <n; index++)
			sum += Math.pow(-1, index) * (1.0 / (2.0*index + 1.0));
			
		piEstimation = 4 * sum;
		
		// do not change this part below
		System.out.println(piEstimation + " " + Math.PI);

	}
}
