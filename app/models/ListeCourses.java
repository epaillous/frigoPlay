package models;

import java.util.Collection;

import javax.persistence.*;

import play.db.jpa.Model;

public class ListeCourses extends Model {

	public String nom;
	
	@ManyToOne
	public User utilisateur;
	@ManyToMany
	public Collection<Aliment> items;
	
}
