package model.data_structures.grafo;

import java.awt.Color;
import java.util.Random;

import model.vo.VerticeLatLongServicios;

public class ComponenteConexo<K> 
{

	private GrafoDirigido<K, IVertice<K>, IArco> grafo;

	private String color;
	
	private int cantidad;

	public ComponenteConexo(GrafoDirigido<K, IVertice<K>, IArco> diGraph, int cantidad)
	{
		grafo=diGraph;
		Random rand = new Random();	 
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color color2 = new Color(r, g, b);
		color = Integer.toHexString(color2.getRGB() & 0x00FFFFFF);  
		this.cantidad=cantidad;
		for (IVertice<K>actual:grafo.darVertices()) 
		{
			actual.setDensidad(cantidad);
		}
	}

	public String darColor()
	{
		return color;
	}

	public GrafoDirigido<K, IVertice<K>, IArco> darGrafo()
	{
		return grafo;
	}

	public int darVertices()
	{
		return grafo.darCantVertices();
	}
}
