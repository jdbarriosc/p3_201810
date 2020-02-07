package model.data_structures;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * taken from https://algs4.cs.princeton.edu/31elementary/SequentialSearchST.java.html
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class SequentialSearchST<Key, Value extends Comparable<Value>> {
	private ListaDoblementeEncadenada<Value> values;      // the linked list of key-value pairs
	private Key key;
	
	public Key getKey(){
		return key;
	}
	public void setKey(Key llave){
		 key=llave;
	}
	public ListaDoblementeEncadenada<Value> getValues(){
		return values;
	}
	

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 *
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return values==null?0:values.size();
	}

	/**
	 * Returns true if this symbol table is empty.
	 *
	 * @return {@code true} if this symbol table is empty;
	 *         {@code false} otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns true if this symbol table contains the specified key.
	 *
	 * @param  key the key
	 * @return {@code true} if this symbol table contains {@code key};
	 *         {@code false} otherwise
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public boolean contains(Value val) {
		if (val == null) throw new IllegalArgumentException("argument to contains() is null");
		if(values!=null)
		{	
			Iterator<Value> iter=values.iterator();
			while(iter.hasNext())
			{
				if(iter.next().compareTo(val)==0)
					return true;
			}
		}
		return false;
	}

//	/**
//	 * Returns the value associated with the given key in this symbol table.
//	 *
//	 * @param  key the key
//	 * @return the value associated with the given key if the key is in the symbol table
//	 *     and {@code null} if the key is not in the symbol table
//	 * @throws IllegalArgumentException if {@code key} is {@code null}
//	 */
//	public Value get(Key key) {
//		if (key == null) throw new IllegalArgumentException("argument to get() is null"); 
//		Iterator<Value> iter=values.iterator();
//		while(iter.hasNext())
//		{
//			if(iter.next().compareTo(val)==0)
//				return true;
//		}
//		return false;
//	}

	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old 
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is {@code null}.
	 *
	 * @param  key the key
	 * @param  val the value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void put(Value val) {
		if (val == null)throw new IllegalArgumentException("Argument to put() is null");
		if(values==null)
			values=new ListaDoblementeEncadenada<Value>();

		values.addInOrder(val);

	}

	/**
	 * Removes the specified key and its associated value from this symbol table     
	 * (if the key is in this symbol table).    
	 *
	 * @param  key the key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void delete(Value val) {
		if (val == null) throw new IllegalArgumentException("argument to delete() is null");
		if(values!=null)
		{
			
			Iterator<Value> iter=values.iterator();
			int c=0;
			while(iter.hasNext())
			{
				if(iter.next().compareTo(val)==0)
				{
					values.remove(c);
					return;
				}
				c++;
	
			}
		}
		
	}

//	// delete key in linked list beginning at Node x
//	// warning: function call stack too large if table is large
//	private Node delete(Node x, Key key) {
//		if (x == null) return null;
//		if (key.equals(x.key)) {
//			n--;
//			return x.next;
//		}
//		x.next = delete(x.next, key);
//		return x;
//	}


//	/**
//	 * Returns all keys in the symbol table as an {@code Iterable}.
//	 * To iterate over all of the keys in the symbol table named {@code st},
//	 * use the foreach notation: {@code for (Key key : st.keys())}.
//	 *
//	 * @return all keys in the symbol table
//	 */
//	public Iterable<Key> keys()  {
//		LinkedListQueue<Key> queue = new LinkedListQueue<Key>();
//		for (Node x = first; x != null; x = x.next)
//			queue.enqueue(x.key);
//		return queue;
//	}


}