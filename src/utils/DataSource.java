/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author FatmaNL
 */
public class DataSource {


    private Connection cnx;

    // faut faire attention au port
    // serverTimezone est obligatoire
    // c'est un projet fx, faut avoir un main fx
    private final String URL = "jdbc:mysql://localhost:3306/pidev?serverTimezone=UTC";
    private final String LOGIN = "root";
    private final String PASSWORD = "";
    private static DataSource instance;

    private DataSource() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
