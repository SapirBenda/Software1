package il.ac.tau.cs.sw1.hw6;

public class Test_sectionB_sapir {
	
public static void main(String[] args) {
		
	int [] sort_array = {1,2,3,4,5,6,7,8,9,10};
	int [] array = {2,3,6,7,8,5,4,1,10,9};
	int [] small_array = {13,29};
	
	boolean x = SectionB.contains(sort_array,5);
	if(!x)
		System.out.println("error 1 - 5");
	else if(SectionB.contains(sort_array,0))
		System.out.println("error 1 - 0");
	else
		System.out.println("contains is good");
			
	if(SectionB.unknown(sort_array) != 1)
		System.out.println("error 2 - sort");
	else if(SectionB.unknown(array) != 0)
		System.out.println("error 2 - not sort");
	else if(SectionB.unknown(null) != 0)
		System.out.println("error 2 - null");
	else if(SectionB.unknown(small_array) != 0)
		System.out.println("error 2 - length");
	else
		System.out.println("unknow is good");	
	
	
	if(SectionB.max(sort_array) != 10)
		System.out.println("error sort_array - 10");
	else if(SectionB.max(small_array)!=29)
		System.out.println("error small_array - 29");
	else
		System.out.println("max is good");
	
	
	if(SectionB.min(sort_array) != 1)
		System.out.println("error sort_array - 1");
	else if(SectionB.min(small_array)!=13)
		System.out.println("error small_array - 13");
	else if(SectionB.min(array)!=1)
		System.out.println("error array - 1");
	else
		System.out.println("min is good");
	
	
	if(!SectionB.reverse("sapir").equals("ripas")) 
		System.out.println("error revers - sapir");
	else if(!SectionB.reverse("or").equals("ro"))
		System.out.println("error revers - or");
	else if(!SectionB.reverse("yam").equals("may"))
		System.out.println("error revers - yam");
	else if(!SectionB.reverse("abcdef").equals("fedcba"))
		System.out.println("error revers - yam");
	else
		System.out.println("revers is good");
	
	int [] c = {2,2,2,2,2,2,4,2,2,2,2};
	if(SectionB.guess(sort_array).equals(sort_array))
		System.out.println("erroe guess - sort");
	else if (SectionB.guess(c).equals(c))
		System.out.println("erroe guess - 2,4");
	else
		System.out.println("guess is good");
	
	System.out.println();
	System.out.println("Done!");
	}
}
