/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Produit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import models.lignecommande;
import utils.DataSource;

/**
 *
 * @author Asus
 */
public class servicepanier {
    
        Connection cnx = DataSource.getInstance().getcnx();

  /*  public void addProduitPanier(int product_id, int quantite, int user_id) {
        Statement st;

        try {
            st = cnx.createStatement();
            String rec2 = "SELECT product_id FROM lignecommande WHERE `product_id` = '" + product_id + "'";

            ResultSet rs = st.executeQuery(rec2);
            if (rs.next()) {
                System.err.println("product exists in cart");
            } else {
                try {
                    st = cnx.createStatement();
                    String rec = " INSERT INTO `cart`(`product_id`, `quantite`, `user_id`) VALUES ('" + product_id + "','" + quantite + "','" + user_id + "')";
                    st.executeUpdate(rec);
                    System.err.println("add successful");
                } catch (SQLException ex) {
                    Logger.getLogger(services_cart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(services_cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   /* public void delete_product_from_panier(int product_id, int activ_user_id) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("delete from cart where product_id=?  AND user_id=? ");
            pt.setInt(1, product_id);
            pt.setInt(2, activ_user_id);
            pt.executeUpdate();
            System.out.println("product deleted from cart");

        } catch (SQLException ex) {
            Logger.getLogger(services_cart.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void vider_panier(int user_id) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("delete from cart where user_id=? ");
            pt.setInt(1, user_id);
            pt.executeUpdate();
            System.out.println("cart vider");

        } catch (SQLException ex) {
            Logger.getLogger(services_cart.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update_panier_product_quantity(int user_id, int product_id, int quantity) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("UPDATE cart SET quantite =?  WHERE user_id =? AND product_id =? ");
            pt.setInt(1, quantity);
            pt.setInt(2, user_id);
            pt.setInt(3, product_id);
            pt.executeUpdate();
            System.out.println("quantite updated");

        } catch (SQLException ex) {
            Logger.getLogger(services_cart.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @return
     */
    /*public ObservableList<products> getInitialTableData() {
        String sql = "SELECT * FROM products";
        ObservableList<products> data = FXCollections.observableArrayList();
        try {
            PreparedStatement s = connecttoDb.getInstance().getCnx().prepareStatement(sql);
            s.executeQuery(sql);
            ResultSet res = s.executeQuery(sql);
            while (res.next()) {
                File file = new File(res.getString(7));
                Image imgg = new Image(file.toURI().toString());
                ImageView imgView = new ImageView(imgg);
                imgView.setFitWidth(200);
                imgView.setFitHeight(200);
                products p = new products(res.getInt(1), res.getString(2), res.getInt(3), res.getDouble(4), res.getString(5), res.getInt(6), res.getString(7));
                p.setImagev(imgView);
                data.add(p);
            }
            System.out.println(data);
        } catch (SQLException ex) {
            System.out.println("Error in Building Data");
        }
        return data;
    }

    public ObservableList<products> getInitialTableData_Cart(int user_id) {
        String sql = "SELECT products.product_name, products.img, products.price, cart.quantite, cart.product_id FROM products INNER JOIN cart ON products.product_id=cart.product_id WHERE cart.user_id = '" + user_id + "'";
        ObservableList<products> data = FXCollections.observableArrayList();
        try {
            PreparedStatement s = connecttoDb.getInstance().getCnx().prepareStatement(sql);
            s.executeQuery(sql);
            ResultSet res = s.executeQuery(sql);
            while (res.next()) {
                File file = new File(res.getString(2));
                Image imgg = new Image(file.toURI().toString());
                ImageView imgView = new ImageView(imgg);
                imgView.setFitWidth(200);
                imgView.setFitHeight(200);
                products p = new products(res.getInt(5), res.getString(1), 1, res.getDouble(3), "categorie", res.getInt(4), res.getString(2));
                p.setImagev(imgView);
                data.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Error in Building Data" + ex);
        }
        return data;
    }

    public double total_price_calcul(int activ_user_id) {
        double total_price = 0;
        final String sql = "SELECT products.price, cart.quantite FROM products INNER JOIN cart ON products.product_id=cart.product_id WHERE cart.user_id = '" + activ_user_id + "'";

        try (PreparedStatement stm = getC().prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                total_price = total_price + (rs.getDouble(1)*rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        System.out.println("totalprice "+total_price);
        return total_price;

    }

    /**
     * @return the c
     */
    /*public Connection getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
   /* public void setC(Connection c) {
        this.c = c;
    } /**/
    
}
