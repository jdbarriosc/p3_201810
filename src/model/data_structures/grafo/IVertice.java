/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Pablo Barvo - Mar 28, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package model.data_structures.grafo;
import java.awt.Color;
import java.io.Serializable;

/**
 * Interfaz utilizada para implementar el elemento de un v�rtice
 * @param <ID_VERT> Tipo del id del v�rtice
 */
public interface IVertice<K> extends Serializable
{
	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el ID del v�rtice
	 * @return Identificador del v�rtice
	 */
	public K darId( );

	public int darCantidadObjetos();
	
	public void setDensidad(int total);
	
	public double darDensidad();
}
