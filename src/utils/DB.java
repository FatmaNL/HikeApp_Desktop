package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utils.DB;

public class DB {
	
	private static DB instance;
    private Connection cnx;

    private final String URL = "jdbc:mysql://localhost:3306/pidev";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private DB() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
