package model.vo;

import java.awt.Color;
import java.util.ArrayList;

import model.data_structures.ListaDoblementeEncadenada;
import model.data_structures.grafo.IVertice;

public class VerticeLatLongServicios implements IVertice<String>,Comparable<VerticeLatLongServicios>
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
    private ArrayList<String> idServicios;
    private double latitud;
    private double longitud;
    public double densidad;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------    
    
    /**
     * Constructor de la clase.
     * @param valor Dato contenido por el vértice.
     */
    public VerticeLatLongServicios( double latitud,double longitud,String idServicio )
    {
        this.latitud = latitud;
        this.longitud = longitud;
        idServicios=new ArrayList<String>();
        idServicios.add(idServicio);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------    
    public double darLatitud()
    {
    	return latitud;
    }
    public double darLongitud()
    {
    	return longitud;
    }
    public ArrayList<String> darIdServicios()
    {
    	return idServicios;
    }
    public void agregarServicio(String idServicio)
    {
    	idServicios.add(idServicio);
    }
    
    public void setDensidad(int servTotales)
    {
    	densidad=(double)servTotales/darCantidadObjetos();
    }
    
    public double darDensidad()
    {
    	return densidad;
    }

	@Override
	public String darId() {
		return latitud+"/"+longitud;
	}

	@Override
	public int compareTo(VerticeLatLongServicios arg0) {
		return darId().compareTo(arg0.darId());
	}

	@Override
	public int darCantidadObjetos() {
		// TODO Auto-generated method stub
		return darIdServicios().size();
	}



}
