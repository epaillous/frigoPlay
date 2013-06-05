package models;
import java.util.Set;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.ManyToMany;

import play.db.jpa.Model;

public class EtatFrigo extends Model {
public Set<User> utilisateurs;

   

	public Date date;
	public String lienPhoto;

}
