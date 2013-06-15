package models;

import javax.persistence.Entity;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class EtatFrigo extends Model {
	public Date date;

	@ManyToOne
	public User user;

	public String image;

	@OneToMany //(mappedBy="etatFrigo" )
	public List<Aliment> aliment;

	public EtatFrigo(Date date, User user, String image,
			List<Aliment> aliment) {
		super();
		this.date = date;
		this.user = user;
		this.image = image;
		this.aliment = aliment;
	}
	
//	public EtatFrigo addAliment(String aliment) {
//		AlimentConnu present = AlimentConnu.find("byNom", aliment).first();
//		if (present == null) {			
//			Aliment newAliment = new Aliment(aliment, this, Section.Autre).save();
//			this.aliment.add(newAliment);
//		}
//	    this.save();
//	    return this;
//	}
	
	public EtatFrigo addAliment(String aliment) {
		AlimentConnu present = AlimentConnu.find("byNom", aliment).first();
		if (present == null) {			
	    Aliment newAliment = new Aliment(aliment, new Date(), new Date(), this, Section.Autre).save();
	    this.aliment.add(newAliment);
		} else {	
			new Aliment(aliment,new Date(), new Date(), this, present.section).save();
		}
	    this.save();
	    return this;
	}

}