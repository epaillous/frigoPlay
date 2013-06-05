package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;

import play.db.jpa.Model;

import java.net.URL;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Recette extends Model {

	@Id
	@NotEmpty
	public String nom;


	public int difficulte;

	public int prix;

	public int calories;

	public URL lien;

	@ManyToMany(mappedBy="recette" )
	public Set<Aliment> ingredient;


}
