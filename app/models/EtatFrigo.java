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
	
	
	public EtatFrigo addAliment(String aliment, String section) {
		Aliment newAliment;
		if ((newAliment = Aliment.find("byNom", aliment).first()) == null ){
			/* cet Aliment n'existe pas */
			newAliment = new Aliment(aliment, new Date(), new Date(), this, section).save();
			new AlimentConnu(aliment, section).save();
		}
		this.aliment.add(newAliment);
	    this.save();
	    return this;
	}

}