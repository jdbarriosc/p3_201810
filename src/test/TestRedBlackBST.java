package test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Iterator;

import org.junit.Test;

import model.data_structures.RedBlackBST;

public class TestRedBlackBST {


	public void testEmpty() {
		RedBlackBST<Integer, String> st = new RedBlackBST<Integer, String>();
		assertEquals(0, st.size());
		assertEquals(true, st.isEmpty());
		st.delete(3);
		assertEquals(null, st.get(3));
		assertEquals(false, st.contains(3));
	}
	@Test
	public void testListOperations() {
		RedBlackBST<Integer, String> st = new RedBlackBST<Integer, String>();

		try {
			st.put(null, "1");
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
		st.put(20, "life");
		st.put(3, "good");
		st.put(4, "best");
		st.put(6, "java");
		st.put(7, "c++");
		st.put(30, "javascript");
		st.put(55, "datastructure");
		st.put(18, "bst");


		assertEquals(false, st.isEmpty());
		assertEquals(8, st.size());
		assertEquals("good", st.get(3));
		assertEquals(true, st.contains(3));
		assertEquals(false, st.contains(980));
		st.delete(4536);
		st.delete(30);
		assertEquals(7, st.size());

		assertEquals(true, st.contains(3));
		st.delete(20);
		assertEquals(6, st.size());
		st.delete(3);
		assertEquals(5, st.size());
		assertEquals(true, st.contains(4));
		st.delete(4);
		st.delete(6);
		st.delete(7);
		st.delete(55);
		st.delete(18);
		assertEquals(true, st.isEmpty());

		st.put(3, "good");
		st.put(4, "best");
		assertEquals(2, st.size());


		st = new RedBlackBST<Integer, String>();
		st.put(20, "life");
		st.put(3, "good");
		st.put(4, "best");
		st.put(6, "java");
		st.put(7, "c++");
		st.put(30, "javascript");
		st.put(55, "datastructure");
		st.put(18, "bst");

		Integer[] expected = {3, 4, 6, 7, 18, 20, 30, 55};
		Integer[] result = new Integer[8];
		int i=0;
		for(Integer key : st.keys())
		{
			result[i] = key;
			assertEquals(expected[i], result[i]);
			i++;
		}


	}

}
