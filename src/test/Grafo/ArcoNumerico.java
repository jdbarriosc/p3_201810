/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArcoNumerico.java,v 1.2 2008/10/09 16:32:35 alf-mora Exp $
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

import model.data_structures.grafo.IArco;

/**
 * Representa un arco sencillo con peso n�merico.
 */
public class ArcoNumerico implements IArco
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
    /**
	 * Constante para la serializaci�n 
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
     * Constructor por par�metros.
     * @param peso Peso del arco.
     */
    public ArcoNumerico( int peso )
    {
        this.peso = peso;
    }
    
    // -----------------------------------------------------------------
    // M�todos
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
