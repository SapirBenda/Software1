package il.ac.tau.cs.sw1.hw6;

public class SectionB {
	
	/*
	* @post $ret == true iff exists i such that array[i] == value
	*/
	public static boolean contains(int[] array, int value) { 
		int index;
		for(index =0; index< array.length; index++) {
			if(array[index] == value)
				return true;
		}
		return false;
	}
	
	// there is intentionally no @post condition here 
	/*
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	*/
	public static int unknown(int[] array) { 
		if(array == null || array.length<= 2)
			return 0;
		int index;
		for(index=0; index<array.length-1;index++) {
			if(array[index] > array[index+1]) {
				return 0;	
			}
				
		}
		return 1;
	}
	/*
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre array.length >= 1
	* @post for all i array[i] <= $ret
	*/
	public static int max(int[] array) { 
		return array[array.length-1]; // the array is sorted!
		
	}
	
	/*
	* @pre array.length >=1
	* @post for all i array[i] >= $ret
	* @post Arrays.equals(array, prev(array))
	*/
	public static int min(int[] array) { 
		int min = array[0],index;
		for(index =1; index< array.length; index++) {
			if(array[index] < min) {
				min = array[index];
			}
		}
		return min;
	}
	
	/*
	* @pre word.length() >=1
	* @post for all i : $ret.charAt(i) == word.charAt(word.length() - i - 1)

	*/
	public static String reverse(String word){
		char [] new_word = new char[word.length()];
		int index, len = word.length();
		for(index =0; index<len;index++) {
			new_word[index] = word.charAt(len - index - 1);
		}
		String poli = new String(new_word);
		return poli;
	}
	
	/*
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre exist i,j such that: array[i] != array[j]
	* @post !Arrays.equals($ret, Arrays.sort($ret))
	* @post for any x: contains(prev(array),x) == true iff contains($ret, x) == true
	*/
	public static int[] guess(int[] array) { 
		int i = array[0];
		int index=1 ;
		int [] ret = new int [array.length];
		System.arraycopy(array, 0, ret, 0, array.length);
		while(i== array[index]) {
			index++;
		}
		ret[0] = array[index];
		ret[index] = array[0];
		return ret;
	}
		
}