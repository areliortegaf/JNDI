/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * Implementacion mas usada de JNDI con JDBC en JAVA EE.
 * Para su implementacion primero se debio haber creado la db, y esquema oracle en services
 * Derby Conn, archivo.sql 
 */
public class Main {
    
    public Main(){
        System.out.println(jndi());
    }
    
    
    public String queryDatabase(){
        String outStr ="";
        try{
            Connection con =  DriverManager.getConnection("jdbc:derby://localhost:1527/LoginDb","oracle","oracle");
            PreparedStatement st = con.prepareStatement("SELECT * FROM STUDENTS");
            ResultSet rs = st.executeQuery();{
            int rowNum = 1;//contador para saber cuantos registros tiene
            //ITERADOR
            while (rs.next()){
                //rs es la variable que sostiene el resultado del query
                outStr += " ------------------------ " + rowNum++ + "\n";
                outStr += " Numero de Estudiante " + rs.getString(1) + "\n";
                outStr += " Apellido " + rs.getString(2) + "\n";//3 = numero de columna
                outStr += " Nombre " + rs.getString(3) + " \n";
                outStr += "  \n";
                
            }
        }
            
        }catch(Exception  e){
            e.printStackTrace();
        }
        return outStr;
    }
    
    
    public String jndi(){
        //MANERA DE HACERLO CON JNDI, CORREGIR DATASOURCE.LOOKUP
        String outStr ="";
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/login");
            Connection con = ds.getConnection();
            
            PreparedStatement stm = con.prepareStatement("SELECT * FROM STUDENTS");
            ResultSet rs = stm.executeQuery();
            
            //Statement st = con.createStatement();
            //ResultSet rs = st.executeQuery("SELECT * FROM STUDENTS");
            //CONTADOR
            int rowNum = 1;
            //ITERADOR
            while (rs.next()){
                //rs es la variable que sostiene el resultado del query
                outStr += " ------------------------ " + rowNum++ + "\n";
                outStr += " Numero de Estudiante " + rs.getString(1) + "\n";
                outStr += " Apellido " + rs.getString(2) + "\n";//3 = numero de columna
                outStr += " Nombre " + rs.getString(3) + " \n";
                outStr += "  \n";
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return outStr;
    }
    
    public static void main(String[] args) {
        new Main();
    }
}
