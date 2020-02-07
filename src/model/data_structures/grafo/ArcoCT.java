/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArcoCT.java,v 1.2 2008/10/11 22:03:43 alf-mora Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - Feb 7, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package model.data_structures.grafo;
/**
 * Representa un arco en una clausura transitiva.
 */
public class ArcoCT implements IArco
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
    private double peso;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    

    /**
     * Constructor por parámetros del arco.
     * @param peso Peso del arco.
     */
    public ArcoCT( double peso )
    {
        this.peso = peso;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Retorna el peso del arco.
     * @return El peso del arco.
     */
    public double darPeso( )
    {
        return peso;
    }

	@Override
	public double darPeso(int referencia) {
		// TODO Auto-generated method stub
		return peso;
	}

}
