/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Camino.java,v 1.7 2008/04/19 03:58:41 jua-gome Exp $
 * Universidad de los Andes (Bogotï¿½ - Colombia)
 * Departamento de Ingenierï¿½a de Sistemas y Computaciï¿½n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: J. Villalobos - Abril 14, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package model.data_structures.grafo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representa un camino en un grafo
 * @param <K> Tipo del identificador de un vértice
 * @param <V> Tipo de datos del elemento del vértice
 * @param <A> Tipo de datos del elemento del arco
 */
public class Camino<K, V extends IVertice<K>, A extends IArco> implements Comparable<Camino<K,V,A>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista con los arcos del camino
     */
    private List<A> arcos;

    /**
     * Lista con los vértices del camino
     */
    private List<V> vertices;

    /**
     * Origen del camino
     */
    private V origen;

    /**
     * Costo total del camino
     */
    private int costo;
    
    private int tiempo;

    private int distancia;

    private int pagaronPeaje;


    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del camino
     * @param origen Vértice de origen del camino
     */
    public Camino( V origen )
    {
        // Inicializar los atributos del camino
        vertices = new ArrayList<V>( );
        arcos = new ArrayList<A>( );
        costo = 0;
        tiempo = 0;
        distancia = 0;
        pagaronPeaje = 0;
        this.origen = origen;
    }

    public Camino( Camino<K, V, A> camino )
    {
        // Inicializar los atributos del camino
        vertices =camino.darVertices();
        arcos = camino.darArcos();
        costo = camino.darCosto();
        tiempo = camino.getTiempo();
        distancia = camino.getDistancia();
        pagaronPeaje = camino.getPagaronPeaje();
        origen = camino.darOrigen();
    }
    
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega un arco al final del camino
     * @param arco Arco a agregar
     */
    public void agregarArcoFinal( V vertice, A arco )
    {
        arcos.add( arco );
        vertices.add( vertice );
        costo += arco.darPeso(3);
        tiempo += arco.darPeso(2);
        distancia += arco.darPeso(1);
        pagaronPeaje += arco.darPeso(4);
    }

    
    /**
     * Agrega un arco al final del camino
     * @param arco Arco a agregar
     */
    public void agregarArcoFinal( int referencia, V vertice, A arco )
    {
        arcos.add( arco );
        vertices.add( vertice );
        costo += arco.darPeso(referencia);
    }
    /**
     * Agrega un arco al comienzo del camino.
     * @param nuevoOrigen Nuevo origen del camino
     * @param arco Arco que va del nuevo origen al antiguo origen del camino
     */
    public void agregarArcoComienzo( V nuevoOrigen, A arco )
    {
        arcos.add( 0,arco );
        vertices.add(0, origen );
        origen = nuevoOrigen;
        costo += arco.darPeso( );
    }

    /**
     * Concatena todos los arcos del camino especificado al final del camino
     * @param camino Camino a concatenar
     */
    public void concatenar( Camino<K, V, A> camino )
    {
        // Agregar los arcos y vertices del camino a concatenar ignorando el origen del camino ingresado por parámetro
        for( int i = 0; i < camino.arcos.size( ); i++ )
            agregarArcoFinal( camino.vertices.get( i ), camino.arcos.get( i ) );
    }

    /**
     * Elimina el último arco
     */
    public void eliminarUltimoArco( )
    {
        if( arcos.size( ) >= 1 )
        {
            A arco = arcos.get( arcos.size( ) - 1 );
            arcos.remove( arcos.size( ) - 1 );
            vertices.remove( vertices.size( ) - 1 );
            costo -= arco.darPeso( );
        }
    }

    public List<V> darVertices()
    {
    	return vertices;
    }
    
    public List<A> darArcos()
    {
    	return arcos;
    }
    
    /**
     * Reinicia el camino conservando el origen
     */
    public void reiniciar( )
    {
        arcos.clear( );
        vertices.clear( );
        costo = 0;
    }

    /**
     * Devuelve la longitud del camino
     * @return Longitud del camino
     */
    public int darLongitud( )
    {
        return arcos.size( );
    }

    /**
     * Devuelve el costo del camino
     * @return Costo del camino
     */
    public int darCosto( )
    {
        return costo;
    }

    /**
     * Devuelve los vértices por los cuales pasa el camino
     * @return Iterador sobre los vértices
     */
    public Iterator<V> darSecuenciaVertices( )
    {
        // Crear una lista auxiliar y agregarle el origen
        List<V> aux = new ArrayList<V>( );
        aux.add( origen );

        // Poblara la lista auxiliar con los vértices del camino
        for( int i = 0; i < vertices.size( ); i++ )
        {
            aux.add( vertices.get( i ) );
        }

        // Retornar el iterador
        return aux.iterator( );
    }

    /**
     * Retorna el origen del camino
     * @return El vertice desde el que se origina el camino
     */
    public V darOrigen( )
    {
        return origen;
    }

    public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getPagaronPeaje() {
		return pagaronPeaje;
	}

	public void setPagaronPeaje(int pagaronPeaje) {
		this.pagaronPeaje = pagaronPeaje;
	}



	@Override
	public int compareTo(Camino<K, V, A> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	


}
