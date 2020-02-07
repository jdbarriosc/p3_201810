package model.vo;

import java.util.Comparator;

import model.data_structures.grafo.Camino;

public class ComparadorCaminosPorTiempo implements Comparator<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>> {

	@Override
	public int compare(Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor> cam1,
			Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor> cam2) {

		if(cam1.getTiempo()>cam2.getTiempo())
			return 1;
		if(cam2.getTiempo()>cam1.getTiempo())
			return -1;
		return 0;
	}

}
