package models;

import java.util.Collection;

import play.db.jpa.Model;

public class RecetteProposee extends Model {

	public Recette recette;
	public User utilisateur;
	public Collection<Aliment> aliments;
	
}
