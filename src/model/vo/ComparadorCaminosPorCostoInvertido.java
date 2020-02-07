package model.vo;

import java.util.Comparator;

import model.data_structures.grafo.Camino;

public class ComparadorCaminosPorCostoInvertido implements Comparator<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>> {

	@Override
	public int compare(Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor> cam1,
			Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor> cam2) {

		if(cam1.darCosto()>cam2.darCosto())
			return -1;
		if(cam2.darCosto()>cam1.darCosto())
			return 1;
		return 0;
	}

}
