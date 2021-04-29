 package services;

import java.util.List;


public interface crud<T> {
	
	public void ajouter(T t);
    public void supprimer(int cin);
    public void modifier(T t,int i);
    public List<T> afficher();
	
}
