/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Fatma NL
 */
public class Transport {
    private int idtransport;
    private String type;
    private int volumemax;
    private int nombre_transports;
    
    public Transport(int id, String type, int volumemax, int nombre_transports){
        this.idtransport=id;
        this.type=type;
        this.volumemax=volumemax;
        this.nombre_transports=nombre_transports;
    }
    
    public Transport(String type, int volumemax, int nombre_transports){
        this.type=type;
        this.volumemax=volumemax;
        this.nombre_transports=nombre_transports;
    }

    public int getId() {
        return idtransport;
    }

    public void setId(int id) {
        this.idtransport = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVolumemax() {
        return volumemax;
    }

    public void setVolumemax(int volumemax) {
        this.volumemax = volumemax;
    }

    public int getNombre_transports() {
        return nombre_transports;
    }

    public void setNombre_transports(int nombre_transports) {
        this.nombre_transports = nombre_transports;
    }

    @Override
    public String toString() {
        return "Transport{" + "id=" + idtransport + ", type=" + type + ", volumemax=" + volumemax + ", nombre_transports=" + nombre_transports + '}';
    }

    public Transport() {
    }
    
    
    
}
