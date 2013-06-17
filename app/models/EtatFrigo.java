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
		AlimentConnu newAlimentConnu;
		if ((newAlimentConnu = AlimentConnu.find("byNom", aliment).first()) == null ){
			/* cet Aliment n'existe pas */
<<<<<<< HEAD
			newAliment = new Aliment(aliment, new Date(), new Date(), this, section).save();
			new AlimentConnu(aliment, section).save();
		}
		this.aliment.add(newAliment);
=======
			System.out.println("l'aliment n'existe pas");
			newAlimentConnu = new AlimentConnu(aliment, section).save();
		}
		/* dans tous les cas on rajoute l'aliment dans le frigo */
		newAliment = new Aliment(aliment, new Date(), new Date(), this, section).save();
		System.out.println("section = " + section);	
	    this.aliment.add(newAliment);
>>>>>>> 9f7b114e9424c0d6c8afe30e163c3a4010b5373f
	    this.save();
	    return this;
	}

}