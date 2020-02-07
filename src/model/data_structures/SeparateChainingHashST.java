package model.data_structures;
/**
 * taken from https://algs4.cs.princeton.edu/34hash/SeparateChainingHashST.java.html
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class SeparateChainingHashST<Key, Value extends Comparable<Value>> {
	
	private int n;                                // number of key-value pairs
	private int m;                                // hash table size
	private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables


	
	/**
	 * Initializes an empty symbol table with {@code m} chains.
	 * @param m the initial number of chains
	 */
	public SeparateChainingHashST(int capacity) {
		this.m =nextPrime(capacity*2);
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
		for (int i = 0; i < m; i++)
			st[i] = new SequentialSearchST<Key, Value>();
	} 

	// resize the hash table to have the given number of chains,
	// rehashing all of the keys
	private void resize(int chains) {
//		SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
//		for (int i = 0; i < m; i++) {
//			for (Key key : st[i].keys()) {
//				temp.put(key, st[i].get(key));
//			}
//		}
//		this.m  = temp.m;
//		this.n  = temp.n;
//		this.st = temp.st;
	}

	// hash value between 0 and m-1
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	} 

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 *
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return n;
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
	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	} 

	/**
	 * Returns the value associated with the specified key in this symbol table.
	 *
	 * @param  key the key
	 * @return the value associated with {@code key} in the symbol table;
	 *         {@code null} if no such value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public ListaDoblementeEncadenada<Value> get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		int i = hash(key);
		return st[i].getValues();
	} 

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
	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) return;
		

		// double table size if average length of list >= 10
		if (n >= 6*m) resize(nextPrime(2*m));

		int i = hash(key);
		if (!st[i].contains(val)) n++;
		
		st[i].put(val);
		if(st[i].getKey()==null)st[i].setKey(key);
		
	} 

	/**
	 * Removes the specified key and its associated value from this symbol table     
	 * (if the key is in this symbol table).    
	 *
	 * @param  key the key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");

		int i = hash(key);
		n-=st[i].getValues().size();
		st[i]=null;

		// halve table size if average length of list <= 2
		if ( n <= 2*m) resize(m/2);
	} 
	
	private int nextPrime(int input){
		  int counter;
		  input++;   
		  while(true){
		    counter = 0;
		    for(int i = 2; i <= Math.sqrt(input); i ++)
		    {
		      if(input % i == 0)  counter++;
		    }
		    if(counter == 0)
		      return input;
		    else{
		      input++;
		      continue;
		    }
		  }
		}

	// return keys in symbol table as an Iterable
	public Iterable<Key> keys() {
		LinkedListQueue<Key> queue = new LinkedListQueue<Key>();
		for (int i = 0; i < m; i++) {
			if(!st[i].isEmpty()&&st[i].getKey()!=null)
				queue.enqueue(st[i].getKey());
		}
		return queue;
	} 
	
	public int sizeKeys() {
		int size=0;
		for (int i = 0; i < m; i++) {
			if(!st[i].isEmpty()&&st[i].getKey()!=null)
				size++;
		}
		return size;
	} 

}
