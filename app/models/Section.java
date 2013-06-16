package models;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import net.sf.oval.constraint.NotEmpty;

import play.data.validation.Required;
import play.db.jpa.Model;


public enum Section {
	FruitsLegumes("Fruits et Légumes","/public/images/carotte.svg"),
	Boissons("Boissons","/public/images/boissons.svg"),
	Laitages("Laitages", "/public/images/lait.svg"), 
	Viandes("Viandes et Poissons", "/public/images/steak.svg"),
	Autre("Autre", "/public/images/condiments.svg"),
	Epicerie("Epicerie", "/public/images/confiture.svg");

	private String name = "";
	private String icone = "";
	//Constructeur
	
	Section(String name, String icone){
		this.name = name;
		this.icone = icone;
	}

	public String toString(){
		return name;
	}

}
