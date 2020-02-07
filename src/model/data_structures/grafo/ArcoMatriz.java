/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan E. Gomez - Ene 28, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package model.data_structures.grafo;

/**
 * Representa una posici�n en una matriz de adyacencia
 */
public class ArcoMatriz<K, V extends IVertice<K>, A extends IArco>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
    /**
     * Marca del arco
     */
    private boolean marcado;

    /**
     * Arco
     */
    private Arco<K,V,A> arco;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
	/**
	 * Construye una posici�n de una matriz de ayacencia a partir de un
	 * <code>Arco</code>
	 * @param arco Informaci�n del arco
	 */
	public ArcoMatriz(Arco<K,V,A> arco) {
		marcado = false;
		this.arco = arco;
	}

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
	
	/**
	 * Retorna la marca del arco
	 * @return <code>true</code> si el arco est� marcado o <code>false</code>
	 *         en caso contrario
	 */
	public boolean marcado() {
		return marcado;
	}

	/**
	 * Marca el arco
	 */
	public void marcar() {
		marcado = true;
	}

	/**
	 * Elimina la marca del vertice
	 */
	public void desmarcar() {
		marcado = false;
	}

	/**
	 * Retorna el arco contenido
	 * @return La informaci�n de arco contenido
	 */
	public Arco<K,V,A> darArco() {
		return arco;
	}

}
