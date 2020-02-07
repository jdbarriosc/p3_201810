package model.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import api.ITaxiTripsManager;
import model.data_structures.ListaDoblementeEncadenada;
import model.data_structures.grafo.Camino;
import model.data_structures.grafo.ComponenteConexo;
import model.data_structures.grafo.Iterador;
import model.data_structures.grafo.VerticeNoExisteException;
import model.vo.ArcoDistanciaTiempoValor;
import model.vo.ClassManager;
import model.vo.ServiceVo;
import model.vo.VerticeLatLongServicios;



public class TaxiTripsManager implements ITaxiTripsManager 
{
	public static final String DIRECCION_SMALL_JSON = "./data/taxi-trips-wrvz-psew-subset-small.json";
	public static final String DIRECCION_MEDIUM_JSON = "./data/taxi-trips-wrvz-psew-subset-medium.json";
	public static final String DIRECCION_LARGE_JSON = "./data/taxi-trips-wrvz-psew-subset-large";
	public static final String DIRECCION_02= "/taxi-trips-wrvz-psew-subset-02-02-2017.json";
	public static final String DIRECCION_03= "/taxi-trips-wrvz-psew-subset-03-02-2017.json";
	public static final String DIRECCION_04= "/taxi-trips-wrvz-psew-subset-04-02-2017.json";
	public static final String DIRECCION_05= "/taxi-trips-wrvz-psew-subset-05-02-2017.json";
	public static final String DIRECCION_06= "/taxi-trips-wrvz-psew-subset-06-02-2017.json";
	public static final String DIRECCION_07= "/taxi-trips-wrvz-psew-subset-07-02-2017.json";
	public static final String DIRECCION_08= "/taxi-trips-wrvz-psew-subset-08-02-2017.json";






	private ClassManager administrador;
	private String tamano;
	private double distanciaReferencia;




