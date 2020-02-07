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
import com.teamdev.jxmaps.swing.MapView;

import controller.Controller;
import model.data_structures.grafo.VerticeNoExisteException;
import model.vo.VerticeLatLongServicios;
import view.TaxiTripsManagerView;

import javax.swing.*;
import java.awt.*;

/**
 * This example demonstrates how to create and customize a Marker on the Map.
 *
 * @author Vitaly Eremenko
 */
public class MarkerMap extends MapView {
	public MarkerMap() {
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
					VerticeLatLongServicios vert = null;			
					vert = TaxiTripsManagerView.darVerticeCongestionado();

					// Setting map type control options
					options.setMapTypeControlOptions(controlOptions);
					// Setting map options
					map.setOptions(options);
					// Setting the map center
					map.setCenter(new LatLng(vert.darLatitud(), vert.darLongitud()));
					// Setting initial zoom value
					map.setZoom(9.0);
					// Creating a new marker object
					Marker marker = new Marker(map);
					// Setting marker position
					marker.setPosition(new LatLng(vert.darLatitud(), vert.darLongitud()));
					// Creating info window, that will be initially displayed on the marker

				}
			}
		});
	}

	public static void mostrar() {
		final MarkerMap sample = new MarkerMap();

		JFrame frame = new JFrame("Vertice Mas Congestionado");

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(sample, BorderLayout.CENTER);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
