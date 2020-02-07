package test;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.LinkedListStack;

public class testStack {

	LinkedListStack<Integer> stackToTest;

	@Before
	public void beforeTestSetUp() {
		stackToTest = new LinkedListStack<Integer>();
	}

	@Test
	public void testPush() {

		for (int i = 0; i < 10000; i++) {
			Integer temp = (int) Math.random() * 100;
			stackToTest.push(temp);
			assertEquals(temp, stackToTest.peek());
		}
	}

	@Test
	public void testPop() {
		Integer[] testData = new Integer[10000];

		for (int i = 0; i < 10000; i++) {
			int temp = (int) Math.random() * 100;
			testData[i] = temp;
			stackToTest.push(temp);
		}

		for (int i = testData.length - 1; i >= 0; i--) {
			// Make sure the data is equal
			assertEquals(testData[i], stackToTest.pop());
		}

	}

	@Test(expected = NoSuchElementException.class)
	public void testEmptyPop() {
		stackToTest.pop();
	}

	@Test
	public void testPeek() {
		for (int i = 0; i < 10000; i++) {
			Integer temp = (int) Math.random() * 100;
			stackToTest.push(temp);
			assertEquals(temp, stackToTest.peek());
		}
	}

	@Test
	public void testIsEmpty() {
		assertEquals(true, stackToTest.isEmpty());
		stackToTest.push(1);
		assertEquals(false, stackToTest.isEmpty());
		stackToTest.pop();
		assertEquals(true, stackToTest.isEmpty());

	}


}
