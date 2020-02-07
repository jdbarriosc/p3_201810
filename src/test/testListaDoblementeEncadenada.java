package test;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.ListaDoblementeEncadenada;

public class testListaDoblementeEncadenada {

	private ListaDoblementeEncadenada<String> personas;
	
	
	private void setupEscenario(){
		personas = new ListaDoblementeEncadenada<String>();
		
		Comparator comparador=new Comparador();
		personas.addInOrderComparador(comparador, "Daniel");
		personas.addInOrderComparador(comparador,"Andrés");
		personas.addInOrderComparador(comparador,"Santiago");
		personas.addInOrderComparador(comparador,"Beatriz");
		personas.addInOrderComparador(comparador,"Juan");
		
		
		for(int i=0;i<personas.size();i++)
		{
			System.out.println(personas.darNodo(i).darElemento());
		}		
		
		
		
		
		
	} 


	public class Comparador implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}
		
	}

	private void setupEscenario2(){
		personas = new ListaDoblementeEncadenada<String>();
		personas.add("Pedro");
	} 
	@Test
	public void testAgregarObtener(){
		setupEscenario();
		
		assertEquals("el nombre no es el esperado", "Daniel", personas.darNodo(2).darElemento());
		assertEquals("el nombre no es el esperado", "Andrés", personas.darNodo(0).darElemento());
		assertEquals("el nombre no es el esperado", "Santiago", personas.darNodo(4).darElemento());
		assertEquals("el nombre no es el esperado", "Beatriz", personas.darNodo(1).darElemento());	
		assertEquals("el nombre no es el esperado", "Juan", personas.darNodo(3).darElemento());

	}
	
	@Test
	public void testTamanio(){
		setupEscenario();
		assertEquals(5, personas.size());
		personas.remove(0); 
		personas.remove(0);
		
		assertEquals(3, personas.size());
	}
	@Test
	public void testBuscarUltimo(){
		setupEscenario();
		assertEquals("el nombre no es el esperado", "Santiago", personas.darNodo(personas.size()-1).darElemento());
	}
	@Test(expected=IndexOutOfBoundsException.class)
	public void testEliminarPrimero2(){
		setupEscenario2();
		
		personas.remove(0); 
		personas.darNodo(0);
	}
	

}
