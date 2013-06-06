package models;

import javax.persistence.Entity;

import java.net.URL;
import java.util.*;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class EtatFrigo extends Model {
	public Date date;

	@ManyToMany
	public Set<User> user;

	public String image;

	@OneToMany(mappedBy="etatFrigo" )
	public Set<Aliment> aliment;


}
