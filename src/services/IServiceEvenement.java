/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import javafx.collections.ObservableList;

/**
 *
 * @author Fatma NL
 * @param <obj>
 */
public interface IServiceEvenement<obj> {
    public void ajouter(obj x);
    public void supprimer(obj x);
    public void modifier(obj x);
    public ObservableList<Evenement> getList();
    
}
