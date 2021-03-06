package models;

import java.util.*;


import javax.persistence.*;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

import net.sf.oval.constraint.NotEmpty;

@Entity

public class User extends Model {

	@NotEmpty   
	@Required
	public String nom;

	@NotEmpty
	@Required
	public String prenom;
	@NotEmpty
	@Required
	public String motDePasse;

	@Temporal(TemporalType.DATE)
	public Date dateDeNaissance;

	public int nbFoyer = 1;

	@NotEmpty
	@Email
	@Required
	public String email;

	public Preference preference = Preference.Aucune;

	@OneToMany(mappedBy="user")
	public List<EtatFrigo> etatFrigo;

	@OneToMany(mappedBy="user")
	public List<ListeDeCourse> listeDeCourse;

	@ManyToMany(mappedBy="user")
	public List<Recette> recettesFavorites;

	public String toString() {
	    return email;
	}

	public static User connect(String email, String password) {
	    return find("byEmailAndMotDePasse", email, password).first();
	}

	public User(String nom, String prenom, String motDePasse, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.email = email;
	} 


      
}
