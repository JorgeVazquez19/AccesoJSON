package com.company;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class AccesoJson {

    ApiRequest encargadoPeticiones;
    private String SERVER_PATH, GET_SERIES, SET_SERIES,GET_PROTAGONISTAS,SET_PROTAGONISTAS; // Datos de la conexion

    public AccesoJson() {

        encargadoPeticiones = new ApiRequest();

        SERVER_PATH ="http://localhost/JorgeVazquez2/SeriesJSONServer/";
        GET_SERIES = "leeSeries.php";
        SET_SERIES = "escribirSeries.php";
        GET_PROTAGONISTAS = "leeProtagonistas.php";
        SET_PROTAGONISTAS = "escribirProtagonistas.php";

    }
    public HashMap<Integer, Protagonistas> leeProtagonistas() {

        HashMap<Integer, Protagonistas> auxhm = new HashMap<Integer, Protagonistas>();

        try {

            System.out.println("---------- Leemos datos de JSON --------------------");

            System.out.println("Lanzamos peticion JSON para jugadores");

            String url = SERVER_PATH + GET_PROTAGONISTAS; // Sacadas de configuracion
            System.out.println(url);
            // System.out.println("La url a la que lanzamos la petición es " +
            // url); // Traza para pruebas

            String response = encargadoPeticiones.getRequest(url);

            //System.out.println(response); // Traza para pruebas

            // Parseamos la respuesta y la convertimos en un JSONObject
            JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

            if (respuesta == null) { // Si hay algún error de parseo (json
                // incorrecto porque hay algún caracter
                // raro, etc.) la respuesta será null
                System.out.println("El json recibido no es correcto. Finaliza la ejecución");
                System.exit(-1);
            } else { // El JSON recibido es correcto
                // Sera "ok" si todo ha ido bien o "error" si hay algún problema
                String estado = (String) respuesta.get("estado");
                // Si ok, obtenemos array de jugadores para recorrer y generar hashmap
                if (estado.equals("ok")) {
                    JSONArray array = (JSONArray) respuesta.get("protagonistas");

                    if (array.size() > 0) {

                        // Declaramos variables
                        Protagonistas nuevoProtagonistas;
                        String nombre;
                        int id;
                        String representante;
                        int edad;

                        for (int i = 0; i < array.size(); i++) {
                            JSONObject row = (JSONObject) array.get(i);

                            nombre = row.get("Nombre").toString();
                            id = Integer.parseInt(row.get("ID").toString());
                            representante = row.get("Representante").toString();
                            edad = Integer.parseInt(row.get("Edad").toString());
                           
                            nuevoProtagonistas = new Protagonistas(id, nombre, edad, representante);

                            auxhm.put(id, nuevoProtagonistas);
                        }

                        System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
                        System.out.println();

                    } else { // El array de jugadores está vacío
                        System.out.println("Acceso JSON Remoto - No hay datos que tratar");
                        System.out.println();
                    }

                } else { // Hemos recibido el json pero en el estado se nos
                    // indica que ha habido algún error

                    System.out.println("Ha ocurrido un error en la busqueda de datos");
                    System.out.println("Error: " + (String) respuesta.get("error"));
                    System.out.println("Consulta: " + (String) respuesta.get("query"));

                    System.exit(-1);

                }
            }

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error en la busqueda de datos");

            e.printStackTrace();

            System.exit(-1);
        }

        return auxhm;
    }

    public HashMap<Integer, Series> lee() {

        HashMap<Integer, Series> auxhm = new HashMap<Integer, Series>();

        try {

            System.out.println("---------- Leemos datos de JSON --------------------");

            System.out.println("Lanzamos peticion JSON para jugadores");

            String url = SERVER_PATH + GET_SERIES; // Sacadas de configuracion
            System.out.println(url);
            // System.out.println("La url a la que lanzamos la petición es " +
            // url); // Traza para pruebas

            String response = encargadoPeticiones.getRequest(url);

            //System.out.println(response); // Traza para pruebas

            // Parseamos la respuesta y la convertimos en un JSONObject
            JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

            if (respuesta == null) { // Si hay algún error de parseo (json
                // incorrecto porque hay algún caracter
                // raro, etc.) la respuesta será null
                System.out.println("El json recibido no es correcto. Finaliza la ejecución");
                System.exit(-1);
            } else { // El JSON recibido es correcto
                // Sera "ok" si todo ha ido bien o "error" si hay algún problema
                String estado = (String) respuesta.get("estado");
                // Si ok, obtenemos array de jugadores para recorrer y generar hashmap
                if (estado.equals("ok")) {
                    JSONArray array = (JSONArray) respuesta.get("series");

                    if (array.size() > 0) {

                        // Declaramos variables
                        Series nuevaSerie;
                        String nombre;
                        int id;
                        String descripcion;
                        String productora;
                        int temporadas;
                        int id_protagonistas;

                        for (int i = 0; i < array.size(); i++) {
                            JSONObject row = (JSONObject) array.get(i);

                            nombre = row.get("nombre").toString();
                            id = Integer.parseInt(row.get("id").toString());
                            descripcion = row.get("descripcion").toString();
                            temporadas = Integer.parseInt(row.get("temporadas").toString());
                            productora = row.get("productora").toString();
                            id_protagonistas = Integer.parseInt(row.get("id_protagonistas").toString());
                            
                            nuevaSerie = new Series(id, nombre, descripcion, productora, temporadas, id_protagonistas);

                            auxhm.put(id, nuevaSerie);
                        }

                        System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
                        System.out.println();

                    } else { // El array de jugadores está vacío
                        System.out.println("Acceso JSON Remoto - No hay datos que tratar");
                        System.out.println();
                    }

                } else { // Hemos recibido el json pero en el estado se nos
                    // indica que ha habido algún error

                    System.out.println("Ha ocurrido un error en la busqueda de datos");
                    System.out.println("Error: " + (String) respuesta.get("error"));
                    System.out.println("Consulta: " + (String) respuesta.get("query"));

                    System.exit(-1);

                }
            }

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error en la busqueda de datos");

            e.printStackTrace();

            System.exit(-1);
        }

        return auxhm;
    }
    public void anadirSerieJSON(Series auxSerie) {

		try {
			JSONObject objSerie = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objSerie.put("nombre", auxSerie.getNombre());
			objSerie.put("descripcion", auxSerie.getDescripcion());
			objSerie.put("productora", auxSerie.getProductora());
			objSerie.put("temporadas", auxSerie.getTemporadas());
			objSerie.put("id_protagonistas", auxSerie.getProtagonistas());

			// Tenemos el jugador como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "add");
			objPeticion.put("seriesAnnadir", objSerie);
			
			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para almacenar una serie");

			String url = SERVER_PATH + SET_SERIES;

			//System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			//System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);
			
			System.out.println("El json que recibimos es: ");
			
			System.out.println(response); // Traza para pruebas
			System.exit(-1);
			
			// Parseamos la respuesta y la convertimos en un JSONObject
			

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				
				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado"); 
				if (estado.equals("ok")) {

					System.out.println("Almacenado serie enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirSerie' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}

	}
    public void anadirProtagonistasJSON(Protagonistas auxProtagonistas) {

		try {
			JSONObject objProtagonistas = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objProtagonistas.put("nombre", auxProtagonistas.getNombre());
			objProtagonistas.put("edad", auxProtagonistas.getEdad());
			objProtagonistas.put("representante", auxProtagonistas.getRepresentante());

			// Tenemos el jugador como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "add");
			objPeticion.put("protagonistasAnnadir", objProtagonistas);
			
			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para almacenar una serie");

			String url = SERVER_PATH + SET_PROTAGONISTAS;

			//System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			//System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);
			
			System.out.println("El json que recibimos es: ");
			
			System.out.println(response); // Traza para pruebas
			System.exit(-1);
			
			// Parseamos la respuesta y la convertimos en un JSONObject
			

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());
			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				
				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado"); 
				if (estado.equals("ok")) {

					System.out.println("Almacenado serie enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirProtagonistas' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}

	}

}
