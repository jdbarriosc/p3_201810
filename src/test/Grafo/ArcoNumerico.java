/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArcoNumerico.java,v 1.2 2008/10/09 16:32:35 alf-mora Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - Abril 8, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package test.Grafo;

import model.data_structures.grafo.IArco;

/**
 * Representa un arco sencillo con peso númerico.
 */
public class ArcoNumerico implements IArco
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
    /**
	 * Constante para la serialización 
	 */
	private static final long serialVersionUID = 1L;
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
     * Peso del arco.
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Constructor por parámetros.
     * @param peso Peso del arco.
     */
    public ArcoNumerico( int peso )
    {
        this.peso = peso;
    }
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.grafo.IArco#darPeso()
     */
    public double darPeso( )
    {
        return (double)peso;
    }

	@Override
	public double darPeso(int referencia) {
		// TODO Auto-generated method stub
		return 0;
	}

}
