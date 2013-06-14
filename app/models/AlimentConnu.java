package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class AlimentConnu extends Model {
	
	@Required
	public String nom;
	public Section section;
	
	public AlimentConnu(String nom, Section section) {
		super();
		this.nom = nom;
		this.section = section;
	}
	
}
