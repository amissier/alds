package Leet;

import java.util.Map;

import java.util.HashMap;

class CacheItem <T, U>
{
    T key; U value;
    CacheItem<T, U> prev, next;
    
    CacheItem(T key, U value)
    {
    	this.key = key;
    	this.value = value;
    }
}
public class LRUGenerics <K, V> {

    int capacity;
    Map<K,CacheItem<K,V>> cacheMap;
    CacheItem<K, V> head, tail;
    
    public LRUGenerics(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<K,CacheItem<K,V>>(capacity);
       
            
    }
    
    public void addToList(CacheItem<K, V> node)
    {
        if(head!=null)
        {
        node.next = head;
        node.prev=null;
        head.prev=node;
        }
        
        head = node;
        if(tail==null)
            tail=head;
    }
    
    public void remove(CacheItem<K, V> node)
    {
    	CacheItem<K, V> nodeNext = node.next;
    	CacheItem<K, V> nodePrev = node.prev;
        if(nodePrev!=null)
            nodePrev.next = nodeNext;
        else
            head = nodeNext;
            
        if(nodeNext!=null)
            nodeNext.prev = nodePrev;
        else
            tail = nodePrev;
       
    }
    public V get(K key) 
    {
    	
    	
      
        if(cacheMap.containsKey(key))
        {
        	CacheItem<K,V> node = cacheMap.get(key);
            remove(node);
            addToList(node);
            return node.value;
        }
            
        else
            return null;
    }
    
    public void put(K key, V value) {

        if(cacheMap.containsKey(key))
        {
        	CacheItem<K,V> node = cacheMap.get(key);
            node.value = value;
            remove(node);
            addToList(node);
        }
        else
        {
        	CacheItem<K, V> newNode = new CacheItem<K, V>(key,value);
            
            newNode.prev=null;
            newNode.next=null;
            
            if(cacheMap.size()==capacity)
            {
                cacheMap.remove(tail.key);
                remove(tail);
                
            }
            cacheMap.put(key,newNode);
            addToList(newNode);
        }
       
    }

public static void main(String args[])
{
	LRUGenerics<Integer, Integer> cache = new LRUGenerics<Integer, Integer>(4);
	
	 cache.put(1,1);
	 cache.put(2,1);
	
	 cache.put(3,1);
	 cache.put(1,1);
	 cache.put(4,1);
	 cache.put(5,1);
	 CacheItem<Integer, Integer> node = cache.head;
	while(node!=null)
	{
		System.out.println(node.key);
		node=node.next;
	}
}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */