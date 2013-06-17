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


	/* Constructeur pour les aliments ajoutés en liste de courses */
	public Aliment(String nom, List<ListeDeCourse> listeDeCourse, Section section) {
		super();
		this.nom = nom;
		this.listeDeCourse = listeDeCourse;
		this.section = section;
	}


//	/* Constructeur pour les aliments ajoutés dans le contenu du frigo */
	public Aliment(String nom, Date peremption, Date entreeFrigo, EtatFrigo etatFrigo, Section section) {
		super();
		this.nom = nom;
		this.peremption = peremption;
		this.entreeFrigo = entreeFrigo;
		this.etatFrigo = etatFrigo;
		this.section = section;
	}

	// constructeur ajout dans le frigo Alice
	public Aliment(String nom, Date peremption, Date entreeFrigo, EtatFrigo etatFrigo, String section) {
		super();
		this.nom = nom;
		this.peremption = peremption;
		this.entreeFrigo = entreeFrigo;
		this.etatFrigo = etatFrigo;
		System.out.println("section = " + section);
		if (section.equals("Epicerie")){
			this.section = Section.Epicerie;
		}else if 
		(section.equals("Fruits et Légumes")){
			this.section = Section.FruitsLegumes;
		}else if
		(section.equals("Boissons")){
			this.section = Section.Boissons;
		}else if (section.equals("Laitages")){
			this.section = Section.Laitages;
		}else if (section.equals("Viandes et Poissons")){
				this.section = Section.Viandes;
		}else if (section.equals("Epicerie")){
				this.section = Section.Epicerie;
		}else if (section.equals("Autre")){
		this.section = Section.Autre;
	}
}


	@ManyToMany
	public List<ListeDeCourse> listeDeCourse;

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
