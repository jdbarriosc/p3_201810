package controller;

import java.util.ArrayList;
import java.util.List;

import api.ITaxiTripsManager;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.ListaDoblementeEncadenada;
import model.data_structures.PriorityQueueListaDoble;
import model.data_structures.grafo.Camino;
import model.data_structures.grafo.ComponenteConexo;
import model.data_structures.grafo.Iterador;
import model.data_structures.grafo.VerticeNoExisteException;
import model.data_structures.ILinkedList;
import model.logic.TaxiTripsManager;
import model.vo.ArcoDistanciaTiempoValor;
import model.vo.VerticeLatLongServicios;




public class Controller 
{
	/**
	 * modela el manejador de la clase l�gica
	 */
	private static ITaxiTripsManager manager = new TaxiTripsManager();

	//1C
	public static boolean cargarSistema(String direccionJson, double distanciaReferencia, boolean porPersistencia)
	{
		return manager.cargarSistema(direccionJson, distanciaReferencia, porPersistencia);
	}

	public static String generarJSON()
	{
		return manager.generarJSON();
	}
	
	public static VerticeLatLongServicios darVerticeMasCongestionado() throws VerticeNoExisteException
	{

		return manager.darVerticeMasCongestionado();
	}
	public static List<ComponenteConexo> darComponentesConexos()
	{
		return manager.darComponentesConexos();
	}
	public static ArrayList<VerticeLatLongServicios> darCaminoDistanciaMinima()
	{
		return manager.darCaminoDistanciaMinima();
	}
	
	public 	static ArrayList<ArrayList<VerticeLatLongServicios>> darCaminoMenorDuracionIdaYVuelta()
	{
		return manager.darCaminoMenorDuracionIdaYVuelta();
	}
	public static ArrayList<ArcoDistanciaTiempoValor> darArcosCamino(ArrayList<VerticeLatLongServicios> camino)
	{
		return manager.darArcosCamino(camino);
	}

	public static ArrayList<ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>>> darTodosCaminosSinPeaje()
	{
		return manager.darTodosCaminosSinPeaje();
	}


}
