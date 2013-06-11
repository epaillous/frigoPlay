package models;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import net.sf.oval.constraint.NotEmpty;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Section extends Model {
	
	@NotEmpty
	@Required
	public String nom;
	
	@OneToMany(mappedBy="section")
	public List<Aliment> aliments;
	
	public String toString() {
	    return nom;
	}

}
