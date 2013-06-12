package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.apache.commons.io.FileUtils;

import models.Aliment;
import models.EtatFrigo;
import models.User;


import play.Play;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.With;


@With(Secure.class)
public class Application extends Controller {
	
	@Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user);
        }
    }

	
	public static void index() {
		User user = User.find("byEmail", Security.connected()).first();
		// Premier état frigo de la liste 
		List<Aliment> aliments = user.etatFrigo.get(0).aliment;
		List<Aliment> fruitsLegumes = new ArrayList<Aliment>();
		List<Aliment> viandes = new ArrayList<Aliment>();
		List<Aliment> laitages = new ArrayList<Aliment>();
		List<Aliment> boissons= new ArrayList<Aliment>() ;
		List<Aliment> autre = new ArrayList<Aliment>();
		
		Iterator iter = aliments.iterator();	
		while (iter.hasNext()) {
			Aliment cour = (Aliment) iter.next();
			switch (cour.section.nom) {
			case "Fruits et Légumes":
				fruitsLegumes.add(cour);
				break;
			case "Laitages":
				laitages.add(cour);
				break;	
			case "Viandes":
				viandes.add(cour);
				break;	
			case "Boissons":
				boissons.add(cour);
				break;
			case "Autre":
				autre.add(cour);
				break;	
			default:
				break;
			}
		}	
		render(fruitsLegumes, viandes, laitages, boissons, autre);
		
		
	}
 
    public static void historique() {
    	render();
    }
    
    public static void recettesProposees() {
    	render();
    }
    public static void recettesFavorites() {
    	render();
    }
    public static void carottesRapees() {
    	render();
    }
    
    public static void listesCourses() {
    	render();
    }
    
    public static void archives() {
    	render();
    }
    
    public static void profil() {
    	render();
    }
    
    public static void photo() {
    	render();
    }         
    
}
