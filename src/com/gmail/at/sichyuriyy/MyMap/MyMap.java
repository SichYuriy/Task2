package com.gmail.at.sichyuriyy.MyMap;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


public class MyMap<K, V> implements Map<K, V> {

	  private ArrayList<K> keys;
	  private ArrayList<V> values;
	  private ArrayList<Long> timeOfCreation;
	  private long timeForLife = -1;
	  private boolean isAlive(int i) {
		  if(timeForLife == -1) return true;
		  if(System.currentTimeMillis() - timeOfCreation.get(i) > timeForLife)
			  return false;
		  else
			  return true;
	  }
	  public MyMap() {
	    keys = new ArrayList<K>();
	    values = new ArrayList<V>();
	    timeOfCreation = new ArrayList<Long>();
	  }
	  public MyMap(long time) {
		    keys = new ArrayList<K>();
		    values = new ArrayList<V>();
		    timeOfCreation = new ArrayList<Long>();
		    timeForLife = time;
		    
		  }

	  /** Return the number of mappings in this Map. */
	  public int size() {
	    return keys.size();
	  }

	  /** Return true if this map is empty. */
	  public boolean isEmpty() {
	    return size() == 0;
	  }

	  /** Return true if o is contained as a Key in this Map. */
	  public boolean containsKey(Object o) {
		  for(int i=0; i < timeOfCreation.size(); i++)
			  if(!isAlive(i))
				  remove(i);
		 
		  return keys.contains(o);
	  }

	  /** Return true if o is contained as a Value in this Map. */
	  public boolean containsValue(Object o) {
		  for(int i=0; i < timeOfCreation.size(); i++)
			  if(!isAlive(i))
				  remove(i);
	    return values.contains(o);
	  }

	  /** Get the object value corresponding to key k. */
	  public V get(Object k) {
	    int i = keys.indexOf(k);
	    if(!isAlive(i)) {
	    	remove(i);
	    	return null;
	    }
	    	
	    if (i == -1)
	      return null;
	    return values.get(i);
	  }

	  /** Put the given pair (k, v) into this map, by maintaining "keys"
	   * in sorted order.
	   */
	  public V put(K k, V v) {
	    for (int i=0; i < keys.size(); i++) {
	      V old = values.get(i);

	      /* Does the key already exist? */
	      if (((Comparable)k).compareTo(keys.get(i)) == 0) {
	        values.set(i, v);
	        timeOfCreation.set(i, System.currentTimeMillis());
	        return old;
	      }

	      /* Did we just go past where to put it?
	       * i.e., keep keys in sorted order.
	       */
	      if (((Comparable)k).compareTo(keys.get(i)) == +1) {
	        int where = i > 0 ? i -1 : 0;
	        keys.add(where, k);
	        values.add(where, v);
	        timeOfCreation.set(i, System.currentTimeMillis());
	        return null;
	      }
	      
	    }

	    // Else it goes at the end.
	    keys.add(k);
	    values.add(v);
	    timeOfCreation.add(System.currentTimeMillis());
	    return null;
	  }

	  /** Put all the pairs from oldMap into this map */
	  public void putAll(java.util.Map oldMap) {
	    Iterator keysIter = oldMap.keySet().iterator();
	    while (keysIter.hasNext()) {
	      K k = (K)keysIter.next();
	      V v = (V)oldMap.get(k);
	      put(k, v);
	    }
	  }

	  public V remove(Object k) {
	    int i = keys.indexOf(k);
	    if (i == -1)
	      return null;
	    V old = values.get(i);
	    keys.remove(i);
	    values.remove(i);
	    timeOfCreation.remove(i);
	    return old;
	  }

	  public void clear() {
	    keys.clear();
	    values.clear();
	    timeOfCreation.clear();
	  }

	  public java.util.Set keySet() {
		  for(int i=0; i < timeOfCreation.size(); i++)
			  if(!isAlive(i))
				  remove(i);
	    return new TreeSet(keys);
	  }

	  public java.util.Collection values() {
		  for(int i=0; i < timeOfCreation.size(); i++)
			  if(!isAlive(i))
				  remove(i);
	    return values;
	  }

	  /** The Map.Entry objects contained in the Set returned by entrySet().
	   */
	  private class MyMapEntry<K,V> implements Map.Entry<K,V>, Comparable {
	    private K key; 
	    private V value;
	    MyMapEntry(K k, V v) {
	      key = k;
	      value = v;
	    }
	    public K getKey() { return key; }
	    public V getValue() { return value; }
	    public Object setValue(Object nv) {
	      throw new UnsupportedOperationException("setValue");
	    }
	    public int compareTo(Object o2) {
	      if (!(o2 instanceof MyMapEntry))
	        throw new IllegalArgumentException(
	          "Huh? Not a MapEntry?");
	      Object otherKey = ((MyMapEntry)o2).getKey();
	      return ((Comparable)key).compareTo((Comparable)otherKey);
	    }
	    }

	  /** The set of Map.Entry objects returned from entrySet(). */
	  private class MyMapSet extends AbstractSet {
	    List list;
	    MyMapSet(ArrayList al) {
	      list = al;
	    }
	    public Iterator iterator() {
	      return list.iterator();
	    }
	    public int size() {
	      return list.size();
	    }
	  }

	  /** Returns a set view of the mappings contained in this Map.
	   * Each element in the returned set is a Map.Entry.
	   * NOT guaranteed fully to implement the contract of entrySet
	   * declared in java.util.Map.
	   */
	    public java.util.Set entrySet() {
	    if (keys.size() != values.size())
	      throw new IllegalStateException(
	        "InternalError: keys and values out of sync");
	    ArrayList al = new ArrayList();
	    for (int i=0; i<keys.size(); i++) {
	      al.add(new MyMapEntry(keys.get(i), values.get(i)));
	    }
	    return new MyMapSet(al); 
	  }
}