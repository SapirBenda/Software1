package il.ac.tau.cs.sw1.ex1;

public class Assignment1 
{
	
	private static boolean triple_inequality(int a, int b, int c) 
	{
		if ((a + b) < c) 
			return false;
		return true;
	}
	
	private static boolean right_triangle(int a, int b, int c) 
	{
		if (a*a + b*b != c*c) 
			return false;
		return true;
	} 
	
	
	public static void main(String[] args)
	{
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		int z = Integer.parseInt(args[2]);
		boolean h = false;

		if (x <= 0 || y <= 0 || z<= 0)
			System.out.println("Invalid input!");
		
		else
		{
			boolean r1 = right_triangle(x,y,z);
			boolean r2 = right_triangle(z,x,y);
			boolean r3 = right_triangle(y,z,x);
			
			boolean b1 = triple_inequality(x,y,z);
			boolean b2 = triple_inequality(z,x,y);
			boolean b3 = triple_inequality(y,z,x);
		
		
			if (b1 && b2 && b3) // a valid triangle
			{
				h = true;
				
				if (r1 || r2 || r3) // a valid right triangle
					System.out.println("The input ("+x+", "+y+", "+z+") defines a valid right triangle!");
				else
					System.out.println("The input ("+x+", "+y+", "+z+") defines a valid triangle!");	
			}
			
			else if (!h) 
				System.out.println("The input ("+x+", "+y+", "+z+") does not define a valid triangle!");
			
		}
	}
}
