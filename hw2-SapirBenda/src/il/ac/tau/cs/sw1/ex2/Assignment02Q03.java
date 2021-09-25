package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q03{

	public static void main(String[] args){
		int numOfEven = 0;
		int n = -1;
		n = Integer.parseInt(args[0]); // according the given assumptions n>=3
		int [] fibarr = new int [n];
		fibarr[0] = 1;
		fibarr[1] = 1;
		
		int index = 2;
		for (index = 2; index< n; index++)
			fibarr[index] = fibarr[index-1] + fibarr[index-2];

		System.out.println("The first "+ n +" Fibonacci numbers are:");
	
		for(index = 0; index < fibarr.length -1; index++){
			System.out.print(fibarr[index] + " ");
			if (fibarr[index] % 2 == 0)
				numOfEven ++;
		}
		
		if (fibarr[n -1] % 2 == 0)
			numOfEven ++;
		
		System.out.println(fibarr[n-1]);
		System.out.println("The number of even numbers is: "+numOfEven);
	}	
}
