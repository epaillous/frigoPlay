package models;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import net.sf.oval.constraint.NotEmpty;

import play.data.validation.Required;
import play.db.jpa.Model;


public enum Section {
	FruitsLegumes("Fruits et Légumes"), 
	Boissons("Boissons"),
	Laitages("Laitages"), 
	Viandes("Viandes"),
	Autre("Autre");

	private String name = "";

	//Constructeur
	Section(String name){
		this.name = name;
	}

	public String toString(){
		return name;
	}

}
