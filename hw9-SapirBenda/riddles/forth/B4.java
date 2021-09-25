package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.Iterator;

public class B4 implements Iterator<String> {
	
	private String[] arr;
	private int k;
	private int curr;
	
	public B4(String [] strings, int k) {
		this.arr = new String [strings.length*(k+1)];
		for (int i = 0; i < strings.length; i++){
			for (int j = 0; j < k; j++){
				arr[i+strings.length*j] = strings[i];
				}
			}
		this.k =k;
		}

	@Override
	public boolean hasNext() {
		if (this.arr[curr] != null && curr < this.arr.length) return true;
		return false;
	}
	@Override
	public String next() {
		this.curr++;
		return this.arr[curr-1];
	}
	
}
