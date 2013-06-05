package models;

import javax.persistence.Entity;

import java.net.URL;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class EtatFrigo extends Model {
	public int date;

	@ManyToMany
	public Set<User> user;


	@Id
	public URL image;

	@OneToMany(mappedBy="etatFrigo" )
	public Set<Aliment> aliment;


}
