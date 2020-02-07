/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: VerticeGrafo.java,v 1.1 2008/04/19 03:58:41 jua-gome Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo G�mez - Feb 29, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package model.data_structures.grafo;

/**
 * Representa un v�rtice del grafo que puede ser marcada y encapsula un elemento IVertice
 * @param <K> Tipo de identificador del v�rtice
 * @param <V> Tipo de datos del elemento del v�rtice
 */
public class VerticeGrafo<K, V extends IVertice<K>>
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Informaci�n contenida en el vertice
     */
    private V vertice;

    /**
     * Indica si el v�rtice est� marcado
     */
    private boolean marcado;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parametros.
     * @param vertice Informaci�n del vertice contenido
     */
    public VerticeGrafo( V vertice )
    {
        // Inicializar los atributos de la clase
        this.vertice = vertice;
        marcado = false;
    }

    // -----------------------------------------------------------------
    // M�todos consultores
    // -----------------------------------------------------------------

    /**
     * Retorna el <code>IVertice</code> encapsulado en el v�rtice
     * @return El <code>IVertice</code> encapsulado en el v�rtice
     */
    public V darInfoVertice( )
    {
        return vertice;
    }

    /**
     * Indica si el v�rtice est� marcado
     * @return <code>true</code> si el v�rtice est� marcado o <code>false</code> en caso contrario
     */
    public boolean estaMarcado( )
    {
        return marcado;
    }

    // -----------------------------------------------------------------
    // M�todos modificadores
    // -----------------------------------------------------------------

    /**
     * Prende la marca del v�rtice
     */
    public void marcar( )
    {
        marcado = true;
    }

    /**
     * Apaga la marca del v�rtice
     */
    public void desmarcar( )
    {
        marcado = false;
    }
}
