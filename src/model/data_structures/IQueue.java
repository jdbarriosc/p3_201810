package model.data_structures;

import java.util.NoSuchElementException;

public interface IQueue<E> {

	/** Enqueue a new element at the end of the queue */
	public void enqueue(E item);

	/** Dequeue the "first" element in the queue
	 * @return "first" element or null if it doesn't exist
	 */
	public E dequeue() throws NoSuchElementException;

	/** Evaluate if the queue is empty. 
	 * @return true if the queue is empty. false in other case.
	 */
	public boolean isEmpty();

	public int size();


	/**
	 * Returns the first element in the queue. Throws an exception if there are no elements in the queue.
	 * 
	 * @return The first element in the queue
	 * @throws NoSuchElementException
	 *             When there are no elements in the queue
	 */
	public E element() throws NoSuchElementException;



	/**
	 * @return the first element in the queue. Returns null if the queue is empty.
	 */
	public E peek();

}
