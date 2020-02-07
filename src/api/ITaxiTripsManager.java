package api;

import java.util.ArrayList;
import java.util.List;

import model.data_structures.ListaDoblementeEncadenada;
import model.data_structures.grafo.Camino;
import model.data_structures.grafo.ComponenteConexo;
import model.data_structures.grafo.Iterador;
import model.data_structures.grafo.VerticeNoExisteException;
import model.vo.ArcoDistanciaTiempoValor;
import model.vo.VerticeLatLongServicios;

/**
 * API para la clase de logica principal  
 */
public interface ITaxiTripsManager 
{

	public boolean cargarSistema(String direccionJson, double distanciaReferencia, boolean porPersistencia);

	public String generarJSON();

	public VerticeLatLongServicios darVerticeMasCongestionado() throws VerticeNoExisteException;

	public List<ComponenteConexo> darComponentesConexos();
	
	public ArrayList<VerticeLatLongServicios> darCaminoDistanciaMinima();
	
	public 	ArrayList<ArrayList<VerticeLatLongServicios>> darCaminoMenorDuracionIdaYVuelta();
	
	public ArrayList<ArcoDistanciaTiempoValor> darArcosCamino(ArrayList<VerticeLatLongServicios> camino);

	public ArrayList<ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>>> darTodosCaminosSinPeaje();

}
