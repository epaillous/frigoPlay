package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;

import play.db.jpa.Model;

import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class ListeDeCourse extends Model {

	@Id
	@NotEmpty
	public String nom;

	@ManyToOne(optional=false)
	public User user;

	@ManyToMany(mappedBy="listeDeCourse" )
	public Set<Aliment> article;


}
