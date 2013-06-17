package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;

import play.db.jpa.Model;

import javax.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class ListeDeCourse extends Model {

	@NotEmpty
	public String nom;

	@ManyToOne(optional=false)
	public User user;

	@ManyToMany//(mappedBy="listeDeCourse" )
	public List<AlimentConnu> article;
	
	public ListeDeCourse addAliment(String nom) {
		/* on recherche si l'aliment est connu */
		System.out.println("dans add Aliment pour l'aliment " + nom);
		AlimentConnu present = AlimentConnu.find("byNom", nom).first();
		List<ListeDeCourse> listes = new ArrayList<ListeDeCourse>();
		AlimentConnu newAliment = null ;
		if (present == null) {	

			System.out.println("dans present = null");
			/* si il n'était pas connu on crée un aliment de section Autre */
			 newAliment = new AlimentConnu(nom, listes, Section.Autre).save();
		} else {	
			System.out.println("dans present != null");
			 newAliment = present;
		}
    	this.article.add(newAliment);
		listes.add(this);
	    this.save();
	    return this;
	}
}
