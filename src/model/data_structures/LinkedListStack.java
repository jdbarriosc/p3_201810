package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListStack<E> implements IStack<E>, Iterable<E> {
	private Node<E> top = null;
	private int size=0;

	/**
	 * Helper Class for GenericLinkedStack.
	 */
	private static class Node<E> {
		private E data;
		private Node<E> next = null;

		Node(E element) {
			data = element;
		}
	}

	@Override
	public void push(E element) {
		Node<E> newItem = new Node<E>(element);

		if (top == null) {
			top = newItem;
			size++;
		} else {
			// New Top
			newItem.next = top;
			top = newItem;
			size++;
		}

	}

	@Override
	public E pop() {
		if (top == null) {
			throw new NoSuchElementException("The stack is empty.");
		}

		E output = top.data;
		top = top.next;
		size--;

		return output;
	}



	@Override
	public E peek() {
		if (top == null) {
			throw new NoSuchElementException("The stack is empty.");
		}
		return top.data;
	}

	@Override
	public boolean isEmpty() 
	{
		return top == null;
	}
	@Override
	public void vaciar() {

		top.next=null;
		top=null;


	}

	public int size()
	{
		return size;
	}
	
	public Iterator<E> iterator() {
		return new ListIterator<E>(top);
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<E> implements Iterator<E> {
		private Node<E> current;

		public ListIterator(Node<E> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			E item = current.data;
			current = current.next; 
			return item;
		}
	}




}
