package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.SessionFactory;

import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.CrpytDecPassword;
import utils.DB;



public class CrudUser implements crud<User> {
	Connection Conn;
	Connection cnx = DB.getInstance().getCnx();
	
	public String getpassbymail(String email) {
        try {
            String requete = "select password from user where email=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException ex) {
        	System.err.println(ex.getMessage());
        }
        return "";

    }
	
	public boolean login(String email, String password) {
		String pass = this.getpassbymail(email);
        try {
            String requete = "select * from user where email=? and password=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (BCrypt.checkpw(password, pass)) {
            	
            	System.out.println(" you are logged");
                return true;
            }
        } catch (SQLException ex) {
        	System.out.println(" you are not logged");
            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
	
	public String getRolebyCin(int cin) {
		try {
            String requete = "SELECT roles FROM user where cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, cin);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               return rs.getString("roles");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return "";
    }
	
	public void setNewMotPass(int id ,String pass){
	        try {
	        	
	            String req = "UPDATE `user` SET `password` ='" + pass + "' WHERE `user`.`cin` = "+id;
	            PreparedStatement pst = cnx.prepareStatement(req);
	            pst.executeUpdate();
	             
	        } catch (SQLException ex) {
	            Logger.getLogger(CrudUser.class.getName()).log(Level.SEVERE, null, ex);
	        }  
	        
	    }
	
	public String getStatusbyCin(int cin) {
		try {
            String requete = "SELECT status FROM user where cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, cin);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               return rs.getString("status");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return "";
    }
	
	public String getPassbyCin(int cin) {
		try {
            String requete = "select password from user where cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, cin);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException ex) {
        	System.err.println(ex.getMessage());
        }
        return "";
    }
	
	public String getTelbyCin(int cin) {
		try {
            String requete = "select tel from user where cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, cin);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                return rs.getString("tel");
            }
        } catch (SQLException ex) {
        	System.err.println(ex.getMessage());
        }
        return "";
    }
	
	public int getCinbymail(String email) {
        try {
            String requete = "select cin from user where email=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
        	System.err.println(ex.getMessage());
        }
        return 0;

    }
	
	public int getCinbynom(String nom) {
        try {
            String requete = "select cin from user where nom=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, nom);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
        	System.err.println(ex.getMessage());
        }
        return 0;

    }
	
	public String getNombyCin(int cin) {
		try {
            String requete = "select nom from user where cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, cin);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                return rs.getString("nom");
            }
        } catch (SQLException ex) {
        	System.err.println(ex.getMessage());
        }
        return "";

    }
	
	public String getPrenombyCin(int cin) {
		try {
            String requete = "select prenom from user where cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, cin);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                return rs.getString("prenom");
            }
        } catch (SQLException ex) {
        	System.err.println(ex.getMessage());
        }
        return "";

    }
	
	public String getMailbyCin(int cin) {
		try {
            String requete = "select email from user where cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, cin);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException ex) {
        	System.err.println(ex.getMessage());
        }
        return "";

    }
	
	@Override
    public void ajouter(User t) {
        try {
            String requete = "INSERT INTO user (cin,nom,prenom,age,sexe,adresse,tel,email,password,roles,status) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getCin());
            pst.setString(2, t.getNom());
            pst.setString(3, t.getPrenom());
            pst.setInt(4, t.getAge());
            pst.setString(5, t.getSexe());
            pst.setString(6, t.getAdresse());
            pst.setInt(7, t.getTel());
            pst.setString(8, t.getEmail());
            pst.setString(9, t.getPassword());
            pst.setString(10, t.getRoles());
            pst.setString(11, t.getStatus());
            pst.executeUpdate();
            System.out.println("Utilisateur ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int cin) {
        try {
            String requete = "DELETE FROM user WHERE cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, cin);
            pst.executeUpdate();
            System.out.println("Utilisateur supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User t,int cin) {
        try {
            String requete = "UPDATE user SET nom=?,prenom=?,age=?,sexe=?,adresse=?,tel=?,email=?,roles=?,status=?,cin=? WHERE cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(11, cin);
            pst.setInt(10, t.getCin());
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setInt(3, t.getAge());
            pst.setString(4, t.getSexe());
            pst.setString(5, t.getAdresse());
            pst.setInt(6, t.getTel());
            pst.setString(7, t.getEmail());
            pst.setString(8, t.getRoles());
            pst.setString(9, t.getStatus());
            pst.executeUpdate();
            System.out.println("Utilisateur modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void modifieruser(User t,int cin) {
        try {
            String requete = "UPDATE user SET nom=?,prenom=?,tel=?,email=?,password=? WHERE cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(6, cin);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setInt(3, t.getTel());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getPassword());
            pst.executeUpdate();
            System.out.println("Utilisateur modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public ObservableList<User> afficher() {
        ObservableList<User> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM user";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),
                		rs.getString(9),rs.getString(10),rs.getString(11)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
}
