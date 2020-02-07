package model.vo;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.junit.internal.ArrayComparisonFailure;

import com.google.gson.Gson;

import GUI.MainFrame;
import api.ITaxiTripsManager;
import model.data_structures.ListaDoblementeEncadenada;
import model.data_structures.SeparateChainingHashST;
import model.data_structures.grafo.ArcoNoExisteException;
import model.data_structures.grafo.ArcoYaExisteException;
import model.data_structures.grafo.Camino;
import model.data_structures.grafo.CaminosMinimos;
import model.data_structures.grafo.ComponenteConexo;
import model.data_structures.grafo.GrafoDirigido;
import model.data_structures.grafo.Iterador;
import model.data_structures.grafo.IteradorSimple;
import model.data_structures.grafo.VerticeNoExisteException;
import model.data_structures.grafo.VerticeYaExisteException;


public class ClassManager {


	private double distanciaReferencia;
	private static GrafoDirigido<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor> grafo;
	private ListaDoblementeEncadenada<String> coordenadasCalles;

	public ClassManager(double distanciaReferencia)
	{
		this.distanciaReferencia=distanciaReferencia;
		grafo=new GrafoDirigido<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>();
		coordenadasCalles= new ListaDoblementeEncadenada<String>();
	}

	public void agregarServicio(String tripId, double seconds, double miles, double total,double pickUpLatitude,double pickUpLongitude,double dropOffLatitude,double dropOffLongitude, double peajes) throws VerticeNoExisteException, ArcoYaExisteException  
	{
		//si no tenemos la informacion de alguna de estas no tendriamos info suficiente para agregar los nodos
		try
		{
			VerticeLatLongServicios inicio=agregarModificarVertice(tripId,pickUpLatitude,pickUpLongitude);
			VerticeLatLongServicios fin=agregarModificarVertice(tripId,dropOffLatitude,dropOffLongitude);
			try
			{
				ArcoDistanciaTiempoValor arco=grafo.darArco(inicio.darId(), fin.darId());
				arco.agregarValores(miles*1609.34, seconds, total, peajes);
			}
			catch (ArcoNoExisteException e)
			{
				grafo.agregarArco(inicio.darId(), fin.darId(),new ArcoDistanciaTiempoValor(inicio.darId(), fin.darId(),miles*1609.34, seconds, total, peajes));
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}


	}

	public void agregarArco(ArcoDistanciaTiempoValor arco)
	{

		try 
		{
			grafo.agregarArco(arco.getIdVerticeOrigen(), arco.getIdVerticeDestino(),arco);
		} catch (VerticeNoExisteException | ArcoYaExisteException e) 
		{
			e.printStackTrace();
		}

	}
	public void agregarVertice(VerticeLatLongServicios vertice)
	{
		try
		{
			grafo.agregarVertice(vertice);
		} catch (VerticeYaExisteException e)
		{
			e.printStackTrace();
		}
	}

	public VerticeLatLongServicios agregarModificarVertice(String tripId,double latitude,double longitude) throws VerticeYaExisteException
	{
		VerticeLatLongServicios vertice=null;
		double distanciaMin=Double.POSITIVE_INFINITY;
		for(VerticeLatLongServicios actual:grafo.darVertices())
		{
			double dist=distanciaEnMetros(actual.darLatitud(),actual.darLongitud(),latitude,longitude,true);
			if(dist<distanciaMin)
			{
				vertice=actual;
				distanciaMin=dist;
			}
		}
		if(vertice==null)
		{
			vertice=new VerticeLatLongServicios(latitude,longitude,tripId);
			grafo.agregarVertice(vertice);
			return vertice;
		}
		vertice.agregarServicio(tripId);
		return vertice;

	}
	
	public void agregarCoordenada(String pCoordenadas)
	{
		coordenadasCalles.add(pCoordenadas);
	}
	public double[] darLatLongAleatorio()
	{
		try
		{
			String[] latlong=coordenadasCalles.dar((int)(Math.random()*coordenadasCalles.size())).split("/");
			double[] resp=new double[2];
			resp[1]=Double.parseDouble(latlong[0]);
			resp[0]=Double.parseDouble(latlong[1]);
			return resp;
		}
		catch(Exception e)
		{
			System.out.println("Hay alguna coordenada corrupta");
			return null;
		}

	}
	
	public VerticeLatLongServicios darVerticeMasCercano(double[] latlong)
	{
		VerticeLatLongServicios vertice=null;
		double distanciaMin=Double.POSITIVE_INFINITY;
		for(VerticeLatLongServicios actual:grafo.darVertices())
		{
			double dist=distanciaEnMetros(actual.darLatitud(),actual.darLongitud(),latlong[0],latlong[1],false);
			if(dist<distanciaMin)
			{
				vertice=actual;
				distanciaMin=dist;
			}
		}
		
		return vertice;
	}

	public double distanciaEnMetros(double pickupLatitud, double pickupLongitud,double pLat, double pLon, boolean conDistRef) 
	{
		try
		{
			int r=6371*1000;
			Double latDist=toRad(pickupLatitud-pLat);
			Double lonDist=toRad(pickupLongitud-pLon);
			Double a = Math.sin(latDist/2)*Math.sin(latDist/2)
					+Math.cos(toRad(pLat))*Math.cos(toRad(pickupLatitud))
					*Math.sin(lonDist/2)*Math.sin(lonDist/2);
			Double c=2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			if(conDistRef)
				return distanciaReferencia>=(r*c)?(r*c):Double.POSITIVE_INFINITY;
			return (r*c);



		}
		catch(Exception e)
		{ 	
			e.printStackTrace();
			System.out.println("entro");
			return Double.POSITIVE_INFINITY;
		}
	}
	public double toRad(double grados)
	{
		return (grados/90)*3.1416;
	}
	public VerticeLatLongServicios darVerticeMasCongestionado() throws VerticeNoExisteException
	{
		String idMayor="";
		int mayor=0;
		SeparateChainingHashST<String, String> servs= new SeparateChainingHashST<>(grafo.darCantVertices());
		Iterator<ArcoDistanciaTiempoValor> iter=grafo.darArcos().iterator();
		while(iter.hasNext())
		{
			ArcoDistanciaTiempoValor arco=iter.next();
			servs.put(arco.getIdVerticeOrigen(), arco.getIdVerticeOrigen());
			servs.put(arco.getIdVerticeDestino(), arco.getIdVerticeDestino());
		}
		Iterator<String>iter2=servs.keys().iterator();
		while (iter2.hasNext()) 
		{
			String key=iter2.next();
			int cantidad=servs.get(key).size();
			if(cantidad>mayor)
			{
				mayor=cantidad;
				idMayor=key;
			}

		}

		VerticeLatLongServicios vert=grafo.darVertice(idMayor);
		return vert;

	}
	public static List<VerticeLatLongServicios>darTodosSucesores(String id) throws VerticeNoExisteException
	{
		return grafo.darSucesores(id);
	}
	public List<ComponenteConexo> darComponentesConexos()
	{
		System.out.println("cantidad:"+grafo.contarComponentesConexos());
		return grafo.darComponentesConexos();
	}

	public void imprimirInfo()
	{
		System.out.println("Información del grafo: ");
		System.out.println("--Número de vértices: "+grafo.darVertices().size());
		System.out.println("--Número de arcos: "+grafo.darArcos().size());
		System.out.println("--Distancia utilizada: "+distanciaReferencia+" métros");
	}
	
	public void printLatLongs()
	{
		for(VerticeLatLongServicios vertice: grafo.darVertices())
			System.out.println(vertice.darId());
	}
	
	public 	ArrayList<ArrayList<VerticeLatLongServicios>> darCaminoMenorDuracionIdaYVuelta()
	{
		
		ArrayList<ArrayList<VerticeLatLongServicios>> caminos=new ArrayList<ArrayList<VerticeLatLongServicios>>();
		try 
		{
			VerticeLatLongServicios inicio= darVerticeMasCercano(darLatLongAleatorio());
			VerticeLatLongServicios fin= darVerticeMasCercano(darLatLongAleatorio());
			if(inicio.equals(fin))
			{
				ArrayList<VerticeLatLongServicios> camino = new ArrayList<VerticeLatLongServicios>();
				camino.add( inicio );	
				caminos.add(camino);
			}
			else 
			{
				Iterador<VerticeLatLongServicios> iter =null;
				if(grafo.hayCamino(inicio.darId(), fin.darId()))
				{
					iter=grafo.dijkstra(2,inicio.darId()).darCaminoMinimo(grafo.darObjetoVertice(fin.darId()));
					ArrayList<VerticeLatLongServicios> camino=new ArrayList<VerticeLatLongServicios>();
					VerticeLatLongServicios actual;
					while((actual=iter.darSiguiente())!=null)
						camino.add(actual);
					caminos.add(camino);
					
				}
				if(grafo.hayCamino(fin.darId(), inicio.darId()))
				{
					iter=grafo.dijkstra(2,fin.darId()).darCaminoMinimo(grafo.darObjetoVertice(inicio.darId()));
					ArrayList<VerticeLatLongServicios> camino=new ArrayList<VerticeLatLongServicios>();
					VerticeLatLongServicios actual;
					while((actual=iter.darSiguiente())!=null)
						camino.add(actual);
					caminos.add(camino);
				}
			}
			return caminos;
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	
	public ArrayList<VerticeLatLongServicios> darCaminoDistanciaMinima()
	{
		try 
		{
			VerticeLatLongServicios inicio= darVerticeMasCercano(darLatLongAleatorio());
			VerticeLatLongServicios fin= darVerticeMasCercano(darLatLongAleatorio());
			Iterador<VerticeLatLongServicios> iter =null;
			if(inicio.equals(fin))
			{
				ArrayList<VerticeLatLongServicios> lista=new ArrayList<VerticeLatLongServicios>();
				lista.add(inicio);
				return lista;
			}
			else if(grafo.hayCamino(inicio.darId(), fin.darId()))
				iter=grafo.dijkstra(inicio.darId()).darCaminoMinimo(grafo.darObjetoVertice(fin.darId()));
			else return null;
		
			ArrayList<VerticeLatLongServicios> lista=new ArrayList<VerticeLatLongServicios>();
			VerticeLatLongServicios actual;
			while((actual=iter.darSiguiente())!=null)
				lista.add(actual);
			
			
			return lista;
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	
	public ArrayList<ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>>> darTodosCaminosSinPeaje()
	{
		try 
		{
			ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>>porTiempo;
			ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>>porCosto;
			ArrayList<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>> lista;
			ArrayList<ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>>> caminosOrdenados;
			VerticeLatLongServicios inicio= darVerticeMasCercano(darLatLongAleatorio());
			VerticeLatLongServicios fin= darVerticeMasCercano(darLatLongAleatorio());
			if(inicio.equals(fin))
			{
				porTiempo=new ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>>();
				caminosOrdenados=new ArrayList<ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>>>();
				porTiempo.add(new Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>(inicio));
				caminosOrdenados.add(porTiempo);
				return caminosOrdenados;
			}
			else if(grafo.hayCamino(inicio.darId(), fin.darId()))
				lista =grafo.darTodosLosCaminos(inicio.darId(), fin.darId());
			else return null;
		
			
			caminosOrdenados=new ArrayList<ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>>>();
			
			Comparator<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>> compTime=new ComparadorCaminosPorTiempo();
			Comparator<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>> compCost=new ComparadorCaminosPorCostoInvertido();
			
			porTiempo=new ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>>();
			porCosto=new ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>>();

			for(Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor> actual:lista)
				if(actual.getPagaronPeaje()==0)
				{
					porTiempo.addInOrderComparador(compTime, actual);
					porCosto.addInOrderComparador(compCost, actual);
				}
			caminosOrdenados.add(porTiempo);
			caminosOrdenados.add(porCosto);
			
			return caminosOrdenados;
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	
	/**
	 * Calcula todos los caminos mínimos desde el vértice dado hacia los demás vértices del grafo
	 * @param idVertice El identificador del vértice
	 * @return Los caminos mínimos desde el vértice especificado hacía los demás nodos
	 * @throws Lista de arcos que unen los nodos de un camino
	 */
	public ArrayList<ArcoDistanciaTiempoValor> darArcosCamino(ArrayList<VerticeLatLongServicios> camino)
	{	
		ArrayList<ArcoDistanciaTiempoValor> arcos = new ArrayList<ArcoDistanciaTiempoValor>();
		if(camino!=null&&camino.size()>1)	
			for (int i = 0; (i+1) < camino.size(); i++) 	
				try 
				{
					arcos.add(grafo.darArco(camino.get(i).darId(), camino.get(i+1).darId()));
				} 
				catch (Exception e)
				{
					// no deberia entrar
					System.out.println("Hay arcos que no existen (No deberia pasar)");
				}
		return arcos;
		
	}
	
	
	

	public String generarJSON(String tamanoDatos, String distanciaDeReferencia)
	{
		File archivoArcos = new File("./data/Persistencias-grafo/arcos-"+tamanoDatos+"-"+distanciaReferencia+"m.json");
		File archivoVertices = new File("./data/Persistencias-grafo/vertices-"+tamanoDatos+"-"+distanciaReferencia+"m.json");
		Gson gson=new Gson();


		try 
		{
			if(archivoVertices.exists()&&archivoArcos.exists())
				return "El JSON ya habia sido generado";

			if(!archivoVertices.exists())
			{
				archivoVertices.createNewFile();

				PrintWriter pw = new PrintWriter(archivoVertices);
				pw.println(gson.toJson(grafo.darVertices()));
				pw.close();
			}

			if(!archivoArcos.exists())
			{
				archivoArcos.createNewFile();

				PrintWriter pw1 = new PrintWriter(archivoArcos);
				pw1.println(gson.toJson(grafo.darArcos()));
				pw1.close();
			}
			return "El JSON se genero correctamente";


		}
		catch (Exception e) {
			return "Hubo un problema escribiendo el JSON";
		}
	}


}
