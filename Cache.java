package Leet;

import java.util.HashMap;
import java.util.Map;

class CacheEntry
{
	int key;
	int value;//unnecessary
	long lastAccessed;
}
public class Cache {
	
	int capacity;
	int size;
	Map<Integer,CacheEntry> cacheEntry;
	
	public Cache(int capacity)
	{
		this.capacity = capacity;
		cacheEntry = new HashMap<Integer, CacheEntry>();
	}
	public int get(int key) {
		
		if (cacheEntry.containsKey(key))
			return key;
	        
		return -1;
	 }
	public void put(int key)
	{
		CacheEntry entry, lruEntry, lruTemp;
		
		int lruKey = 0;
		long lru=Long.MAX_VALUE;
	
		entry = cacheEntry.get(key);
		
		//create a new entry in the cache if it the map doesn't have one
		if(entry==null)
		{
			entry = new CacheEntry();
			entry.key = key;
			entry.value = 1;
			//System.out.println("If:::"+"key::"+entry.key+" value::"+entry.value);
			size++;
		
		
		}
		//if it exists, increment the value
		else
		{
			entry.value++;

		}
		//update timestamp 
		entry.lastAccessed = System.nanoTime();
		
		//if not at MAX, then add to map
		if(this.size <= this.capacity)
			cacheEntry.put(key, entry);
		//if maxed out
		else
		{
			//get least accessed time
			for(Map.Entry<Integer, CacheEntry> i : cacheEntry.entrySet())
			{
				lruTemp= i.getValue();
				if(lruTemp.lastAccessed<lru)
				{
					lru = lruTemp.lastAccessed;
					lruEntry = lruTemp;
					lruKey = lruTemp.key;
					
					
				}
			}

			
			cacheEntry.remove(lruKey);
//			entry = new CacheEntry();
//			entry.key = key;
//			entry.value = 1;
//			entry.lastAccessed =  System.nanoTime();
			cacheEntry.put(key, entry);
			
			size++;
		}
		
	}
	public static void main(String[] args) {
		 Cache cache = new Cache(4);
		 cache.put(1);
		 cache.put(2);
		 cache.put(3);
		 cache.put(1);
		 cache.put(4);
		 cache.put(5);
		 
		 for(Map.Entry<Integer, CacheEntry> j : cache.cacheEntry.entrySet())
		 {
			 System.out.println(j.getKey());
		 }
		 
	}

}

