package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{
	
	public HashMap <T,Integer> hashmap = new HashMap<>();
	
	@Override
	public Iterator<T> iterator() {
		Iterator<T> I = new HashMapHistogramIterator<T>(hashmap);
		return I;
	}

	@Override
	public void addItem(T item) {
		int times = getCountForItem(item);
		hashmap.put(item,times + 1);
	}

	@Override
	public void removeItem(T item) throws IllegalItemException {
		int times = getCountForItem(item);
		if (times > 0) 
			hashmap.replace(item, times -1);
		else
			throw new IllegalItemException();
	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValueException {
		if(k < 1)
			throw new IllegalKValueException(k);
		else {
			int times = getCountForItem(item); 
			if(times ==0) {
				addItem(item);
				hashmap.replace(item, getCountForItem(item) +k -1);
			}
			else
				hashmap.replace(item, getCountForItem(item) +k);
		}	
	}

	@Override
	public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException {
		int times = getCountForItem(item);
		 if (times == 0) 
			throw new IllegalItemException();
		else if (times < k || k<1) 
			throw new IllegalKValueException(k);
		else 
			hashmap.replace(item, times -k);
	}

	@Override
	public int getCountForItem(T item) {
		Integer times = hashmap.get(item);
		if(times == null)
			return 0;
		return times;
	}

	@Override
	public void addAll(Collection<T> items) {
		for( T element : items) {
			addItem(element);
		}
	}

	@Override
	public void clear() {
		hashmap.clear();
		
	}

	@Override
	public Set<T> getItemsSet() {
		return hashmap.keySet();
	}


	@Override
	public void update(IHistogram<T> anotherHistogram) {
		int timesforthis, timesforother;
		Iterator<T> other = anotherHistogram.iterator();
		while(other.hasNext()) {
			T element = other.next();
			timesforthis = getCountForItem(element);
			timesforother = anotherHistogram.getCountForItem(element);
			if (timesforthis == 0) 
				addItem(element);
			hashmap.replace(element, timesforthis + timesforother);
		}
	}
	
	@Override
	public void printhashmap() {
		for(Map.Entry<T, Integer> entry: hashmap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
		
	@Override
	public Integer getsize() {
		return hashmap.size();
	}
}
