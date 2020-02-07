package view;

import java.awt.Desktop;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.examples.MarkersExample;

import controller.Controller;
import maps.java.ComponentsMap;
import maps.java.MarkerMap;
import maps.java.RouteMap;
import maps.java.RoutesMap;
import model.data_structures.ListaDoblementeEncadenada;
import model.data_structures.grafo.Camino;
import model.data_structures.grafo.ComponenteConexo;
import model.data_structures.grafo.IVertice;
import model.data_structures.grafo.Iterador;
import model.data_structures.grafo.VerticeNoExisteException;
import model.logic.TaxiTripsManager;
import model.vo.ArcoDistanciaTiempoValor;
import model.vo.VerticeLatLongServicios;



/**
 * view del programa
 */
public class TaxiTripsManagerView 
{
	static VerticeLatLongServicios i;
	static ComponenteConexo ii=null;
	static List<ComponenteConexo> iii=null;
	static ArrayList<VerticeLatLongServicios> iv=null;
	static ArrayList<ArrayList<VerticeLatLongServicios>> v=null;
	static ArrayList<ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>>> vi=null;
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		while(!fin)
		{


			//imprime menu
			printMenu();

			//opcion req
			int option = sc.nextInt();

			HttpURLConnection con;
			switch(option)
			{

			case 1: // cargar informacion a procesar

				//imprime menu cargar
				printMenuCargar();

				//opcion cargar
				int optionCargar = sc.nextInt();

				//directorio json
				String linkJson = "";
				switch (optionCargar)
				{
				//direccion json pequeno
				case 1:

					linkJson = TaxiTripsManager.DIRECCION_SMALL_JSON;
					break;

					//direccion json mediano
				case 2:

					linkJson = TaxiTripsManager.DIRECCION_MEDIUM_JSON;
					break;

					//direccion json grande
				case 3:

					linkJson = TaxiTripsManager.DIRECCION_LARGE_JSON;
					break;
				}


				printMenuDistancia();
				int distanciaRef = sc.nextInt();
				double distanciaReferencia = 0;
				switch (distanciaRef)
				{
				case 1:
					distanciaReferencia = 25;
					break;
				case 2:
					distanciaReferencia = 50;
					break;
				case 3:
					distanciaReferencia = 70;
					break;
				case 4:
					distanciaReferencia = 100;
					break;
				}

				printMenuPorPersistencia();
				int persist = sc.nextInt();
				boolean porPersist = false;
				switch (persist)
				{
				case 1:
					porPersist = true;
					break;
				case 2:
					porPersist = false;
					break;
				}

				System.out.println("Datos cargados: " + linkJson);
				System.out.println("Distancia de referencia para la creacion del grafo: " + distanciaReferencia+" metros");
				//Memoria y tiempo
				long memoryBeforeCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				long startTime = System.nanoTime();

				//Cargar data
				Controller.cargarSistema(linkJson,distanciaReferencia, porPersist);

				//Tiempo en cargar
				long endTime = System.nanoTime();
				long duration = (endTime - startTime)/(1000000);

				//Memoria usada
				long memoryAfterCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.out.println("Tiempo en cargar: " + duration + " milisegundos \nMemoria utilizada:  "+ ((memoryAfterCase1 - memoryBeforeCase1)/1000000.0) + " MB");

				break;
			case 2:
				String mens=Controller.generarJSON();
				System.out.println(mens);

				break;

			case 3:

				try {
					i = Controller.darVerticeMasCongestionado();
					System.out.println("El vertice mas congestionado fue: "+i.darId()+" con "+i.darCantidadObjetos()+" servicios");

				} catch (VerticeNoExisteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				maps.java.MarkerMap.mostrar();

				break;
			case 4:
				List<ComponenteConexo>componentes=Controller.darComponentesConexos();
				System.out.println("Se encontraron "+componentes.size()+" componentes");
				int posicion=1;
				int mayor=0;

				for(ComponenteConexo<String>actual:componentes)
				{

					System.out.println("La componente "+posicion+" tiene "+actual.darVertices()+" vertices"+ " y esta identificada con el color:"+actual.darColor());
					if(actual.darVertices()>mayor)
					{
						mayor=actual.darVertices();
						ii=actual;
					}
					posicion++;
				}
				if(ii!=null)
				{
					System.out.println("La componente mayor tiene "+ii.darVertices()+" vertices"+ " y esta identificada con el color:"+ii.darColor());

				}
				MarkerMap.mostrar();
				break;
			case 5:
				iii=Controller.darComponentesConexos();
				ComponentsMap.mostrar();
				break;
			case 6:
				iv=Controller.darCaminoDistanciaMinima();

				ArrayList<ArcoDistanciaTiempoValor> arcos=Controller.darArcosCamino(iv);

				//Pintar vertices y camino union entre ellos en el orden del iterador
				if(iv!=null&&!iv.isEmpty())
				{
					double tiempo=0;
					double distancia=0;
					double valor=0;

					if(!arcos.isEmpty())
					{
						System.out.println("--------------------------\nCamino entre "+arcos.get(0).getIdVerticeOrigen()+" y "+arcos.get(arcos.size()-1).getIdVerticeDestino()+":");
						for(ArcoDistanciaTiempoValor arco:arcos)
						{
							tiempo+=arco.darPromedioTiempo();
							distancia+=arco.darPromedioDistancia();
							valor+=arco.darPromedioValor();
						}

						System.out.println("-Tiempo estimado: "+tiempo+" segundos");
						System.out.println("-Distancia estimada: "+distancia+" metros");
						System.out.println("-Valos estimado: $"+valor);
						System.out.println("-Camino a seguir: ");
					}

					if(iv.size()>1)	
						for (int i = 0; (i+1) < iv.size(); i++) 	
							System.out.println("--De:"+iv.get(i).darId()+"    a:"+iv.get(i+1).darId());
					else System.out.println("-- Ya esta en el nodo de destino: "+iv.get(0).darId());

					RouteMap.mostrar();
				}
				else 
					System.out.println("No hay camino entre los puntos generados aleatoriamente");
				break;	



			case 7:
				v=Controller.darCaminoMenorDuracionIdaYVuelta();
				if(v!=null &&v.size()>0&&v.size()<=2)
				{	
					String entre="";
					for(int i=0;i<v.size();i++)
					{
						ArrayList<VerticeLatLongServicios> vertices=v.get(i);
						ArrayList<ArcoDistanciaTiempoValor> calles=Controller.darArcosCamino(vertices);

						//Pintar vertices y camino union entre ellos en el orden del iterador
						if(vertices!=null&&!vertices.isEmpty())
						{
							double tiempo=0;
							double distancia=0;
							double valor=0;


							if(i==0)System.out.println("--------------------------\nCamino de ida: ");
							else System.out.println("--------------------------\nCamino de vuelta: ");

							if(!calles.isEmpty())
							{
								if(entre.equals(""))
									entre="- Entre "+calles.get(0).getIdVerticeOrigen()+" y "+calles.get(calles.size()-1).getIdVerticeDestino()+":";
								System.out.println(entre);

								for(ArcoDistanciaTiempoValor arco:calles)
								{
									tiempo+=arco.darPromedioTiempo();
									distancia+=arco.darPromedioDistancia();
									valor+=arco.darPromedioValor();
								}

								System.out.println("-Tiempo estimado: "+tiempo+" segundos");
								System.out.println("-Distancia estimada: "+distancia+" metros");
								System.out.println("-Valos estimado: $"+valor);
								System.out.println("-Camino a seguir: ");
							}

							if(vertices.size()>1)	
								for (int j = 0; (j+1) < vertices.size(); j++) 	
									System.out.println("--De:"+vertices.get(j).darId()+"    a:"+vertices.get(j+1).darId());
							else System.out.println("-- Ya esta en el nodo de destino: "+vertices.get(0).darId());

						}

					}
					RoutesMap.mostrar();
				}
				else 
					System.out.println("No hay caminos");
				break;


			case 8:
				vi=Controller.darTodosCaminosSinPeaje();
				if(vi!=null &&vi.size()>0&&vi.size()<=2)
				{	
					for(int i=0;i<vi.size();i++)
					{
						if(i==0)System.out.println("--------------------------\nCaminos sin peaje por tiempo: ");
						else System.out.println("--------------------------\nCaminos sin peaje por costo: ");
						ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>> caminos=vi.get(i);

						for(int j=0;j<vi.size();j++) 
						{
							System.out.println("--Camino "+j+": ");

							Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor> camino; 
							camino=caminos.dar(j);

							//Pintar vertices y camino union entre ellos en el orden del iterador
							if(camino!=null)
							{

								System.out.println("---Tiempo estimado: "+camino.getTiempo()+" segundos");
								System.out.println("---Camino a seguir: ");

								List<VerticeLatLongServicios> vertices=camino.darVertices();
								if(vertices.size()>1)	
									for (int k = 0; (k+1) < vertices.size(); k++) 	
										System.out.println("---- De:"+vertices.get(k).darId()+"    a:"+vertices.get(k+1).darId());
								else System.out.println("---- Ya esta en el nodo de destino: "+vertices.get(0).darId());

							}
						}

					}
					//					RoutesMap.mostrar();
				}
				else 
					System.out.println("No hay caminos");
				break;



			case 9: 
				fin=true;
				sc.close();
				break;

			}
		}
	}

