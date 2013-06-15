package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Image extends Model {

	@Required 
	public String lien;
	
	@ManyToOne
	public AlimentConnu aliment;
}
