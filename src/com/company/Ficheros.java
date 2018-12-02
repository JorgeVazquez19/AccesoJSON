package com.company;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Ficheros implements DataManager{
    private String url;
    private String bd;
    private String login;
    private String pwd;
    BufferedWriter bw = null;
    FileWriter fw = null;
    BufferedWriter bw1 = null;
    FileWriter fw1 = null;
    File file = new File("Series.ini");
    File file1 = new File("Protagonistas.ini");
    private Connection conexion;
    Scanner sc = new Scanner(System.in);

    public Ficheros()
    {
        Properties p = new Properties();
        FileInputStream bbdd = null;
        try {
            bbdd = new FileInputStream("bbdd.ini");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            p.load(bbdd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = p.getProperty("url");
        bd = p.getProperty("bd");
        login = p.getProperty("login");
        pwd = p.getProperty("pwd");
        try {


            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, login, pwd);
            // Quitamos esta instrucción: conexion.close();
            //System.out.println(" - Conexión con MySQL establecida -");
        } catch (Exception e) {
            System.out.println(" – Error de Conexión con MySQL -");
            e.printStackTrace();
        }

    }


    public void leer() {

        int opcion = 0;
        System.out.print("¿Que archivo quieres leer?\n");
        System.out.print("1.Protagonistas\n");
        System.out.print("2.Series\n");
        opcion = sc.nextInt();
        if (opcion == 1) {

            File config = new File("Protagonistas.ini");
            int i;
            // System.out.println(config.getAbsolutePath());
            System.out.println("");
            if (config.exists()) {
                try {
                    InputStream mifichero = new FileInputStream(config);
                    i = mifichero.read();
                    while (i != -1) {
                        if (i == '\n')
                            System.out.print("");
                        System.out.print((char) i);
                        i = mifichero.read();

                    }
                } catch (IOException e) {
                    System.out.print("Error de Entrada/Salida");
                }
            } else
                System.out.println("El fichero no existe");

        } else if (opcion == 2) {


            File config = new File("Series.ini");
            int i;
            // System.out.println(config.getAbsolutePath());
            System.out.println("");
            if (config.exists()) {
                try {
                    InputStream mifichero = new FileInputStream(config);
                    i = mifichero.read();
                    while (i != -1) {
                        if (i == '\n')
                            System.out.print("");
                        System.out.print((char) i);
                        i = mifichero.read();

                    }
                } catch (IOException e) {
                    System.out.print("Error de Entrada/Salida");
                }
            } else
                System.out.println("El fichero no existe");
        }
    }

    public void anadir() throws IOException {
        // flag true, indica adjuntar información al archivo.
       // File file = new File("Series.ini").
        int option = 0;
        System.out.println("Pulsa 1 para añadir una Serie u 2 para añadir un protagonista");
        option = sc.nextInt();
        if(option == 1) {


            System.out.println("Pulsa enter para añadir");
            sc.nextLine();
            System.out.println("Introduce ID:");
            String ID = sc.nextLine();
            System.out.println("Introduce Nombre:");
            String Nombre = sc.nextLine();
            System.out.println("Introduce Descripción:");
            String Desc = sc.nextLine();
            System.out.println("Introduce Productora:");
            String Productora = sc.nextLine();
            System.out.println("Introduce número de temporadas:");
            String Temp = sc.nextLine();


            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write("\n" + ID);
            bw.write("\n" + Nombre);
            bw.write("\n" + Desc);
            bw.write("\n" + Productora);
            bw.write("\n" + Temp);

            bw.close();

            System.out.println("información 1 agregada!");
        }
        else if(option == 2) {
            System.out.println("Pulsa enter para añadir");
            sc.nextLine();
            System.out.println("Introduce ID:");
            String ID1 = sc.nextLine();
            System.out.println("Introduce Nombre:");
            String Nombre1 = sc.nextLine();
            System.out.println("Introduce Edad:");
            String Edad = sc.nextLine();
            System.out.println("Introduce Representante:");
            String Representante = sc.nextLine();


            fw1 = new FileWriter(file1.getAbsoluteFile(), true);
            bw1 = new BufferedWriter(fw1);

            bw1.write("\n" + ID1);
            bw1.write("\n" + Nombre1);
            bw1.write("\n" + Edad);
            bw1.write("\n" + Representante);

            bw1.close();

            System.out.println("información 2 agregada!");
        }
    }

    @Override
    public void metaDatosResultSet() {

    }



    public void ficheroAbD(File file) throws SQLException, IOException {
        // File ini = new File("Series.ini");

        String chain1;
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        int cont = 0;
        int r1 = 0;

        String a1 = "";
        String b1 = "";
        String c1 = "";
        String d1 = "";


        ArrayList<String> info1 = new ArrayList<String>();

        FileReader fr1 = new FileReader(file1);
        BufferedReader br1= new BufferedReader(fr1);

        int cont1 = 0;


        while ((chain1 = br1.readLine()) != null) {

            info1.add(chain1);
            cont1++;
            //System.out.println(cont);
        }

        for (int i = 0; i < info1.size(); i += 4) {
            cont++;
            try {
                a1 = info1.get(i);
                b1 = info1.get(i + 1);
                c1 = info1.get(i + 2);
                d1 = info1.get(i + 3);
                System.out.println(a1 + b1 + c1 +d1);

                String sql1 = "INSERT INTO protagonistas(ID, Nombre, Edad, Representante) VALUES (?,?,?,?)";
                PreparedStatement stmt1 = conexion.prepareStatement(sql1);
                stmt1.setString(1, a1);
                stmt1.setString(2, b1);
                stmt1.setString(3, c1);
                stmt1.setString(4, d1);

                System.out.println(stmt1.toString());

                r1 += stmt1.executeUpdate();
                System.out.println(r1);

            } catch (SQLException t) {
                t.printStackTrace();
            }

        }
        String chain;

        int r = 0;

        String a = "";
        String b = "";
        String c = "";
        String d = "";
        String e = "";
        String f = "";

        ArrayList<String> info = new ArrayList<String>();




        while ((chain = br.readLine()) != null) {

            info.add(chain);
            cont++;
            //System.out.println(cont);
        }

        for (int i = 0; i < info.size(); i += 6) {
            cont++;
            try {
                a = info.get(i);
                b = info.get(i + 1);
                c = info.get(i + 2);
                d = info.get(i + 3);
                e = info.get(i + 4);
                f = info.get(i + 5);
                System.out.println(a + b + c +d +e+f);

                String sql = "INSERT INTO series(ID, Nombre, Descripcion, Productora, Temporadas,id_protagonistas) VALUES (?,?,?,?,?,?)";
                PreparedStatement stmt = conexion.prepareStatement(sql);
                stmt.setString(1, a);
                stmt.setString(2, b);
                stmt.setString(3, c);
                stmt.setString(4, d);
                stmt.setString(5, e);
                stmt.setString(6, f);


                r += stmt.executeUpdate();
                System.out.println(r);

            } catch (SQLException t) {
                t.printStackTrace();
            }
        }



    }

    public int borraDatos() {
        PreparedStatement pstm;
        PreparedStatement pstm2;
        int r = 0;
        try {
            pstm = conexion.prepareStatement("Delete from series");
            //pstm.setInt(1, numero);
            //ResultSet rset = pstm.executeQuery();
            r = pstm.executeUpdate();

            pstm2 = conexion.prepareStatement("Delete from protagonistas");
            //pstm.setInt(1, numero);
            //ResultSet rset = pstm.executeQuery();
            r = pstm2.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }


}
