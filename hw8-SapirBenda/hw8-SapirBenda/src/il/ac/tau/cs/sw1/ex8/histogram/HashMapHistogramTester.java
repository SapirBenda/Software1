package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IllegalItemException;
import il.ac.tau.cs.sw1.ex8.histogram.IllegalKValueException;


public class HashMapHistogramTester {
	public static void main(String[] args) {
	
		List<Integer> intLst = Arrays.asList(1, 2, 1, 2, 3, 4, 3, 1);
		IHistogram<Integer> intHist = new HashMapHistogram<>();
		for (int i : intLst) {
			intHist.addItem(i);
		}
		 final int intValue = Integer.reverseBytes(0x7F454C46);
	        HashMapHistogram<Integer> intHist1 = new HashMapHistogram<>();
	        for (int i = 0; i < 42; ++i) {
	            intHist1.addItem(intValue);
	        }
		System.out.println("hashmap = ");
		intHist1.printhashmap();
			
		for (int i = 0; i < 42; ++i) {
			try {
			intHist1.removeItem(intValue);
			} catch(IllegalItemException e) {
				System.out.println("problem in iteration " + i);
				continue;
			}
		}
		final String stringValue1 = "Botswana";
        final String stringValue2 = "Ghana";

        final int kValue1 = 2394071;
        final int kValue2 = 31653431;
        HashMapHistogram<String> stringHist = new HashMapHistogram<>();
        
        try {
	        stringHist.addItemKTimes(stringValue1, kValue1);
	        
        }catch (Exception e) {
        	System.out.println("problem 1");
        }
        try {
        	stringHist.addItemKTimes(stringValue2, kValue2);
	        
        }catch (Exception e) {
        	System.out.println("problem 2");
        }
        
        final String nonExistentStringValue = "Petah Tikva";
        try {
        	stringHist.removeItemKTimes(nonExistentStringValue, 6);
        } catch (IllegalKValueException | IllegalItemException e) {
        	System.out.println("catched the exception for non");
        }
        try {
        	stringHist.removeItemKTimes(stringValue2, 0);
           } catch (IllegalKValueException | IllegalItemException e) {
            System.out.println("removeItemKTimes did not throw exception with k = 0"); 	
           }

		
		
		if (intHist.getCountForItem(1) != 3) {
			System.out.println("ERROR 1");
		}
		if (intHist.getCountForItem(5) != 0) {
			System.out.println("ERROR 2");
		}
		
		Iterator<Integer> intHistIt = intHist.iterator();
		List<Integer> tmpList = new ArrayList<Integer>();
		while (intHistIt.hasNext()) {
			tmpList.add(intHistIt.next());
		}
		
//		System.out.println(tmpList);
		
		if (tmpList.get(0) != 1) {
			System.out.println("ERROR 3");
		}
		if (tmpList.size() != 4) {
			System.out.println("ERROR 4");
		}

		IHistogram<String> stringHist1 = new HashMapHistogram<>();
		IHistogram<String> anotherHist = new HashMapHistogram<>();
		
//		System.out.println("stringHist 1 = ");
//		stringHist.printhashmap();
//		System.out.println();
		
		try {
			stringHist1.addItemKTimes("bb", 5);
			stringHist1.addItemKTimes("aa", 5);
		} catch (IllegalKValueException exp) {
			System.out.println("ERROR 5");
		}
		
//		System.out.println("stringHist 2 = ");
//		stringHist.printhashmap();
//		System.out.println();
		
		stringHist1.addItem("abc");
		stringHist1.addItem("de");
		stringHist1.addItem("abc");
		stringHist1.addItem("de");
		stringHist1.addItem("abc");
		stringHist1.addItem("de");
		stringHist1.addItem("de");
		
//		System.out.println("stringHist 3 = ");
//		stringHist.printhashmap();
//		System.out.println();
		
		if (stringHist1.getCountForItem("abc") != 3) {
			System.out.println("ERROR 6");
		}
		try {
			stringHist1.removeItem("abba"); // this should throw an exception
			System.out.println("ERROR 7");
		} catch (IllegalItemException exp) {

		}
//		System.out.println("stringHist 4 = ");
//		stringHist.printhashmap();
//		System.out.println();

		try {
			stringHist1.removeItemKTimes("de", 2);
		} catch (Exception exp) {
			System.out.println("ERROR 8");
		}
		
//		System.out.println("stringHist 5 = ");
//		stringHist.printhashmap();
//		System.out.println();
		
		try {
			stringHist1.removeItemKTimes("abc", -3);
			System.out.println("ERROR 9");
		} catch (Exception exp) {
			
		}
		
//		System.out.println("stringHist 6 = ");
//		stringHist.printhashmap();
//		System.out.println();
		
		if (stringHist1.getCountForItem("de") != 2) {
			System.out.println("ERROR 10");
		}
		try {
			stringHist1.addItemKTimes("de", 2);
		} catch (IllegalKValueException exp) {
			System.out.println("ERROR 11");
		}
		
//		System.out.println("stringHist 7 = ");
//		stringHist.printhashmap();
//		System.out.println();

		Iterator<String> it = stringHist1.iterator();
		/*
		 * the order of the returned items should be: "aa", "bb", "de", "abc" aa
		 * " and "bb" both appear 5 times, so in this case we sort by the
		 * natural order of the elements "aa" and "bb". This is why "aa" should
		 * appear before "bb"
		 */
		if (!it.next().equals("aa")) {
			System.out.println("ERROR 12");
		}
		if (!it.next().equals("bb")) {
			System.out.println("ERROR 13");
		}
		if (!it.next().equals("de")) {
			System.out.println("ERROR 14");
		}

		anotherHist.addAll(Arrays.asList("abc", "ff"));
		anotherHist.update(stringHist1);
		if (anotherHist.getCountForItem("abc") != 4) {
			System.out.println("ERROR 15");
		}

		if (anotherHist.getCountForItem("de") != 4) {
			System.out.println("ERROR 16");
		}
		
		
		

		System.out.println("done!");
	}

}
