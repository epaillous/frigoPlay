package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class AlimentConnu extends Model {

	@Required
	public String nom;
	public Section section;
	/* lien vers les images */

//	@OneToMany(mappedBy="alimentConnu")
//	public List<Image> images;

	public AlimentConnu(String nom, Section section) {
		super();
		this.nom = nom;
		this.section = section;
	}
	public AlimentConnu(String nom, String section) {
		super();
		this.nom = nom;
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


}
