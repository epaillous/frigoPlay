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
	
}
