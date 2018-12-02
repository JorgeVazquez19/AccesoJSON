package com.company;


import org.hibernate.Query;
import org.hibernate.Session;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class Hibernate implements DataManager {

    Scanner sc = new Scanner(System.in);

    Session session;

    public Hibernate() {

        HibernateUtil util = new HibernateUtil();

        //session = util.getSessionFactory().openSession();

    }

    @Override
    public void metaDatosResultSet() throws IOException {
/*
       File descarga = new File("Series.ini");

        try {


            Query q = session.createQuery("SELECT s from Series s");
            List results = q.list();
            Iterator EmployeeIterator = results.iterator();

            while (EmployeeIterator.hasNext()) {
                Series serie = (Series) EmployeeIterator.next();
                System.out.println("   Id:" + serie.getID() + "   Nombre:" + serie.getNombre() +"   Descripcion:" + serie.getDescripcion()+"   Productora:" + serie.getProductora() + "   Temporadas:" + serie.getTemporadas() + "   ID Protagonista:" + serie.getProtagonistas().getID()  );
                for (int i = 1; i <= serie ; i++) {


               //     results.write(EmployeeIterator.getString(i) + "\n");

                    if (i == 5) {
//						bw.write("\n");
//						bw.write("\n");
                    }
                }
            }
            int a = 0;
            int b = 0;



            while (rset.next()) {

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
*/
    }

    @Override
    public void ficheroAbD(File file) throws SQLException, IOException {



    }

    @Override
    public void anadir() throws IOException {

    }

    @Override
    public void leer() {

        int opc = 0;
        Protagonistas prota = null;
        System.out.print("¿Que tabla quieres leer?\n");
        System.out.print("1.Series\n");
        System.out.print("2.Protagonistas");
        opc = sc.nextInt();
        if (opc == 1) {
            System.out.print("Antexs");
            System.out.print("despuessss");

            Query q = session.createQuery("SELECT s from Series s");
            List results = q.list();
            Iterator EmployeeIterator = results.iterator();

            while (EmployeeIterator.hasNext()) {
                Series serie = (Series) EmployeeIterator.next();
                System.out.println("   Id:" + serie.getID() + "   Nombre:" + serie.getNombre() +"   Descripcion:" + serie.getDescripcion()+"   Productora:" + serie.getProductora() + "   Temporadas:" + serie.getTemporadas() + "   ID Protagonista:" + serie.getProductora()  );
            }
        } else if (opc == 2) {
            System.out.print("Antexs");

            System.out.print("despuessss");


            Query q1 = session.createQuery("SELECT s from Protagonistas s");
            List results1 = q1.list();
            Iterator EmployeeIterator = results1.iterator();
            while (EmployeeIterator.hasNext()) {
                Protagonistas prota1 = (Protagonistas) EmployeeIterator.next();
                System.out.println("   Id:" + prota1.getID() + "   Nombre:" + prota1.getNombre() + "   Edad:" + prota1.getEdad() + "   Representante:" + prota1.getRepresentante());
            }
        }

    }

}
