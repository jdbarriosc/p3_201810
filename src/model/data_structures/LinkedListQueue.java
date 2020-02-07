/**
 * En base a Metodos obtenidos de:https://codereview.stackexchange.com/questions/139975/generic-queue-array-and-linked-list-implementation
 */

package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedListQueue <E> implements IQueue<E>, Iterable<E> {
	private Node<E> first, last;
	private int size=0;

	private static class Node<E> {
		private E data;

		private Node<E> back;

		public Node(E element) {
			data = element;
		}
	}


	public void enqueue(E element) {
		Node<E> newElement = new Node<E>(element);
		if (first == null) {
			first = newElement;
			size++;
		} else {
			if (first.back == null) {
				first.back = newElement;
				size++;
			} else {
				last.back = newElement;
				size++;
			}

			last = newElement;
		}

	}


	public E element() throws NoSuchElementException {
		if (first == null) {
			throw new NoSuchElementException("Queue does not contain any items.");
		}

		return first.data;
	}


	public E dequeue() throws NoSuchElementException {
		if (first == null) {
			throw new NoSuchElementException("Queue does not contain any items.");
		}

		E output = first.data;
		first = first.back;
		return output;
	}



	@Override
	public E peek() {
		return first == null ? null : first.data;
	}




	@Override
	public boolean isEmpty() {
		if (first == null)
		{
			return true;
		}
		return false;
	}


	public Iterator<E> iterator() {
		return new ListIterator<E>(first);
	}


	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<E> implements Iterator<E> {
		private Node<E> current;

		public ListIterator(Node<E> first) {
			current = first;
		}

		@Override
		public boolean hasNext() 
		{ 
			return current != null;                     
		}

		public void remove()      
		{
			throw new UnsupportedOperationException();  
		}

		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			E item = current.data;
			current = current.back; 
			return item;
		}
	}


	
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}


}
