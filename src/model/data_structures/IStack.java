package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface IStack<E> {

	/** Push a new element at the top of the stack */
	public void push (E item);

	/** Pop the element at the top of the stack 
	 * @return the top element or null if it doesn't exist
	 * */
	public E pop();

	/** Evaluate if the stack is empty
	 * @return true if the stack is empty. false in other case. 
	 */
	public boolean isEmpty();

	/**
	 * Returns the last element that was added to the stack.
	 *
	 * @return The last element of the stack.
	 * @throws NoSuchElementException
	 *             is thrown when there are no elements to peek for the stack
	 */
	public E peek() throws NoSuchElementException;
	
	public void vaciar();
	
	public int size();
	
	public Iterator<E> iterator();


}
