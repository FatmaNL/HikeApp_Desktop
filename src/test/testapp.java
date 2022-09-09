/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Transport;
import services.ServiceTransport;
import utils.DataSource;

/**
 *
 * @author Fatma NL
 */
public class testapp {
    public static void main (String[] args){
        //DataSource ds = DataSource.getInstance();
        ServiceTransport t = new ServiceTransport();
        t.ajouter(new Transport("trtest", 28,1));
        t.getList().forEach(System.out::println);
    }
    
}
