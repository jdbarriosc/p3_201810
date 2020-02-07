package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.LinearProbingHashST;
import model.data_structures.ListaDoblementeEncadenada;

public class testHashTable {

	private ArrayList<String>personas;
	private LinearProbingHashST<String, Integer> tabla;


	public void setUpEscenario1() 
	{
		personas=new ArrayList();
		personas.add("Juan");
		personas.add("Pedro");
		personas.add("Santiago");
		personas.add("Camila");
		personas.add("Juan");
		tabla=new LinearProbingHashST<>(personas.size());
		for (int i = 0; i < personas.size(); i++) {
			tabla.put(personas.get(i),i);
		}
	}


	/**
	 * This test looks up all elements of the hash table by searching
	 * for their keys.  When found, the element is confirmed to be non-null
	 * and to have the correct data.
	 */
	@Test
	public void testSearch() 
	{
		setUpEscenario1();

		assertNotNull(tabla.get("Juan"));
		assertTrue(tabla.get("Juan")==4);
		assertNull(tabla.get("Diego"));
	}


	/**
	 * This test looks up all elements of the hash table by searching
	 * for their keys.  When found, each element is removed from the
	 * table, which is confirmed by attempting to remove it again.
	 * At the end, the hash table is confirmed to be empty.
	 */
	@Test
	public void testRemove() 
	{
		setUpEscenario1();
		tabla.delete("Pedro");
		assertNull(tabla.get("Pedro"));

	}

	@Test
	public void testTamaño()
	{
		setUpEscenario1();
		assertTrue(tabla.size()==4);
		tabla.delete("Pedro");
		assertTrue(tabla.size()==3);

	}

	@Test
	public void testResize()
	{
		setUpEscenario1();
		int inicio=personas.size();
		personas.add("manuela");
		personas.add("Carlos");
		personas.add("German");
		personas.add("Jose");
		personas.add("Daniel");
		personas.add("Maria");
		personas.add("Sara");
		personas.add("Esteban");
		personas.add("Mateo");
		personas.add("Juliana");
		personas.add("Sofia");
		for (int i = inicio; i <personas.size() ; i++) 
		{
			tabla.put(personas.get(i),i);
		}
		assertTrue(tabla.size()==15);

	}


}