	/**
	 * Menu 
	 */
	private static void printMenu() //
	{
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Proyecto 2----------------------");
		System.out.println("Iniciar la Fuente de Datos a Consultar :");
		System.out.println("1. Cargar toda la informacion del sistema de una fuente de datos (small, medium o large).");
		System.out.println("2. Generar el archivo JSON con la información cargada.");
		System.out.println("3. Dar vertice mas congestionado");
		System.out.println("4. Dar componentes conexos");
		System.out.println("5. Dar mapa según densidad de servicios");
		System.out.println("6. Dar camino menor distancia entre dos puntos aleatorios");
		System.out.println("7. Dar caminos de ida y vuelta de menor duracion entre dos puntos aleatorios ");
		System.out.println("9. Salir");
		System.out.println("Ingrese el numero de la opcion seleccionada y presione <Enter> para confirmar: (e.g., 1):");

	}

	private static void printMenuCargar()
	{
		System.out.println("-- Que fuente de datos desea cargar?");
		System.out.println("-- 1. Small");
		System.out.println("-- 2. Medium");
		System.out.println("-- 3. Large");
		System.out.println("-- Ingrese el numero de la fuente a cargar y presione <Enter> para confirmar: (e.g., 1)");
	}
	private static void printMenuPorPersistencia()
	{
		System.out.println("-- Desea cargar los datos del JSON (Por persistencia)?");
		System.out.println("-- 1. Si");
		System.out.println("-- 2. No");
		System.out.println("-- Ingrese el numero de la fuente a cargar y presione <Enter> para confirmar: (e.g., 1)");
	}
	private static void printMenuDistancia()
	{
		System.out.println("-- Que distancia de referencia quiere utilizar para la creacion del grafo?");
		System.out.println("-- 1. 25 metros");
		System.out.println("-- 2. 50 metros");
		System.out.println("-- 3. 70 metros");
		System.out.println("-- 4. 100 metros");
		System.out.println("-- Ingrese el numero correspondiente a la distancia de referencia que desea cargar y presione <Enter> para confirmar: (e.g., 1)");
	}

	public static VerticeLatLongServicios darVerticeCongestionado()
	{
		return i;
	}
	public static ComponenteConexo darComponenteMayor()
	{
		return ii;
	}
	public static List<ComponenteConexo> darComponentes()
	{
		return iii;
	}
	public static ArrayList<VerticeLatLongServicios>darRuta()
	{
		return iv;
	}
	public static ArrayList<ArrayList<VerticeLatLongServicios>>darRutas()
	{
		return v;
	}


}
