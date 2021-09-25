package il.ac.tau.cs.sw1.ex8.histogram;
import java.util.*;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T>{
	
	private HashMap <T,Integer> hashmapIterator;
	private Iterator<T> I;
	
	public HashMapHistogramIterator(HashMap <T,Integer> hashmap) {
		hashmapIterator = hashmap; 
		I = sortedListIterator();
	}
	
	private Iterator<T> sortedListIterator(){
		Set <T> s = hashmapIterator.keySet();
		List<T> keys = new ArrayList<T>(s);
		Collections.sort(keys, new HashMapHistogramCompartor() );
		Collections.reverse(keys);
		return keys.iterator();
	}
	
	public class HashMapHistogramCompartor implements Comparator<T>{
    	public int compare(T a ,T b) {
    		Integer avul = hashmapIterator.get(a);
    		Integer bvul =hashmapIterator.get(b);
    		if (avul != bvul)
    			return Integer.compare(avul, bvul);
    		return -a.compareTo(b);
    	}
    }
	
	
	@Override
	public boolean hasNext() {
		return I.hasNext(); 
	}

	@Override
	public T next() {
		return I.next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(); //no need to change this
	}
}
