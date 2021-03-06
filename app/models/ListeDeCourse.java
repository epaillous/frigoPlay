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

	@ManyToMany(mappedBy="listeDeCourse" )
	public List<Aliment> article;

	public ListeDeCourse addAliment(String aliment) {
		AlimentConnu present = AlimentConnu.find("byNom", aliment).first();
		List<ListeDeCourse> listes = new ArrayList<ListeDeCourse>();
		listes.add(this);
		if (present == null) {			
	    Aliment newAliment = new Aliment(aliment, listes, Section.Autre).save();
	    this.article.add(newAliment);
		} else {	
			new Aliment(aliment, listes, present.section).save();
		}
	    this.save();
	    return this;
	}

	public ListeDeCourse(String nom, User user) {
		super();
		this.nom = nom;
		this.user = user;
	}

}
