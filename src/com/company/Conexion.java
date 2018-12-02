package com.company;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Conexion implements DataManager{
    private String url;
    private String bd;
    private String login;
    private String pwd;
    private Connection conexion;
    Scanner sc = new Scanner(System.in);
    BufferedWriter bw = null;
    FileWriter fw = null;
    File file = new File("Series.ini");

    // Constructor que crea la conexión
    public Conexion() {
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
         //   System.out.println(" - Conexión con MySQL establecida -");
        } catch (Exception e) {
            System.out.println(" – Error de Conexión con MySQL -");
            e.printStackTrace();
        }
    }





    public void leer() {


        int opc = 0;
        System.out.print("¿Que tabla quieres leer?\n");
        System.out.print("1.Series\n");
        System.out.print("2.Protagonistas");
        opc = sc.nextInt();
        if (opc == 1) {
            try {
                String query = "SELECT * from series";
                Statement stmt = conexion.createStatement();
                ResultSet rset = stmt.executeQuery(query);
                // System.out.println("HOST - USER");
                while (rset.next()) {
                    System.out.print(rset.getString(1));
                    System.out.println(" - " + rset.getString(2));
                    System.out.println(" - " + rset.getString(3));
                    System.out.println(" - " + rset.getString(4));
                    System.out.println(" - " + rset.getString(5));
                    System.out.println(" - " + rset.getString(6));
                }
                rset.close();
                stmt.close();
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
        else if(opc ==2)
        {
            try {
                String query = "SELECT * from protagonistas";
                Statement stmt = conexion.createStatement();
                ResultSet rset = stmt.executeQuery(query);
                // System.out.println("HOST - USER");
                while (rset.next()) {
                    System.out.print(rset.getString(1));
                    System.out.println(" - " + rset.getString(2));
                    System.out.println(" - " + rset.getString(3));
                    System.out.println(" - " + rset.getString(4));
                }
                rset.close();
                stmt.close();
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
    }




    public void anadir() {

        int opc = 0;
        System.out.println("¿Que quieres añadir?\n");
        System.out.println("1.Introduce serie\n");
        System.out.println("2.Introduce protagonista\n");

        opc = sc.nextInt();
        if (opc == 1) {


        System.out.println("Pulsa Enter para añadir");
        sc.nextLine();
        System.out.println("Introduce ID:");
        String ID1 = sc.nextLine();
        System.out.println("Introduce Nombre:");
        String Nombre1 = sc.nextLine();
        System.out.println("Introduce Descripción:");
        String Desc1 = sc.nextLine();
        System.out.println("Introduce Productora:");
        String Productora1 = sc.nextLine();
        System.out.println("Introduce número de temporadas:");
        String Temp1 = sc.nextLine();
        System.out.println("Introduce el protagonista:");
            System.out.println("Selecciona Protagonistas");
        leer();
        System.out.println("Introduce el id del protagonista que quieras:");
        int id_protagonista=sc.nextInt();
        Statement stmt;
        int r = 0;
        try {
            stmt = conexion.createStatement();
            String catSQL = null;
            catSQL = "Insert into series values (" + ID1 + ", '" + Nombre1 + "', '" + Desc1 + "','" + Productora1 + "','"
                    + Temp1 + "','"+ id_protagonista +"')";
            r = stmt.executeUpdate(catSQL);

        } catch (SQLException e) {
            e.printStackTrace();
            }
        }
        else if(opc==2)
        {
            System.out.println("Pulsa Enter para añadir");
            sc.nextLine();
            System.out.println("Introduce ID:");
            String ID2 = sc.nextLine();
            System.out.println("Introduce Nombre:");
            String Nombre2 = sc.nextLine();
            System.out.println("Introduce Edad:");
            String Edad1 = sc.nextLine();
            System.out.println("Introduce Representante:");
            String Representante1 = sc.nextLine();
            Statement stmt;
            int r = 0;
            try {
                stmt = conexion.createStatement();
                String catSQL = null;
                catSQL = "Insert into protagonistas values (" + ID2 + ", '" + Nombre2 + "', '" + Edad1 + "','" + Representante1 + "')";
                r = stmt.executeUpdate(catSQL);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public void metaDatosResultSet() throws IOException {

        File descarga = new File("Series.ini");

        try {

            BufferedWriter bw = null;
            FileWriter fw = null;

            fw = new FileWriter(descarga.getAbsoluteFile(), false);
            bw = new BufferedWriter(fw);

            String sql = "Select * from series";

            PreparedStatement pstm;
            pstm = conexion.prepareStatement(sql);
            ResultSet rset = pstm.executeQuery();
            ResultSetMetaData rsmd = rset.getMetaData();
            int numColumns = rsmd.getColumnCount();
            int a = 0;
            int b = 0;
//			for (int j = 1; j <= numColumns; j++) {
//				bw.write(rsmd.getColumnName(j) + "\n");
//				if (j == 5) {
////					bw.write("\n");
////					bw.write("\n");
//				}
//			}
            //System.out.println();
            while (rset.next()) {
                for (int i = 1; i <= numColumns; i++) {


                        bw.write(rset.getString(i) + "\n");

                    if (i == 5) {
//						bw.write("\n");
//						bw.write("\n");
                    }
                }
              //  System.out.println();
            }
            bw.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("Se ha copiado series");
        File descarga1 = new File("protagonistas.ini");

        try {

            BufferedWriter bw1 = null;
            FileWriter fw1 = null;

            fw1 = new FileWriter(descarga1.getAbsoluteFile(), false);
            bw1 = new BufferedWriter(fw1);

            String sql1 = "Select * from protagonistas";

            PreparedStatement pstm;
            pstm = conexion.prepareStatement(sql1);
            ResultSet rset = pstm.executeQuery();
            ResultSetMetaData rsmd = rset.getMetaData();
            int numColumns = rsmd.getColumnCount();
            int a = 0;
            int b = 0;
//			for (int j = 1; j <= numColumns; j++) {
//				bw.write(rsmd.getColumnName(j) + "\n");
//				if (j == 5) {
////					bw.write("\n");
////					bw.write("\n");
//				}
//			}
            //System.out.println();
            while (rset.next()) {
                for (int i = 1; i <= numColumns; i++) {
                        bw1.write(rset.getString(i) + "\n");

                    if (i == 4) {
//						bw.write("\n");
//						bw.write("\n");
                    }
                }
                System.out.println();
            }
            bw1.close();
            System.out.println("Se ha copiado protas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ficheroAbD(File file) throws SQLException, IOException {

    }






    public static void main(String[] args) {
        Conexion prueba = new Conexion();

    }
}