package il.ac.tau.cs.sw1.hw3;

import java.util.Arrays;

public class StringUtils {

	public static String findSortedSequence(String str) {
		
		if (str.length()==0 || str.charAt(0) == ' ') // empty or spaces string
			return "";
					
		String [] splitedstr = str.split(" ");
		String maxsubstr = splitedstr[splitedstr.length -1];
		int index, subindex;
		boolean inorder;
		String optional="";
		
		for (index =0 ; index < splitedstr.length-1; index++) {

			if (splitedstr[index].compareTo(splitedstr[index+1]) <= 0) { // in order
				optional = splitedstr[index] + " " + splitedstr[index+1];
				subindex = index +1;
				inorder = true;

				while (subindex < splitedstr.length-1 && inorder) {
					if (splitedstr[subindex].compareTo(splitedstr[subindex+1]) <= 0) { // in order
						optional = optional + " " + splitedstr[subindex+1];
					}
					else if (maxsubstr.split(" ").length <= optional.split(" ").length) {
						maxsubstr = optional;
						inorder = false;
						optional = "";
					}
					if ((subindex == splitedstr.length-2) && (maxsubstr.split(" ").length <= optional.split(" ").length)) { // maxsubstr incloud last word
						maxsubstr = optional;
					}
					subindex++;
				}
			}			
		}			
		return maxsubstr;
	}

	
	public static boolean isAnagram(String a, String b) {
		if (a.length() == 0)
			a = " ";
		char [] aArr = new char[a.length()];
		if (b.length() == 0)
			b = " ";
		char [] bArr = new char[b.length()];

		int inda; // index for aArr
		int indb;// index for bArr
		char space = ' ';
	
		for (inda = 0 ; inda <aArr.length; inda++) {
			aArr[inda] = Character.toLowerCase(a.charAt(inda));
		}
		for (indb = 0 ; indb <bArr.length; indb++) {
			bArr[indb] = Character.toLowerCase(b.charAt(indb));
		}
		
		Arrays.sort(aArr);
		Arrays.sort(bArr);
		
		//skip spaces
		inda = 0;
		indb =0;
		while (inda < aArr.length && aArr[inda] == space )
			inda ++;
		while (indb < bArr.length && bArr[indb] == space )
			indb ++;
	
		if (aArr.length - inda +1 != bArr.length -indb+1) // they dont have the same number of letters
			return false;

		// the same namber of letters remains
		while (inda < aArr.length && indb < bArr.length) {
			if (aArr[inda] != bArr[indb])
				return false;
			inda++;
			indb++;
		}
	
		return true;	

		
	}
		
	
	public static boolean isEditDistanceOne(String a,String b){
		int a_length = a.length();
		int b_length = b.length();
		
		if (Math.abs(a_length - b_length) > 1)
			return false;
		
		int distancecounter = 0;
		
		int indexa = 0, indexb = 0;
		while (indexa < a_length && indexb < b_length) {
			
			if (a.charAt(indexa) != b.charAt(indexb)) {// different letters
				
				if (distancecounter == 1) // more than on edit
					return false;
			
				if (a_length > b_length) // remove a letter from a
					indexa++;
				else if (a_length < b_length) // remove a letter from b
					indexb++;
				else{ // change a letter in a or b
					indexa++;
					indexb++;
				}
				// we edited a word in any case 
				distancecounter++;
			}
			
			else { // equals letters
				indexa++;
				indexb++;
			}
		}
		
		// if last character is extra 
		if (indexa < a_length || indexb < b_length) // remove last letter from a or b
			distancecounter++;

		return distancecounter <= 1;
	}
	
	
}