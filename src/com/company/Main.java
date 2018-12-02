package com.company;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main extends Conexion  {
	AccesoJson acceso = new AccesoJson();
    public static void main(String[] args) {
    	
        boolean bucle = false;
        File config = new File("Series.ini");
        File config2 = new File("Protagonistas.ini");

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            do {
                Scanner sc = new Scanner(System.in);
                System.out.println(
                        "�Que operacion quieres hacer? \n 1.Operaciones con BBDD.  \n 2.Operaciones con ficheros. \n 3.Operaciones con Hibernate. \n 4.Operaciones con JSON. \n 5.EXIT.");
                int opcion = 0;
                int opcion1=0;
                int opcion2 = 0;
                int opcion3 = 0;
                int opcion4 = 0;
                opcion = sc.nextInt();
                Conexion conexion = new Conexion();
                Ficheros ficheros = new Ficheros();
                Hibernate hibernate = new Hibernate();
                AccesoJson json = new AccesoJson();

                switch (opcion) {
                    case 1:
                        System.out.println(
                                "Escoge una opcion: \n 1.Leer datos de la BBDD. , \n 2. Añadir a la BBDD. , \n  3.Copiar de BD a Fichero. \n 4.Pasar de BBDD a Hibernate.");
                        opcion1=sc.nextInt();
                        switch (opcion1)
                        {
                            case 1 :
                                conexion.leer();
                                break;
                            case 2:
                                conexion.anadir();
                                break;
                            case 3:
                                conexion.metaDatosResultSet();
                                break;
                            case 4:

                                hibernate.leer();
                                break;
                        }
                            break;
                    case 2:
                        System.out.println(
                                "Escoge una opcion: \n 1.Leer datos de los fichero. , \n 2. Añadir datos a ficheros. , \n 3.Copiar de Fichero a BD. \n 4.Copiar file a Hibernate");
                        opcion2=sc.nextInt();
                        switch (opcion2)
                        {
                            case 1 :
                                ficheros.leer();
                                break;
                            case 2:
                                ficheros.anadir();
                                break;
                            case 3:
                                ficheros.borraDatos();
                                try {
                                    ficheros.ficheroAbD(config);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                hibernate.leer();
                                break;
                        }
                        break;
                    case 3:
                        System.out.println(
                                "Escoge una opcion: \n 1. Pasar de hibernate a BBDD. , \n 2. Pasar de hibernate a file");
                        opcion3 = sc.nextInt();
                        switch(opcion3){
                            case 1:

                                break;
                            case 2:
                                hibernate.metaDatosResultSet();
                                break;
                        }
                        break;

                    case 4:
                    	Intermediario intermediario = new Intermediario();
                    	intermediario.ejecucion();
                        break;
                    case 5:
                        System.exit(0);
                        break;

                }
            }while(bucle == false);
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            try {
                // Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();

            }

        }
        

    	
    }
 }