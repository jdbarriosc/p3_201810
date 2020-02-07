/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArcoYaExisteException.java,v 1.3 2008/10/11 22:02:54 alf-mora Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Pablo Barvo - Mar 29, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package model.data_structures.grafo;

/**
 * Excepci�n lanzada cuando el arco especificado ya existe
 */
public class ArcoYaExisteException extends Exception
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
     * Identificador del v�rtice desde donde deber�a salir el arco
     */
    private Object origen;

    /**
     * Identificador del v�rtice hasta donde deber�a llegar el arco
     */
    private Object destino;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepci�n
     * @param mensaje Mensaje de error
     * @param idDesde Identificador del v�rtice desde donde sale el arco
     * @param idHasta Identificador del v�rtice hasta donde llega el arco
     */
    public ArcoYaExisteException( String mensaje, Object idDesde, Object idHasta )
    {
        super( mensaje );
        origen = idDesde;
        destino = idHasta;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve el identificador del v�rtice desde donde debe salir el arco
     * @return identificador del v�rtice desde donde debe salir el arco
     */
    public Object darOrigen( )
    {
        return origen;
    }

    /**
     * Devuelve el identificador del v�rtice hasta donde debe llegar el arco
     * @return identificador del v�rtice hasta donde debe llegar el arco
     */
    public Object darDestino( )
    {
        return destino;
    }
}