	// //1C
	public boolean cargarSistema(String direccionJson,double distanciaReferencia, boolean porPersistencia) 
	{
		this.distanciaReferencia=distanciaReferencia;
		if(direccionJson==DIRECCION_MEDIUM_JSON)
			tamano="medium";
		else if(direccionJson==DIRECCION_LARGE_JSON)
			tamano="large";
		else tamano="small";

		try
		{

			administrador=new ClassManager(distanciaReferencia);
			
			if(porPersistencia && !(cargarVertices()&&cargarArcos()))
					return false;
			else if(direccionJson==DIRECCION_LARGE_JSON)
					cargarLarge();
			else
					cargarSmallMedium(direccionJson);
			cargarChicagoStreetsCSV();
			administrador.imprimirInfo();	
		}
		catch (Exception e) 
		{

			System.out.println(e.getMessage());
		}


		return true;
	}
	
	
	public boolean cargarArcos() throws Exception 
	{
		//Crear un InputStream como un new FileInputStream, el parametro que recibe es el link directorio del archivo json
		
		InputStream inputStream;
		try 
		{
			inputStream = new FileInputStream("./data/Persistencias-grafo/arcos-"+tamano+"-"+distanciaReferencia+"m.json");
		} catch (FileNotFoundException e1) {
			System.out.println("La persistencia de los arcos que desea cargar no existe.");
			return false;
		}

		//Inicializar un json reader que recibe como parametro el inputstream anteriormente creado

		JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
		Gson gson = new GsonBuilder().create();

		// Esto recorre todos los elementos que estan en el json. Como no los guarda es mas memory friendly
		reader.beginArray();
		try
		{
			while (reader.hasNext())
			{

				// Instancia un servicio del json
				ArcoDistanciaTiempoValor nArco = gson.fromJson(reader, ArcoDistanciaTiempoValor.class);
			
				administrador.agregarArco(nArco);
			
						
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		reader.close();
		return true;
	}

	public boolean cargarVertices() throws Exception
	{
		//Crear un InputStream como un new FileInputStream, el parametro que recibe es el link directorio del archivo json
		InputStream inputStream;
		try
		{
			inputStream = new FileInputStream("./data/Persistencias-grafo/vertices-"+tamano+"-"+distanciaReferencia+"m.json");
		} 
		catch (FileNotFoundException e1) 	
		{
			System.out.println("La persistencia de los vertices que desea cargar no existe.");
			return false;
		}
		//Inicializar un json reader que recibe como parametro el inputstream anteriormente creado

		JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
		Gson gson = new GsonBuilder().create();

		// Esto recorre todos los elementos que estan en el json. Como no los guarda es mas memory friendly
		reader.beginArray();
		try
		{
			while (reader.hasNext())
			{

				// Instancia un servicio del json
				VerticeLatLongServicios nVertice = gson.fromJson(reader, VerticeLatLongServicios.class);
			
				administrador.agregarVertice(nVertice);
	
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		reader.close();
		return true;
	}
	
	
	
	

	public void cargarSmallMedium(String direccionJson) throws Exception
	{
		//Crear un InputStream como un new FileInputStream, el parametro que recibe es el link directorio del archivo json
		InputStream inputStream = new FileInputStream(direccionJson);

		//Inicializar un json reader que recibe como parametro el inputstream anteriormente creado

		JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
		Gson gson = new GsonBuilder().create();

		// Esto recorre todos los elementos que estan en el json. Como no los guarda es mas memory friendly
		reader.beginArray();
		
		int contador=0;
		try
		{
			while (reader.hasNext())
			{

				// Instancia un servicio del json
				ServiceVo nServ = gson.fromJson(reader, ServiceVo.class);
				if(nServ.getPickup_centroid_latitude()!=null&&nServ.getPickup_centroid_longitude()!=null&&nServ.getDropoff_centroid_latitude()!=null&&nServ.getDropoff_centroid_longitude()!=null)
				{
					administrador.agregarServicio(nServ.getTrip_id()==null?"":nServ.getTrip_id(),
							nServ.getTrip_seconds()==null?0:Double.parseDouble(nServ.getTrip_seconds()),
							nServ.getTrip_miles()==null?0:Double.parseDouble(nServ.getTrip_miles()),
							nServ.getTrip_total()==null? 0:Double.parseDouble(nServ.getTrip_total()),
							Double.parseDouble(nServ.getPickup_centroid_latitude()),
							Double.parseDouble(nServ.getPickup_centroid_longitude()),
							Double.parseDouble(nServ.getDropoff_centroid_latitude()),
							Double.parseDouble(nServ.getDropoff_centroid_longitude()),
							nServ.getTolls()==null? 0:Double.parseDouble(nServ.getTolls()));
	
					contador++;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("Se cargaron "+contador+" servicios");
		reader.close();
	}


	public void cargarLarge() throws Exception
	{
		String direccion="";
		int contador=0;
		for(int i=1;i<8;i++)
		{
			if(i==1)
				direccion=DIRECCION_02;
			else if(i==2)
				direccion=DIRECCION_03;
			else if(i==3)
				direccion=DIRECCION_04;
			else if(i==4)
				direccion=DIRECCION_05;
			else if(i==5)
				direccion=DIRECCION_06;
			else if(i==6)
				direccion=DIRECCION_07;
			else
				direccion=DIRECCION_08;

			int contador1=0;

			//Crear un InputStream como un new FileInputStream, el parametro que recibe es el link directorio del archivo json
			InputStream inputStream = new FileInputStream(DIRECCION_LARGE_JSON+direccion);

			//Inicializar un json reader que recibe como parametro el inputstream anteriormente creado

			JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
			Gson gson = new GsonBuilder().create();

			// Esto recorre todos los elementos que estan en el json. Como no los guarda es mas memory friendly
			reader.beginArray();
			try
			{
				while (reader.hasNext())
				{
	
					// Instancia un servicio del json
					ServiceVo nServ = gson.fromJson(reader, ServiceVo.class);
					if(nServ.getPickup_centroid_latitude()!=null&&nServ.getPickup_centroid_longitude()!=null&&nServ.getDropoff_centroid_latitude()!=null&&nServ.getDropoff_centroid_longitude()!=null)
					{
						administrador.agregarServicio(nServ.getTrip_id()==null?"":nServ.getTrip_id(),
								nServ.getTrip_seconds()==null?0:Double.parseDouble(nServ.getTrip_seconds()),
								nServ.getTrip_miles()==null?0:Double.parseDouble(nServ.getTrip_miles()),
								nServ.getTrip_total()==null? 0:Double.parseDouble(nServ.getTrip_total()),
								Double.parseDouble(nServ.getPickup_centroid_latitude()),
								Double.parseDouble(nServ.getPickup_centroid_longitude()),
								Double.parseDouble(nServ.getDropoff_centroid_latitude()),
								Double.parseDouble(nServ.getDropoff_centroid_longitude()),
								nServ.getTolls()==null? 0:Double.parseDouble(nServ.getTolls()));
		
						contador++;
						contador1++;
					}
	
				
	
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
//			System.out.println("Se cargaron "+contador1+" servicios");
			reader.close();

		}
		System.out.println("Hay "+contador+ " servivios en total");

	}

	public void cargarChicagoStreetsCSV()
	{
		File file=new File("./data/ChicagoStreets.csv");
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
		    String line= reader.readLine();
		    while ((line = reader.readLine()) != null) 
		    {
		        String[] info=line.split(";");
		        if(info.length>=8)
		    	   for(int i=6;i<info.length;i++)
		    		   administrador.agregarCoordenada(info[i].trim().replace(" ", "/"));
		    }
			reader.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("Error leyendo el csv");
			e.printStackTrace();

		}
	}
	

	public String generarJSON()
	{
		return 	administrador.generarJSON(tamano,distanciaReferencia+"");

	}


	@Override
	public VerticeLatLongServicios darVerticeMasCongestionado() throws VerticeNoExisteException {
		// TODO Auto-generated method stub
		return administrador.darVerticeMasCongestionado();
	}


	@Override
	public List<ComponenteConexo> darComponentesConexos() {
		// TODO Auto-generated method stub
		return administrador.darComponentesConexos();
	}
	
	public ArrayList<VerticeLatLongServicios> darCaminoDistanciaMinima()
	{
		return administrador.darCaminoDistanciaMinima();
	}
	
	public 	ArrayList<ArrayList<VerticeLatLongServicios>> darCaminoMenorDuracionIdaYVuelta()
	{
		return administrador.darCaminoMenorDuracionIdaYVuelta();
	}
	public ArrayList<ArcoDistanciaTiempoValor> darArcosCamino(ArrayList<VerticeLatLongServicios> camino)
	{
		return administrador.darArcosCamino(camino);
	}

	public ArrayList<ListaDoblementeEncadenada<Camino<String, VerticeLatLongServicios, ArcoDistanciaTiempoValor>>> darTodosCaminosSinPeaje()
	{
		return administrador.darTodosCaminosSinPeaje();
	}


}
