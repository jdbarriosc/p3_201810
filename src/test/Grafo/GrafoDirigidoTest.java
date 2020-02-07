/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: GrafoDirigidoTest.java,v 1.2 2008/10/09 16:32:35 alf-mora Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo G�mez - Abril 8, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package test.Grafo;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import junit.framework.TestCase;
import model.data_structures.grafo.*;
import model.vo.ServiceVo;

/**
 * Clase utilizada para verificar el funcionamiento de la clase GrafoDirigido.
 * @see GrafoDirigido
 */
public class GrafoDirigidoTest extends TestCase
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------	

	/**
	 * Grafo sobre el que se van a hacer las pruebas.
	 */
	private GrafoDirigido<Integer, VerticeNumerico, ArcoNumerico> grafo;

	Gson gson = new GsonBuilder().create();
	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------    

	/**
	 * Crea un grafo vacio
	 */
	public void setupEscenario1( )
	{
		// Crear al grafo vacio
		grafo = new GrafoDirigido<Integer, VerticeNumerico, ArcoNumerico>( );
	}

	/**
	 * Crea un grafo con 5 vertices y 0 arcos
	 */
	public void setupEscenario2( )
	{
		// Crear el grafo vacio
		grafo = new GrafoDirigido<Integer, VerticeNumerico, ArcoNumerico>( );

		// Crear los vertices
		try
		{
			VerticeNumerico vn = new VerticeNumerico( 0 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 1 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 2 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 3 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 4 );
			grafo.agregarVertice( vn );
		}
		catch( VerticeYaExisteException e )
		{
			// Esto no debería suceder
			fail( );
		}
	}

	/**
	 * Crea un grafo con 5 v�rtices en forma de lista encadenada
	 */
	public void setupEscenario3( )
	{
		// Crear el grafo vacio
		grafo = new GrafoDirigido<Integer, VerticeNumerico, ArcoNumerico>( );

		// Crear los v�rtices
		try
		{
			VerticeNumerico vn = new VerticeNumerico( 0 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 1 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 2 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 3 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 4 );
			grafo.agregarVertice( vn );
		}
		catch( VerticeYaExisteException e )
		{
			// Esto no deber�a suceder
			fail( );
		}

		// Agregar los v�rtices
		try
		{
			ArcoNumerico an = new ArcoNumerico( 1 );
			grafo.agregarArco( 0, 1, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 1, 2, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 2, 3, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 3, 4, an );
		}
		catch( VerticeNoExisteException e )
		{
			// Esto no deber��a
			fail( );
		}
		catch( ArcoYaExisteException e )
		{
			// Esto no deber��a
			fail( );
		}
	}

	/**
	 * Crea un grafo con 10 vertices y 15 arcos
	 */
	public void setupEscenario4( )
	{
		// Crear el grafo vacio
		grafo = new GrafoDirigido<Integer, VerticeNumerico, ArcoNumerico>( );

		// Crear los vertices
		try
		{
			VerticeNumerico vn = new VerticeNumerico( 1 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 2 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 3 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 4 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 5 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 6 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 7 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 8 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 9 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 10 );
			grafo.agregarVertice( vn );
		}
		catch( VerticeYaExisteException e )
		{
			// Esto no debería suceder
			fail( );
		}

		// Agregar los vertices
		try
		{
			ArcoNumerico an = new ArcoNumerico( 7 );
			grafo.agregarArco( 1, 3, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 2, 3, an );
			an = new ArcoNumerico( 9 );
			grafo.agregarArco( 3, 8, an );
			an = new ArcoNumerico( 8 );
			grafo.agregarArco( 3, 7, an );
			an = new ArcoNumerico( 6 );
			grafo.agregarArco( 4, 1, an );
			an = new ArcoNumerico( 6 );
			grafo.agregarArco( 4, 6, an );
			an = new ArcoNumerico( 3 );
			grafo.agregarArco( 5, 4, an );
			an = new ArcoNumerico( 3 );
			grafo.agregarArco( 6, 2, an );
			an = new ArcoNumerico( 5 );
			grafo.agregarArco( 6, 9, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 7, 5, an );
			an = new ArcoNumerico( 3 );
			grafo.agregarArco( 7, 6, an );
			an = new ArcoNumerico( 15 );
			grafo.agregarArco( 8, 4, an );
			an = new ArcoNumerico( 2 );
			grafo.agregarArco( 8, 10, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 9, 8, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 10, 7, an );
		}
		catch( VerticeNoExisteException e )
		{
			// Esto no debería
			fail( );
		}
		catch( ArcoYaExisteException e )
		{
			// Esto no debería
			fail( );
		}
	}

	/**
	 * Crea un grafo con 10 vertices y 20 arcos que tiene un camino hamiltoniano
	 */
	public void setupEscenario5( )
	{
		// Crear el grafo vacio
		grafo = new GrafoDirigido<Integer, VerticeNumerico, ArcoNumerico>( );

		// Crear los vertices
		try
		{
			VerticeNumerico vn = new VerticeNumerico( 1 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 2 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 3 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 4 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 5 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 6 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 7 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 8 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 9 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 10 );
			grafo.agregarVertice( vn );
		}
		catch( VerticeYaExisteException e )
		{
			// Esto no debería suceder
			fail( );
		}

		// Agregar los vertices
		try
		{
			ArcoNumerico an = new ArcoNumerico( 1 );
			grafo.agregarArco( 1, 2, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 1, 3, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 1, 4, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 2, 3, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 2, 6, an );
			an = new ArcoNumerico( 2 );
			grafo.agregarArco( 2, 9, an );
			an = new ArcoNumerico( 3 );
			grafo.agregarArco( 3, 8, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 4, 6, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 5, 1, an );
			an = new ArcoNumerico( 8 );
			grafo.agregarArco( 5, 4, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 5, 7, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 6, 7, an );
			an = new ArcoNumerico( 2 );
			grafo.agregarArco( 7, 3, an );
			an = new ArcoNumerico( 5 );
			grafo.agregarArco( 8, 4, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 9, 6, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 9, 8, an );
			an = new ArcoNumerico( 3 );
			grafo.agregarArco( 9, 10, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 10, 8, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 10, 7, an );
			an = new ArcoNumerico( 5 );
			grafo.agregarArco( 10, 5, an );
		}
		catch( VerticeNoExisteException e )
		{
			// Esto no debería
			fail( );
		}
		catch( ArcoYaExisteException e )
		{
			// Esto no debería
			fail( );
		}
	}

	/**
	 * Crea un grafo igual al escenario 5 y le agrega un arco de peso 1 entre los nodos 8 y 1
	 */
	public void setupEscenario6( )
	{
		// Crear el grafo vacio
		grafo = new GrafoDirigido<Integer, VerticeNumerico, ArcoNumerico>( );

		// Crear los vertices
		try
		{
			VerticeNumerico vn = new VerticeNumerico( 1 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 2 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 3 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 4 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 5 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 6 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 7 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 8 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 9 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 10 );
			grafo.agregarVertice( vn );
		}
		catch( VerticeYaExisteException e )
		{
			// Esto no deber��a suceder
			fail( );
		}

		// Agregar los v�rtices
		try
		{
			ArcoNumerico an = new ArcoNumerico( 1 );
			grafo.agregarArco( 1, 2, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 1, 3, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 1, 4, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 2, 3, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 2, 6, an );
			an = new ArcoNumerico( 2 );
			grafo.agregarArco( 2, 9, an );
			an = new ArcoNumerico( 3 );
			grafo.agregarArco( 3, 8, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 4, 6, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 5, 1, an );
			an = new ArcoNumerico( 8 );
			grafo.agregarArco( 5, 4, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 5, 7, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 6, 7, an );
			an = new ArcoNumerico( 2 );
			grafo.agregarArco( 7, 3, an );
			an = new ArcoNumerico( 5 );
			grafo.agregarArco( 8, 4, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 8, 1, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 9, 6, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 9, 8, an );
			an = new ArcoNumerico( 3 );
			grafo.agregarArco( 9, 10, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 10, 8, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 10, 7, an );
			an = new ArcoNumerico( 5 );
			grafo.agregarArco( 10, 5, an );
		}
		catch( VerticeNoExisteException e )
		{
			// Esto no deber��a
			fail( );
		}
		catch( ArcoYaExisteException e )
		{
			// Esto no deber�a
			fail( );
		}
	}

	/**
	 * Crea un grafo de 10 v�rtices y 17 vertices que tiene un camino hamiltoniano
	 */
	public void setupEscenario7( )
	{
		// Crear el grafo vacio
		grafo = new GrafoDirigido<Integer, VerticeNumerico, ArcoNumerico>( );

		// Crear los v�rtices
		try
		{
			VerticeNumerico vn = new VerticeNumerico( 1 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 2 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 3 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 4 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 5 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 6 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 7 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 8 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 9 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 10 );
			grafo.agregarVertice( vn );
		}
		catch( VerticeYaExisteException e )
		{
			// Esto no debería suceder
			fail( );
		}

		// Agregar los vertices
		try
		{
			ArcoNumerico an = new ArcoNumerico( 1 );
			grafo.agregarArco( 1, 2, an );
			an = new ArcoNumerico( 2 );
			grafo.agregarArco( 2, 9, an );
			an = new ArcoNumerico( 10 );
			grafo.agregarArco( 3, 2, an );
			an = new ArcoNumerico( 3 );
			grafo.agregarArco( 3, 8, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 4, 6, an );
			an = new ArcoNumerico( 8 );
			grafo.agregarArco( 5, 4, an );
			an = new ArcoNumerico( 10 );
			grafo.agregarArco( 6, 2, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 6, 7, an );
			an = new ArcoNumerico( 10 );
			grafo.agregarArco( 6, 9, an );
			an = new ArcoNumerico( 2 );
			grafo.agregarArco( 7, 3, an );
			an = new ArcoNumerico( 10 );
			grafo.agregarArco( 7, 5, an );
			an = new ArcoNumerico( 10 );
			grafo.agregarArco( 7, 10, an );
			an = new ArcoNumerico( 5 );
			grafo.agregarArco( 8, 4, an );
			an = new ArcoNumerico( 10 );
			grafo.agregarArco( 8, 9, an );
			an = new ArcoNumerico( 10 );
			grafo.agregarArco( 8, 10, an );
			an = new ArcoNumerico( 3 );
			grafo.agregarArco( 9, 10, an );
			an = new ArcoNumerico( 5 );
			grafo.agregarArco( 10, 5, an );

		}
		catch( VerticeNoExisteException e )
		{
			// Esto no debería
			fail( );
		}
		catch( ArcoYaExisteException e )
		{
			// Esto no debería
			fail( );
		}
	}

	/**
	 * Crea un grafo de 5 v�rtices y 8 arcos.
	 */
	public void setupEscenario8( )
	{
		// Crear el grafo vacio
		grafo = new GrafoDirigido<Integer, VerticeNumerico, ArcoNumerico>( );

		// Crear los vertices
		try
		{
			VerticeNumerico vn = new VerticeNumerico( 1 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 2 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 3 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 4 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 5 );
			grafo.agregarVertice( vn );
		}
		catch( VerticeYaExisteException e )
		{
			// Esto no debería suceder
			fail( );
		}

		// Agregar los vertices
		try
		{
			ArcoNumerico an = new ArcoNumerico( 1 );
			grafo.agregarArco( 5, 4, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 4, 1, an );
			an = new ArcoNumerico( 2 );
			grafo.agregarArco( 1, 2, an );
			an = new ArcoNumerico( 3 );
			grafo.agregarArco( 2, 3, an );
			an = new ArcoNumerico( 5 );
			grafo.agregarArco( 3, 4, an );
			an = new ArcoNumerico( 8 );
			grafo.agregarArco( 4, 2, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 2, 5, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 5, 3, an );
		}
		catch( VerticeNoExisteException e )
		{
			// Esto no debería
			fail( );
		}
		catch( ArcoYaExisteException e )
		{
			// Esto no debería
			fail( );
		}
	}

	public void setupEscenario9( )
	{
		// Crear el grafo vacio
		grafo = new GrafoDirigido<Integer, VerticeNumerico, ArcoNumerico>( );

		// Crear los vertices
		try
		{
			VerticeNumerico vn = new VerticeNumerico( 1 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 2 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 3 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 4 );
			grafo.agregarVertice( vn );
		}
		catch( VerticeYaExisteException e )
		{
			// Esto no debería suceder
			fail( );
		}

		// Agregar los arcos
		try
		{
			ArcoNumerico an = new ArcoNumerico( 1 );
			grafo.agregarArco( 1, 2, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 2, 1, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 2, 3, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 1, 4, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 1, 3, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 2, 4, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 3, 4, an );
			an = new ArcoNumerico( 1 );
			grafo.agregarArco( 4, 3, an );

		}
		catch( VerticeNoExisteException e )
		{
			// Esto no debería
			fail( );
		}
		catch( ArcoYaExisteException e )
		{
			// Esto no debería
			fail( );
		}
	}

	/**
	 * Crea un grafo con 10 vertices y 7 arcos que tiene un camino hamiltoniano
	 */
	public void setupEscenario10( )
	{
		// Crear el grafo vacio
		grafo = new GrafoDirigido<Integer, VerticeNumerico, ArcoNumerico>( );

		// Crear los vertices
		try
		{
			VerticeNumerico vn = new VerticeNumerico( 1 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 2 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 3 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 4 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 5 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 6 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 7 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 8 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 9 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 10 );
			grafo.agregarVertice( vn );
		}
		catch( VerticeYaExisteException e )
		{
			// Esto no debería suceder
			fail( );
		}

		// Agregar los vertices
		try
		{
			ArcoNumerico an = new ArcoNumerico( 1 );
			grafo.agregarArco( 1, 2, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 2, 3, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 4, 5, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 5, 6, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 7, 8, an );
			an = new ArcoNumerico( 2 );
			grafo.agregarArco( 8, 9, an );
			an = new ArcoNumerico( 3 );
			grafo.agregarArco( 10, 7, an );

		}
		catch( VerticeNoExisteException e )
		{
			// Esto no debería
			fail( );
		}
		catch( ArcoYaExisteException e )
		{
			// Esto no debería
			fail( );
		}
	}
	/**
	 * Crea un grafo con 10 vertices y 7 arcos que tiene un camino hamiltoniano
	 */
	public void setupEscenario11( )
	{
		// Crear el grafo vacio
		grafo = new GrafoDirigido<Integer, VerticeNumerico, ArcoNumerico>( );

		// Crear los vertices
		try
		{
			VerticeNumerico vn = new VerticeNumerico( 1 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 2 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 3 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 4 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 5 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 6 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 7 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 8 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 9 );
			grafo.agregarVertice( vn );
			vn = new VerticeNumerico( 10 );
			grafo.agregarVertice( vn );
		}
		catch( VerticeYaExisteException e )
		{
			// Esto no debería suceder
			fail( );
		}

		// Agregar los vertices
		try
		{
			ArcoNumerico an = new ArcoNumerico( 1 );
			grafo.agregarArco( 1, 4, an );
			an = new ArcoNumerico( 2 );
			grafo.agregarArco( 4, 9, an );
			an = new ArcoNumerico( 3 );
			grafo.agregarArco( 4, 5, an );
			an = new ArcoNumerico( 4 );
			grafo.agregarArco( 5, 1, an );
			an = new ArcoNumerico( 5 );
			grafo.agregarArco( 5, 6, an );
			an = new ArcoNumerico( 6 );
			grafo.agregarArco( 6, 7, an );////////////
			an = new ArcoNumerico( 7 );
			grafo.agregarArco( 7, 5, an );
			an = new ArcoNumerico( 8 );
			grafo.agregarArco( 7, 9, an );
			an = new ArcoNumerico( 9 );
			grafo.agregarArco( 6, 4, an );
			an = new ArcoNumerico( 10 );
			grafo.agregarArco( 2, 8, an );
			an = new ArcoNumerico( 11 );
			grafo.agregarArco( 3, 8, an );
			an = new ArcoNumerico( 12 );
			grafo.agregarArco( 8, 3, an );
			an = new ArcoNumerico( 13 );
			grafo.agregarArco( 8, 2, an );


		}
		catch( VerticeNoExisteException e )
		{
			// Esto no debería
			fail( );
		}
		catch( ArcoYaExisteException e )
		{
			// Esto no debería
			fail( );
		}
	}

	/**
	 * Pruebas de casos normales para el método <code>agregarVertice</code>
	 */
	public void testAgregarVertice( )
	{
		// Crear el grafo vacio
		setupEscenario1( );

		// Casos de agregar en un grafo vacio
		VerticeNumerico vn = new VerticeNumerico( 1 );
		try
		{
			grafo.agregarVertice( vn );
			assertEquals( "El grafo deber�a tener 1 vertice", 1, grafo.darObjetosVertices( ).size( ) );
			assertEquals( "El vertice en el grafo no corresponde al agregado", vn.darId( ), grafo.darVertice( 1 ).darId( ) );
			assertEquals( "El vertice en el grafo no corresponde al agregado", vn, grafo.darVertice( 1 ) );
		}
		catch( VerticeYaExisteException e )
		{
			fail( "El vertice agregado no existe" );
			e.printStackTrace( );
		}
		catch( VerticeNoExisteException e )
		{
			fail( "El vertice buscado si existe" );
			e.printStackTrace( );
		}

		// Casos de agregar en un grafo no vacio
		VerticeNumerico vn2 = new VerticeNumerico( 2 );
		try
		{
			grafo.agregarVertice( vn2 );
			assertEquals( "El grafo debería tener 2 vertice", 2, grafo.darObjetosVertices( ).size( ) );
			assertEquals( "No se encuentran vertices agregados anteriormente", vn.darId( ), grafo.darVertice( 1 ).darId( ) );
			assertEquals( "No se encuentran vertices agregados anteriormente", vn, grafo.darVertice( 1 ) );
			assertEquals( "El vertice en el grafo no corresponde al agregado", vn2.darId( ), grafo.darVertice( 2 ).darId( ) );
			assertEquals( "El vertice en el grafo no corresponde al agregado", vn2, grafo.darVertice( 2 ) );
		}
		catch( VerticeYaExisteException e )
		{
			fail( "El vertice agregado no existe" );
			e.printStackTrace( );
		}
		catch( VerticeNoExisteException e )
		{
			fail( "El vertice buscado si existe" );
			e.printStackTrace( );
		}
	}

	/**
	 * Pruebas de casos erroneos para el método <code>agregarVertice</code>
	 */
	public void testAgregarVerticeError( )
	{
		setupEscenario2( );

		// Ingresar un vertice repetido
		try
		{
			VerticeNumerico vn = new VerticeNumerico( 0 );
			grafo.agregarVertice( vn );
			fail( "El vertice ingresado ya existe" );
		}
		catch( VerticeYaExisteException e )
		{
			// Este es el comportamiento esperado
		}
	}

	/**
	 * Pruebas de casos normales para el método <code>agregarArco</code>
	 */
	public void testAgregarArco( )
	{
		// Crear un grafo sin arcos
		setupEscenario2( );

		try
		{
			ArcoNumerico an = new ArcoNumerico( 5 );
			grafo.agregarArco( 0, 1, an );
			assertNotNull( "El arco no quedo bien agregado", grafo.darArco( 0, 1 ) );
			assertEquals( "Deberia haber solo un arco en el grafo", 1, grafo.darNArcos( ) );
			assertEquals( "El arco agregado no corresponde con el arco devuelto por el grafo", an, grafo.darArco( 0, 1 ) );

			an = new ArcoNumerico( 10 );
			grafo.agregarArco( 1, 2, an );
			assertNotNull( "El arco no quedo bien agregado", grafo.darArco( 1, 2 ) );
			assertEquals( "Deberia haber solo un arco en el grafo", 2, grafo.darNArcos( ) );
			assertEquals( "El arco agregado no corresponde con el arco devuelto por el grafo", an, grafo.darArco( 1, 2 ) );

			an = new ArcoNumerico( 5 );
			grafo.agregarArco( 2, 3, an );
			assertNotNull( "El arco no quedo bien agregado", grafo.darArco( 2, 3 ) );
			assertEquals( "Deberia haber solo un arco en el grafo", 3, grafo.darNArcos( ) );
			assertEquals( "El arco agregado no corresponde con el arco devuelto por el grafo", an, grafo.darArco( 2, 3 ) );

			an = new ArcoNumerico( 10 );
			grafo.agregarArco( 3, 4, an );
			assertNotNull( "El arco no quedo bien agregado", grafo.darArco( 3, 4 ) );
			assertEquals( "Deberia haber solo un arco en el grafo", 4, grafo.darNArcos( ) );
			assertEquals( "El arco agregado no corresponde con el arco devuelto por el grafo", an, grafo.darArco( 3, 4 ) );
		}
		catch( VerticeNoExisteException e )
		{
			// Esto no debería suceder;
			fail( );
		}
		catch( ArcoYaExisteException e )
		{
			fail( "El arco ingresado no existe" );
			e.printStackTrace( );
		}
		catch( ArcoNoExisteException e )
		{
			fail( e.getMessage( ) );
			e.printStackTrace( );
		}

	}

	/**
	 * Pruebas de casos erroneos para el método <code>agregarArco</code>
	 */
	public void testAgregarArcoError( )
	{
		setupEscenario2( );

		// Agregar un arco a un nodo no existente
		ArcoNumerico an = new ArcoNumerico( 1 );
		try
		{
			grafo.agregarArco( 0, 5, an );
			fail( "El destino del arco no existe" );
		}
		catch( VerticeNoExisteException e )
		{
			// Este es el comportamiento esperado
		}
		catch( ArcoYaExisteException e )
		{
			fail( "El arco agregado no existe" );
			e.printStackTrace( );
		}

		// Agregar un arco a un nodo no existente
		an = new ArcoNumerico( 1 );
		try
		{
			grafo.agregarArco( 5, 0, an );
		}
		catch( VerticeNoExisteException e )
		{
			// Este es el comportamiento esperado
		}
		catch( ArcoYaExisteException e )
		{
			fail( "El arco agregado no existe" );
			e.printStackTrace( );
		}

		// Agregar un arco repetido
		an = new ArcoNumerico( 1 );
		ArcoNumerico an2 = new ArcoNumerico( 2 );
		try
		{
			grafo.agregarArco( 0, 1, an );
			grafo.agregarArco( 0, 1, an2 );
			fail( "El arco agregado está repetido" );
		}
		catch( VerticeNoExisteException e )
		{
			fail( "Los vertices si existe" );
			e.printStackTrace( );
		}
		catch( ArcoYaExisteException e )
		{
			// Este es el comportamiento esperado
		}

	}

	/**
	 * Pruebas de casos normales para el método <code>hayCamino</code>
	 */
	public void testHayCamino( )
	{
		setupEscenario3( );

		try
		{
			assertTrue( "Algoritmo no valido para encontrar camino", grafo.hayCamino( 0, 1 ) );
			assertTrue( "Algoritmo no valido para encontrar camino", grafo.hayCamino( 0, 2 ) );
			assertTrue( "Algoritmo no valido para encontrar camino", grafo.hayCamino( 0, 3 ) );
			assertTrue( "Algoritmo no valido para encontrar camino", grafo.hayCamino( 0, 4 ) );
			assertFalse( "Algoritmo no valido para encontrar camino", grafo.hayCamino( 1, 0 ) );
			assertFalse( "Algoritmo no valido para encontrar camino", grafo.hayCamino( 4, 0 ) );
		}
		catch( VerticeNoExisteException e )
		{
			// esto no debería suceder
			fail( );
		}
	}

	/**
	 * Pruebas de casos normales para el método <code>darCaminoMasCorto</code>
	 */
	public void testDarCaminoMasCorto( )
	{
		setupEscenario4( );

		try
		{
			Camino<Integer, VerticeNumerico, ArcoNumerico> c = grafo.darCaminoMasCorto( 9, 1 );
			assertEquals( "Algoritmo no valido para calcular el camino m�s corto", 3, c.darLongitud( ) );
			assertEquals( "Algoritmo no valido para calcular el camino m�s corto", new Integer( 9 ), c.darOrigen( ).darId( ) );
			assertEquals( "Algoritmo no valido para calcular el camino m�s corto", 22, c.darCosto( ) );
			Iterator<VerticeNumerico> iter = c.darSecuenciaVertices( );
			assertEquals( "Camino mal formado", new Integer( 9 ), iter.next( ).darId( ) );
			assertEquals( "Camino mal formado", new Integer( 8 ),iter.next( ).darId( ) );
			assertEquals( "Camino mal formado", new Integer( 4 ), iter.next( ).darId( ) );
			assertEquals( "Camino mal formado", new Integer( 1 ), iter.next( ).darId( ));
		}
		catch( VerticeNoExisteException e )
		{
			// Esto no debería suceder
			fail( );
		}
	}

	/**
	 * Pruebas de casos normales para el método <code>darCaminoMasBarato</code>
	 */
	public void testDarCaminoMasBarato( )
	{
		setupEscenario4( );

		try
		{
			Camino<Integer, VerticeNumerico, ArcoNumerico> c = grafo.darCaminoMasBarato( 9, 1 );
			assertEquals( "Algoritmo no valido para calcular el camino más barato", 6, c.darLongitud( ) );
			assertEquals( "Algoritmo no valido para calcular el camino más corto", new Integer( 9 ), c.darOrigen( ).darId( ) );
			assertEquals( "Algoritmo no valido para calcular el camino más corto", 17, c.darCosto( ) );
			Iterator<VerticeNumerico> iter = (Iterator<VerticeNumerico>) c.darSecuenciaVertices( );
			assertEquals( "Camino mal formado", new Integer( 9 ), iter.next( ).darId( ) );
			assertEquals( "Camino mal formado", new Integer( 8 ), iter.next( ).darId( ) );
			assertEquals( "Camino mal formado", new Integer( 10 ), iter.next( ).darId( ) );
			assertEquals( "Camino mal formado", new Integer( 7 ), iter.next( ).darId( ) );
			assertEquals( "Camino mal formado", new Integer( 5 ), iter.next( ).darId( ) );
			assertEquals( "Camino mal formado", new Integer( 4 ), iter.next( ).darId( ) );
			assertEquals( "Camino mal formado", new Integer( 1 ),iter.next( ).darId( ) );
		}
		catch( VerticeNoExisteException e )
		{
			// Esto no debería suceder
			fail( );
		}
	}

	/**
	 * Pruebas de casos normales para el método <code>hayCiclo</code>
	 */
	public void testHayCiclo( )
	{
		setupEscenario3( );
		try
		{
			assertFalse( "En este grafo no hay ciclos", grafo.hayCiclo( 0 ) );
		}
		catch( VerticeNoExisteException e )
		{
			// Esto no debería suceder
			fail( );
		}

		setupEscenario4( );
		try
		{
			assertTrue( "En este vertice si hay ciclo", grafo.hayCiclo( 4 ) );
			assertTrue( "En este vertice si hay ciclo", grafo.hayCiclo( 6 ) );
			assertTrue( "En este vertice si hay ciclo", grafo.hayCiclo( 2 ) );
			assertTrue( "En este vertice si hay ciclo", grafo.hayCiclo( 8 ) );
		}
		catch( VerticeNoExisteException e )
		{
			// Esto no debería suceder
			fail( );
		}
	}

	/**
	 * Pruebas para el método <code>hayCaminoHamiltoniano</code>
	 */
	public void testHayCaminoHamiltoniano( )
	{
		setupEscenario4( );
		assertFalse( "En este grafo no hay camino hamiltoniano", grafo.hayCaminoHamilton( ) );
		setupEscenario2( );
		assertFalse( "En este grafo no hay camino hamiltoniano", grafo.hayCaminoHamilton( ) );

		setupEscenario3( );
		assertTrue( "En este grafo si hay camino hamiltoniano", grafo.hayCaminoHamilton( ) );
		setupEscenario5( );
		assertTrue( "En este grafo si hay camino hamiltoniano", grafo.hayCaminoHamilton( ) );
		setupEscenario7( );
		assertTrue( "En este grafo si hay camino hamiltoniano", grafo.hayCaminoHamilton( ) );
	}

	/**
	 * Pruebas para el método <code>darCaminoHamiltoniano</code>s
	 */
	public void testDarCaminoHamiltoniano( )
	{
		setupEscenario4( );
		assertNull( "En este grafo no hay camino hamiltoniano", grafo.darCaminoHamilton( ) );
		setupEscenario2( );
		assertNull( "En este grafo no hay camino hamiltoniano", grafo.darCaminoHamilton( ) );

		setupEscenario3( );
		assertNotNull( "En este grafo si hay camino hamiltoniano", grafo.darCaminoHamilton( ) );
		setupEscenario5( );
		assertNotNull( "En este grafo si hay camino hamiltoniano", grafo.darCaminoHamilton( ) );

		setupEscenario7( );
		Camino<Integer, VerticeNumerico, ArcoNumerico> ch = grafo.darCaminoHamilton( );
		assertEquals( "El camino está mal formado", new Integer( 1 ), ch.darOrigen( ).darId( ) );
		assertEquals( "El camino está mal formado", 9, ch.darLongitud( ) );
		assertEquals( "El camino está mal formado", 26, ch.darCosto( ) );
		Iterator<VerticeNumerico> iter = (Iterator<VerticeNumerico>) ch.darSecuenciaVertices( );
		assertEquals( "Camino mal formado", new Integer( 1 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 2 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 9 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 10 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 5 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 4 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 6 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 7 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 3 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 8 ), iter.next( ).darId( ) );
	}

	/**
	 * Pruebas para el método <code>hayCicloHamilton</code>
	 */
	public void testHayCicloHamiltoniano( )
	{
		setupEscenario4( );
		assertFalse( "En este grafo no hay ciclo hamiltoniano", grafo.hayCicloHamilton( ) );
		setupEscenario2( );
		assertFalse( "En este grafo no hay ciclo hamiltoniano", grafo.hayCicloHamilton( ) );
		setupEscenario3( );
		assertFalse( "En este grafo no hay ciclo hamiltoniano", grafo.hayCicloHamilton( ) );
		setupEscenario5( );
		assertFalse( "En este grafo no hay ciclo hamiltoniano", grafo.hayCicloHamilton( ) );
		setupEscenario7( );
		assertFalse( "En este grafo no hay ciclo hamiltoniano", grafo.hayCicloHamilton( ) );

		setupEscenario6( );
		assertTrue( "En este grafo si hay ciclo hamiltoniano", grafo.hayCicloHamilton( ) );
		setupEscenario8( );
		assertTrue( "En este grafo si hay ciclo hamiltoniano", grafo.hayCicloHamilton( ) );
	}

	/**
	 * Pruebas para el método <code>darCicloHamilton</code>
	 */
	public void testDarCicloHamiltoniano( )
	{
		setupEscenario4( );
		assertNull( "En este grafo no hay ciclo hamiltoniano", grafo.darCicloHamilton( ) );
		setupEscenario2( );
		assertNull( "En este grafo no hay ciclo hamiltoniano", grafo.darCicloHamilton( ) );
		setupEscenario3( );
		assertNull( "En este grafo no hay ciclo hamiltoniano", grafo.darCicloHamilton( ) );
		setupEscenario5( );
		assertNull( "En este grafo no hay ciclo hamiltoniano", grafo.darCicloHamilton( ) );
		setupEscenario7( );
		assertNull( "En este grafo no hay ciclo hamiltoniano", grafo.darCicloHamilton( ) );

		setupEscenario6( );
		Camino<Integer, VerticeNumerico, ArcoNumerico> ch = grafo.darCicloHamilton( );
		assertNotNull( "En este grafo si hay ciclo hamiltoniano", ch );
		assertEquals( "El ciclo está mal formado", 10, ch.darLongitud( ) );
		assertEquals( "El ciclo está mal formado", 27, ch.darCosto( ) );

	}

	/**
	 * Pruebas para el método <code>hayCaminoEuler</code>
	 */
	public void testHayCaminoEuler( )
	{
		setupEscenario2( );
		assertTrue( "En este grafo si hay camino de Euler", grafo.hayCaminoEuler( ) );
		setupEscenario3( );
		assertTrue( "En este grafo si hay camino de Euler", grafo.hayCaminoEuler( ) );
		setupEscenario4( );
		assertTrue( "En este grafo si hay camino de Euler", grafo.hayCaminoEuler( ) );
		setupEscenario8( );
		assertTrue( "En este grafo si hay camino de Euler", grafo.hayCaminoEuler( ) );

		setupEscenario5( );
		assertFalse( "En este grafo no hay camino de Euler", grafo.hayCaminoEuler( ) );
		setupEscenario6( );
		assertFalse( "En este grafo no hay camino de Euler", grafo.hayCaminoEuler( ) );
		setupEscenario7( );
		assertFalse( "En este grafo no hay camino de Euler", grafo.hayCaminoEuler( ) );
	}

	/**
	 * Pruebas para el método <code>darCaminoEuler</code>
	 */
	public void testDarCaminoEuler( )
	{
		setupEscenario2( );
		assertNotNull( "En este grafo si hay camino de Euler", grafo.darCaminoEuler( ) );
		setupEscenario3( );
		assertNotNull( "En este grafo si hay camino de Euler", grafo.darCaminoEuler( ) );
		setupEscenario4( );
		assertNotNull( "En este grafo si hay camino de Euler", grafo.darCaminoEuler( ) );
		setupEscenario8( );
		assertNotNull( "En este grafo si hay camino de Euler", grafo.darCaminoEuler( ) );

		setupEscenario5( );
		assertNull( "En este grafo no hay camino de Euler", grafo.darCaminoEuler( ) );
		setupEscenario6( );
		assertNull( "En este grafo no hay camino de Euler", grafo.darCaminoEuler( ) );
		setupEscenario7( );
		assertNull( "En este grafo no hay camino de Euler", grafo.darCaminoEuler( ) );

		setupEscenario3( );
		Camino<Integer, VerticeNumerico, ArcoNumerico> ce = grafo.darCaminoEuler( );
		assertEquals( "El camino está mal formado", new Integer( 0 ), ce.darOrigen( ).darId( ) );
		assertEquals( "El camino está mal formado", 4, ce.darLongitud( ) );
		assertEquals( "El camino está mal formado", 4, ce.darCosto( ) );
		Iterator<VerticeNumerico> iter = ce.darSecuenciaVertices( );
		assertEquals( "Camino mal formado", new Integer( 0 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 1 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 2 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 3 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 4 ), iter.next( ).darId( ) );

		setupEscenario4( );
		ce = grafo.darCaminoEuler( );
		assertEquals( "El camino está mal formado", new Integer( 1 ), ce.darOrigen( ).darId( ) );
		assertEquals( "El camino está mal formado", 15, ce.darLongitud( ) );
		assertEquals( "El camino está mal formado", 77, ce.darCosto( ) );
		iter = ce.darSecuenciaVertices( );
		assertEquals( "Camino mal formado", new Integer( 1 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 3 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 8 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 4 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 6 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 2 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 3 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 7 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 6 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 9 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 8 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 10 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 7 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 5 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 4 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 1 ), iter.next( ).darId( ) );

		setupEscenario8( );
		ce = grafo.darCaminoEuler( );
		assertEquals( "El camino está mal formado", new Integer( 5 ), ce.darOrigen( ).darId( ) );
		assertEquals( "El camino está mal formado", 8, ce.darLongitud( ) );
		assertEquals( "El camino está mal formado", 22, ce.darCosto( ) );
		iter = ce.darSecuenciaVertices( );
		assertEquals( "Camino mal formado", new Integer( 5 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 4 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 1 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 2 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 3 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 4 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 2 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 5 ), iter.next( ).darId( ) );
	}

	/**
	 * Pruebas para el método <code>hayCicloEuler</code>
	 */
	public void testHayCicloEuler( )
	{
		setupEscenario2( );
		assertFalse( "En este grafo no hay ciclo de Euler", grafo.hayCicloEuler( ) );
		setupEscenario3( );
		assertFalse( "En este grafo no hay ciclo de Euler", grafo.hayCicloEuler( ) );
		setupEscenario8( );
		assertFalse( "En este grafo no hay ciclo de Euler", grafo.hayCicloEuler( ) );
		setupEscenario5( );
		assertFalse( "En este grafo no hay ciclo de Euler", grafo.hayCicloEuler( ) );
		setupEscenario6( );
		assertFalse( "En este grafo no hay ciclo de Euler", grafo.hayCicloEuler( ) );
		setupEscenario7( );
		assertFalse( "En este grafo no hay ciclo de Euler", grafo.hayCicloEuler( ) );

		setupEscenario4( );
		assertTrue( "En este grafo si hay ciclo de Euler", grafo.hayCicloEuler( ) );
	}

	/**
	 * Pruebas para el m�todo <code>darCicloEuler</code>
	 */
	public void testDarCicloEuler( )
	{
		setupEscenario2( );
		assertNull( "En este grafo no hay ciclo de Euler", grafo.darCicloEuler( ) );
		setupEscenario3( );
		assertNull( "En este grafo no hay ciclo de Euler", grafo.darCicloEuler( ) );
		setupEscenario8( );
		assertNull( "En este grafo no hay ciclo de Euler", grafo.darCicloEuler( ) );
		setupEscenario5( );
		assertNull( "En este grafo no hay ciclo de Euler", grafo.darCicloEuler( ) );
		setupEscenario6( );
		assertNull( "En este grafo no hay ciclo de Euler", grafo.darCicloEuler( ) );
		setupEscenario7( );
		assertNull( "En este grafo no hay ciclo de Euler", grafo.darCicloEuler( ) );

		setupEscenario4( );
		Camino<Integer, VerticeNumerico, ArcoNumerico> ce = grafo.darCaminoEuler( );
		assertNotNull( "En este grafo si hay ciclo de Euler", ce );

		// assertEquals( "El camino está mal formado", new Integer( 2 ), ce.darOrigen( ).darId( ) );
		assertEquals( "El camino está mal formado", 15, ce.darLongitud( ) );
		assertEquals( "El camino está mal formado", 77, ce.darCosto( ) );
		Iterator<VerticeNumerico> iter = ce.darSecuenciaVertices( );
		assertEquals( "Camino mal formado", new Integer( 1 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 3 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 8 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 4 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 6 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 2 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 3 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 7 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 6 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 9 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 8 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 10 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 7 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 5 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 4 ), iter.next( ).darId( ) );
		assertEquals( "Camino mal formado", new Integer( 1 ), iter.next( ).darId( ) );
	}

	/**
	 * Pruebas para el método <code>darPeso</code>
	 */
	public void testDarPeso( )
	{
		setupEscenario1( );
		assertEquals( "Algoritmo no valido para calcular el peso del grafo", 0, grafo.darPeso( ) );
		setupEscenario2( );
		assertEquals( "Algoritmo no valido para calcular el peso del grafo", 0, grafo.darPeso( ) );
		setupEscenario3( );
		assertEquals( "Algoritmo no valido para calcular el peso del grafo", 4, grafo.darPeso( ) );
		setupEscenario4( );
		assertEquals( "Algoritmo no valido para calcular el peso del grafo", 77, grafo.darPeso( ) );
		setupEscenario5( );
		assertEquals( "Algoritmo no valido para calcular el peso del grafo", 68, grafo.darPeso( ) );
		setupEscenario6( );
		assertEquals( "Algoritmo no valido para calcular el peso del grafo", 69, grafo.darPeso( ) );
		setupEscenario7( );
		assertEquals( "Algoritmo no valido para calcular el peso del grafo", 101, grafo.darPeso( ) );
		setupEscenario8( );
		assertEquals( "Algoritmo no valido para calcular el peso del grafo", 22, grafo.darPeso( ) );

	}

	/**
	 * Pruebas para el método <code>esCompleto</code>
	 */
	public void testEsCompleto( )
	{
		setupEscenario2( );
		assertFalse( "Este grafo no es completo", grafo.esCompleto( ) );
		setupEscenario3( );
		assertFalse( "Este grafo no es completo", grafo.esCompleto( ) );
		setupEscenario4( );
		assertFalse( "Este grafo no es completo", grafo.esCompleto( ) );
		setupEscenario5( );
		assertFalse( "Este grafo no es completo", grafo.esCompleto( ) );
		setupEscenario6( );
		assertFalse( "Este grafo no es completo", grafo.esCompleto( ) );
		setupEscenario7( );
		assertFalse( "Este grafo no es completo", grafo.esCompleto( ) );
		setupEscenario8( );
		assertFalse( "Este grafo no es completo", grafo.esCompleto( ) );

		setupEscenario1( );
		assertTrue( "Este grafo es completo", grafo.esCompleto( ) );
		setupEscenario9( );
		assertTrue( "Este grafo es completo", grafo.esCompleto( ) );
	}


	public void testContarConexas()
	{
		setupEscenario2();
		assertEquals(5, grafo.contarComponentesConexos());
		setupEscenario1();
		assertEquals(0, grafo.contarComponentesConexos());
		setupEscenario3();
		assertEquals(1,grafo.contarComponentesConexos());
		setupEscenario4();
		assertEquals(1,grafo.contarComponentesConexos());
		setupEscenario5();
		assertEquals(1,grafo.contarComponentesConexos());
		setupEscenario10();
		assertEquals(3,grafo.contarComponentesConexos());
		setupEscenario10();
		assertEquals(3,grafo.contarComponentesConexos());
		setupEscenario11();
		assertEquals(3,grafo.contarComponentesConexos());

	}

	public void testDarComponentesConexos()
	{
		setupEscenario1();
		assertEquals(0, grafo.darComponentesConexos().size());
		setupEscenario2();
		assertEquals(5, grafo.darComponentesConexos().size());
		assertEquals(1.0, ((VerticeNumerico) grafo.darComponentesConexos().get(0).darGrafo().darVertices().get(0)).darDensidad());
		setupEscenario3();
		assertEquals(1,grafo.darComponentesConexos().size());
		setupEscenario4();
		assertEquals(1,grafo.darComponentesConexos().size());
		setupEscenario5();
		assertEquals(1,grafo.darComponentesConexos().size());
		setupEscenario10();
		assertEquals(3,grafo.darComponentesConexos().size());
		for(ComponenteConexo<Integer> actual:grafo.darComponentesConexos())
		{
			
			for(IVertice<Integer>vert:actual.darGrafo().darVertices() )
			{
				System.out.println(vert.darId());
			}
			System.out.println("------------------------------------------------------------");
		}
		setupEscenario11();
		assertEquals(3,grafo.darComponentesConexos().size());
		for(ComponenteConexo<Integer> actual:grafo.darComponentesConexos())
		{
			
			for(IVertice<Integer>vert:actual.darGrafo().darVertices() )
			{
				System.out.println(vert.darId());
			}
			System.out.println("------------------------------------------------------------");
		}

	}
}
