/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: VerticeNumerico.java,v 1.2 2008/10/09 16:32:35 alf-mora Exp $
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

import java.awt.Color;

import model.data_structures.grafo.IVertice;

/**
 * Representa un vértice que contiene un dato numérico.
 */
public class VerticeNumerico implements IVertice<Integer>
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
     * Dato contenido por el vértice.
     */
    private int valor;

    private double densidad;
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------    
    
    /**
     * Constructor de la clase.
     * @param valor Dato contenido por el vértice.
     */
    public VerticeNumerico( int valor )
    {
        this.valor = valor;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------    
    
    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.grafo.IVertice#darId()
     */
    public Integer darId( )
    {
        return valor;
    }

	@Override
	public int darCantidadObjetos() {
		// NO SE USA
		return 1;
	}

	@Override
	public void setDensidad(int total) {
		densidad=(double)darCantidadObjetos()/total;
		
	}

	@Override
	public double darDensidad() {
		// TODO Auto-generated method stub
		return densidad;
	}


}
