/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import gestionproduit.utils.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class DataSource1 {
    private static DataSource1 instance;
    private Connection cnx;
    
    private final String URL ="jdbc:mysql://localhost:3306/pidev2";
    private final String LOGIN="root";
    private final String PASSWORD="";
    
    public DataSource1(){
        try {
            cnx= DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connected to BD");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public static DataSource1 getInstance(){
    
        if (instance == null){
        instance= new DataSource1();
        }
        return instance;
    }
    
    public Connection getcnx(){
    
        return cnx;
    }
    
}
