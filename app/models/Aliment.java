package models;

import java.util.*;

import javax.persistence.*;

import net.sf.oval.constraint.NotEmpty;

import play.db.jpa.Model;

@Entity
public class Aliment extends Model {


	@NotEmpty
	public String nom;

	public String quantite;

	public int calories;

	public Date peremption;
	
	public Date entreeFrigo;

	public Aliment(String nom, String quantite, int calories, Date peremption, Date entreeFrigo, EtatFrigo etatFrigo, Section section) {
		super();
		this.nom = nom;
		this.quantite = quantite;
		this.calories = calories;
		this.peremption = peremption;
		this.entreeFrigo = entreeFrigo;
		this.section = section;
		this.etatFrigo = etatFrigo;
	}
	

	@ManyToMany
	public Set<ListeDeCourse> listeDeCourse;

	@OneToMany	
	public Set<Recette> recette;

 	@ManyToOne
 	public EtatFrigo etatFrigo;
	
 	@Enumerated(EnumType.STRING)
	public Section section ;

	public String toString() {
	    return nom;
	}

	public boolean equals(Aliment obj) {
		// Vérification de l'égalité des références
		return this.nom.equals(obj.nom);
	}
   
   }
