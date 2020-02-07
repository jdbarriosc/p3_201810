package test;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.LinkedListQueue;

public class testQueue {

	LinkedListQueue<String> queueTest;

	@Before
	public void setUp() throws Exception {
		queueTest = new LinkedListQueue<String>();
		// queueTest = new GenericArrayQueue<>();
	}

	@Test
	public void testAdd() {
		queueTest.enqueue("5");
		assertEquals("5", queueTest.element());
		queueTest.enqueue("4");
		assertEquals("5", queueTest.element());
		queueTest.enqueue("3");
		assertEquals("5", queueTest.element());
		queueTest.enqueue("2");
		assertEquals("5", queueTest.element());
		queueTest.enqueue("1");
		assertEquals("5", queueTest.element());

		assertEquals("5", queueTest.dequeue());
		assertEquals("4", queueTest.dequeue());
		assertEquals("3", queueTest.dequeue());
		assertEquals("2", queueTest.dequeue());
		assertEquals("1", queueTest.dequeue());
	}

	@Test
	public void testElement() {
		queueTest.enqueue("5");
		assertEquals("5", queueTest.element());
		queueTest.enqueue("4");
		assertEquals("5", queueTest.element());
		queueTest.enqueue("3");
		assertEquals("5", queueTest.element());
		queueTest.enqueue("2");
		assertEquals("5", queueTest.element());
		queueTest.enqueue("1");
		assertEquals("5", queueTest.element());
	}

	@Test(expected = NoSuchElementException.class)
	public void testElementError() {
		queueTest.element();
	}



	@Test(expected = NoSuchElementException.class)
	public void testRemoveError() {
		queueTest.dequeue();
	}

	@Test
	public void testPeek() {
		assertEquals(null, queueTest.peek());
		queueTest.enqueue("5");
		assertNotEquals(null, queueTest.peek());
		queueTest.dequeue();
		assertEquals(null, queueTest.peek());
	}


}
