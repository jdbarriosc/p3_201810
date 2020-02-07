/*
 * Copyright (c) 2000-2017 TeamDev Ltd. All rights reserved.
 * Use is subject to Apache 2.0 license terms.
 */
package maps.java;

import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapMouseEvent;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MouseEvent;
import com.teamdev.jxmaps.Polygon;
import com.teamdev.jxmaps.PolygonOptions;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.swing.MapView;

import controller.Controller;
import model.data_structures.grafo.Arco;
import model.data_structures.grafo.ComponenteConexo;
import model.data_structures.grafo.GrafoDirigido;
import model.data_structures.grafo.Iterador;
import model.data_structures.grafo.VerticeNoExisteException;
import model.vo.ArcoDistanciaTiempoValor;
import model.vo.ClassManager;
import model.vo.VerticeLatLongServicios;
import view.TaxiTripsManagerView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This example demonstrates how to create and customize a Marker on the Map.
 *
 * @author Vitaly Eremenko
 */
public class RoutesMap extends MapView {
	public RoutesMap() {
		// Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
		// the map object is ready to use. Current implementation of onMapReady customizes the map object.
		setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				// Check if the map is loaded correctly
				if (status == MapStatus.MAP_STATUS_OK) {
					// Getting the associated map object
					final Map map = getMap();
					// Creating a map options object
					MapOptions options = new MapOptions();
					// Creating a map type control options object
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					// Changing position of the map type control
					controlOptions.setPosition(ControlPosition.TOP_RIGHT);
					map.setCenter(new LatLng(41.75, -87.39));
					// Setting map type control options
					options.setMapTypeControlOptions(controlOptions);
					// Setting map options
					map.setOptions(options);
					// Setting the map center

					// Setting initial zoom value
					map.setZoom(9.0);


					Iterator<ArrayList<VerticeLatLongServicios>> rutas=TaxiTripsManagerView.darRutas().iterator();

					while(rutas.hasNext())
					{

						Iterator<VerticeLatLongServicios>iter=rutas.next().iterator();
						VerticeLatLongServicios inicio= iter.next();
						Random rand = new Random();	 
						float r = rand.nextFloat();
						float g = rand.nextFloat();
						float b = rand.nextFloat();
						Color color = new Color(r, g, b);
						String color2 = Integer.toHexString(color.getRGB() & 0x00FFFFFF); 

						if(color2.length()<6)
						{
							color2="0"+color2;
						}
						String colorh="#"+color2;
						Marker marker = new Marker(map);
						marker.setIcon("http://www.googlemapsmarkers.com/v1/"+color2+"/");

						// Setting marker position
						marker.setPosition(new LatLng(inicio.darLatitud(), inicio.darLongitud()));	

						VerticeLatLongServicios anterior= inicio;

						while(iter.hasNext())
						{
							VerticeLatLongServicios vert= iter.next();
							// Creating a new marker object
							marker = new Marker(map);
							marker.setIcon("http://www.googlemapsmarkers.com/v1/"+color2+"/");

							// Setting marker position
							marker.setPosition(new LatLng(vert.darLatitud(), vert.darLongitud()));	

							LatLng[]vertices={new LatLng(anterior.darLatitud(),anterior.darLongitud()),new LatLng(vert.darLatitud(), vert.darLongitud())};
							Polygon polygon=new Polygon(map);
							PolygonOptions opcion=new PolygonOptions();

							opcion.setFillColor(colorh);
							opcion.setFillOpacity(0.35);
							opcion.setStrokeColor(colorh);
							polygon.setPath(vertices);
							polygon.setOptions(opcion);
							anterior=vert;

						}

					}
				}
			}
		});
	}

	public static void mostrar() {
		final RoutesMap sample = new RoutesMap();

		JFrame frame = new JFrame("Ruta ida y vuelta");

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(sample, BorderLayout.CENTER);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
