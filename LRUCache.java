package Leet;

import java.util.Map;
import java.util.HashMap;

class Cachee
{
    int key, value;
    Cachee prev, next;
    
    Cachee(int key, int value)
    {
    	this.key = key;
    	this.value = value;
    }
}
public class LRUCache {

    int capacity;
    Map<Integer,Cachee> cacheMap;
    Cachee head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<Integer,Cachee>(capacity);
       
            
    }
    
    public void addToList(Cachee node)
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
    
    public void remove(Cachee node)
    {
        Cachee nodeNext = node.next;
        Cachee nodePrev = node.prev;
        if(nodePrev!=null)
            nodePrev.next = nodeNext;
        else
            head = nodeNext;
            
        if(nodeNext!=null)
            nodeNext.prev = nodePrev;
        else
            tail = nodePrev;
       
    }
    public int get(int key) 
    {
      
        if(cacheMap.containsKey(key))
        {
            Cachee node = cacheMap.get(key);
            remove(node);
            addToList(node);
            return node.value;
        }
            
        else
            return -1;
    }
    
    public void put(int key, int value) {

        if(cacheMap.containsKey(key))
        {
            Cachee node = cacheMap.get(key);
            node.value = value;
            remove(node);
            addToList(node);
        }
        else
        {
            Cachee newNode = new Cachee(key,value);
            
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
	 LRUCache cache = new LRUCache(4);
	 cache.put(1,1);
	 cache.put(2,1);
	
	 cache.put(3,1);
	 cache.put(1,1);
	 cache.put(4,1);
	 cache.put(5,1);
for(Map.Entry<Integer, Cachee> m: cache.cacheMap.entrySet())
	System.out.println(m.getKey());
}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */