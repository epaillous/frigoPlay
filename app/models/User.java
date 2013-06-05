package models;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import play.data.validation.Email;
import play.db.jpa.Model;

import net.sf.oval.constraint.NotEmpty;

@Entity
public class User extends Model {


	@NotEmpty   
	public String nom;

	@NotEmpty
	public String prenom;
	@NotEmpty
	public String motDePasse;

	@Temporal(TemporalType.DATE)
	public Date dateDeNaissance;

	public int nbFoyer = 1;

	@Id
	@NotEmpty
	@Email
	public String email;

	public String preference = "nulle";

	@ManyToMany(mappedBy="user")
	public Set<EtatFrigo> etatFrigo;

	@OneToMany(mappedBy="user")
	public Set<ListeDeCourse> listeDeCourse;



   
      
   }
