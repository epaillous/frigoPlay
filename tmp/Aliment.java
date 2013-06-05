package models;
import java.util.Set;

import java.util.Collection;
import java.util.Date;

import javax.persistence.ManyToMany;

import play.db.jpa.Model;


public class Aliment extends Model {
public Set<Recette> recettesPossibles;
   

	public String nom;
	public TypeAliment categorie;
	public String quantite; 
	public int calories;
	public Date peremption; //En 0000 si il n'est pas dans le frigo
}
