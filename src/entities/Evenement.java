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
public class Evenement {
    private int id;
    private String nomevenement;
    private String depart;
    private String destination;
    private int nbparticipant;
    private String dateevenement;
    private int duree;
    private double prix;
    private String programme;
    private String contact;
    private String infos;
    private String type;
    private String circuit;
    private String image;
    private int idTransport;
 
   public Evenement() {
    }

    public Evenement(int id, String nomevenement, String depart, String destination, int nbparticipant, String dateevenement, int duree, double prix, String programme, String contact, String infos, String type, String circuit, String image, int idTransport) {
        this.id = id;
        this.nomevenement = nomevenement;
        this.depart = depart;
        this.destination = destination;
        this.nbparticipant = nbparticipant;
        this.dateevenement = dateevenement;
        this.duree = duree;
        this.prix = prix;
        this.programme = programme;
        this.contact = contact;
        this.infos = infos;
        this.type = type;
        this.circuit = circuit;
        this.image = image;
        this.idTransport = idTransport;
    }

    public Evenement(String nomevenement, String depart, String destination, int nbparticipant, String dateevenement, int duree, double prix, String programme, String contact, String infos, String type, String circuit, String image, int idTransport) {
        this.nomevenement = nomevenement;
        this.depart = depart;
        this.destination = destination;
        this.nbparticipant = nbparticipant;
        this.dateevenement = dateevenement;
        this.duree = duree;
        this.prix = prix;
        this.programme = programme;
        this.contact = contact;
        this.infos = infos;
        this.type = type;
        this.circuit = circuit;
        this.image = image;
        this.idTransport = idTransport;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomevenement() {
        return nomevenement;
    }

    public void setNomevenement(String nomevenement) {
        this.nomevenement = nomevenement;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNbparticipant() {
        return nbparticipant;
    }

    public void setNbparticipant(int nbparticipant) {
        this.nbparticipant = nbparticipant;
    }

    public String getDateevenement() {
        return dateevenement;
    }

    public void setDateevenement(String dateevenement) {
        this.dateevenement = dateevenement;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(int idTransport) {
        this.idTransport = idTransport;
    }

    @Override
    public String toString() {
        return "Evenement{" + "nomevenement=" + nomevenement + ", depart=" + depart + ", destination=" + destination + ", nbparticipant=" + nbparticipant + ", dateevenement=" + dateevenement + ", duree=" + duree + ", prix=" + prix + ", programme=" + programme + ", contact=" + contact + ", infos=" + infos + ", type=" + type + ", circuit=" + circuit + '}';
    }
    
    
    
}
