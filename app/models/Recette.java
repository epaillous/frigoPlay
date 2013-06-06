package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;

import play.data.validation.Required;
import play.db.jpa.Model;

import java.net.URL;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Recette extends Model {


	@Required
	public String nom;
	
	public int difficulte;

	public int prix;

	public int calories;

	@Required
	public String lien;

	@ManyToMany(mappedBy="recette" )
	public Set<Aliment> ingredient;
	
	public String toString() {
	    return nom;
	}


}
