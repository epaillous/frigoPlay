package models;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class AlimentConnu extends Model {
	
	@Required
	public String nom;
 	@Enumerated(EnumType.STRING)
	public Section section ;

	/* lien vers les images */
	
//	@OneToMany(mappedBy="alimentConnu")
//	public List<Image> images;
	
	@ManyToMany
	public List<ListeDeCourse> listeDeCourse;

	@ManyToMany
	public Set<Recette> recette;


	/* Constructeur pour les aliments ajoutés en liste de courses */
	public AlimentConnu(String nom, List<ListeDeCourse> listeDeCourse, Section section) {
		super();
		this.nom = nom;
		this.listeDeCourse = listeDeCourse;
		this.section = section;
	}
	
	public AlimentConnu(String nom, Section section) {
		super();
		this.nom = nom;
		this.section = section;
	}
	
	public AlimentConnu(String nom, String section) {
		super();
		this.nom = nom;
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
		}else {
		this.section = Section.Autre;
		}
	}
	
}
