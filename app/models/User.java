package models;

import java.util.Date;
import java.util.Set;

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

	public String preference = "nulle";

	@ManyToMany(mappedBy="user")
	public Set<EtatFrigo> etatFrigo;

	@OneToMany(mappedBy="user")
	public Set<ListeDeCourse> listeDeCourse;
	
	public String toString() {
	    return email;
	}



   
      
   }
