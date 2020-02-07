/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Pablo Barvo - Mar 28, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package model.data_structures.grafo;

import java.io.Serializable;

/**
 * Interfaz utilizada para representar las responsabilidades mínimas de un arco
 */
public interface IArco extends Serializable
{
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el peso del arco
     * @return Peso del arco
     */
    public double darPeso( );
    
    /**
	 * Devuelve el peso del arco que se quiere, diferenciandolos con enteros 
	 * @param referencia la referencia al peso que quiere que se devuelva 1=distancia
	 * 																	  2=tiempo
	 * 																	  3=valor
	 * 																	  4=cantidadQuePagaronPeaje
	 * 																	 de lo contrario devuelve 0
	 * @param referencia del peso que se quiere
	 * @return El peso
	 */
    public double darPeso(int referencia);
}
