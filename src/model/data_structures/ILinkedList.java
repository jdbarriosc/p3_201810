package model.data_structures;

import java.util.Iterator;


/**
 * Abstract Data Type for a linked list of generic objects
 * This ADT should contain the basic operations to manage a list
 * add: add a new element T 
 * delete: delete the given element T 
 * get: get the given element T (null if it doesn't exist in the list)
 * size: return the the number of elements
 * get: get an element T by position (the first position has the value 0) 
 * listing: set the listing of elements at the firt element
 * getCurrent: return the current element T in the listing (return null if it doesn´t exists)
 * next: advance to next element in the listing (return if it exists)
 * @param <T>
 */
public interface ILinkedList <E>  
{
	public void add(E nuevo);
	
	public void addAtStart(E nuevo);

	public E dar(int index);

	public int size( ) ;

	public boolean isEmpty( );

	public E getFirst( );

	public E getLast( );

	public Iterator<E> iterator();
	
	public void addInOrder( E elementoAUbicar) ;
}
