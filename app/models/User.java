package models;
 
import play.*;
import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.*;
 
import javax.persistence.*;

import java.util.*;
 

@Entity
public class User extends Model {

	@Email
	@Required
	public String email;
	@Required
	public String nom;
	@Required
	public String prenom;
	@Required
	public String motDePasse;
	public int nbFoyer;
	public String preference;

	@Temporal(TemporalType.DATE)
	public Date dateDeNaissance;

	@ManyToMany
	public Collection<EtatFrigo> etatsFrigo;
	@OneToMany
	public Collection<ListeCourses> listesCourses;
	
	@ElementCollection 
	@ManyToMany
	public Collection<Recette> recettesFavorites;
	

	public User(long id, String email, String nom, String prenom,
			String motDePasse, int nbFoyer, String preference,
			Date dateDeNaissance) {
		super();
		this.id = id;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.nbFoyer = nbFoyer;
		this.preference = preference;
		this.dateDeNaissance = dateDeNaissance;
	}

	public String toString() {
	        return email;
	    }
	 
	 public static User connect(String email, String motDePasse) {
		    return find("byEmailAndPassword", email, motDePasse).first();
		}
		
 	
}