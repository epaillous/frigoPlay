package models;

import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToMany;

public class Recette {

	public String nom;
	public String difficulte;
	public String prix;
	public int calories;
	public String lien;
	
	@ElementCollection 
	@ManyToMany
	public Collection<Aliment> alimentsNecessaires;
	
}
