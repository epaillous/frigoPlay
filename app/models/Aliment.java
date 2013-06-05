package models;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

import net.sf.oval.constraint.NotEmpty;

import play.db.jpa.Model;

@Entity
public class Aliment extends Model {

	@Id
	@NotEmpty
	public String nom;

	public String quantite;

	public int calories;

	public Date peremption;

	@OneToMany
	public Set<ListeDeCourse> listeDeCourse;


	@OneToMany	
	public Set<Recette> recette;



	@ManyToOne(optional=false)
	public EtatFrigo etatFrigo;


   
   }
