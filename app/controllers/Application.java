package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.apache.commons.io.FileUtils;

import models.Aliment;
import models.Courante;
import models.EtatFrigo;
import models.ListeDeCourse;
import models.Recette;
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
	
	@Before
    static void listeCourante() {
            User user = User.find("byEmail", Security.connected()).first();
            ListeDeCourse listeCourante = user.listeDeCourse.get(0);
            List<Aliment> articles = listeCourante.article;
            List<Aliment> artFruitsLegumes = new ArrayList<Aliment>();
    		List<Aliment> artViandes = new ArrayList<Aliment>();
    		List<Aliment> artLaitages = new ArrayList<Aliment>();
    		List<Aliment> artBoissons= new ArrayList<Aliment>() ;
    		List<Aliment> artAutre = new ArrayList<Aliment>();
    		List<Aliment> artEpicerie = new ArrayList<Aliment>();
    		
    		Iterator iter = articles.iterator();	
    		while (iter.hasNext()) {
    			Aliment cour = (Aliment) iter.next();
    			switch (cour.section.name()) {
    			case "FruitsLegumes":
    				artFruitsLegumes.add(cour);
    				break;
    			case "Laitages":
    				artLaitages.add(cour);
    				break;	
    			case "Viandes":
    				artViandes.add(cour);
    				break;	
    			case "Boissons":
    				artBoissons.add(cour);
    				break;
    			case "Autre":
    				artAutre.add(cour);
    				break;	
    			case "Epicerie":
    				artEpicerie.add(cour);
    				break;
    			
    			default:
    				break;
    			}
    		}	
           
            renderArgs.put("artFruitsLegumes", artFruitsLegumes);
            renderArgs.put("artLaitages", artLaitages);
            renderArgs.put("artViandes", artViandes);
            renderArgs.put("artBoissons", artBoissons);
            renderArgs.put("artAutre", artAutre);
            renderArgs.put("artEpicerie", artEpicerie);
    }
	
	public static void index() {
		User user = User.find("byEmail", Security.connected()).first();
		// Premier état frigo de la liste 
		EtatFrigo dernierEtat = EtatFrigo.find("user like ? order by date desc", user).first();
		List<Aliment> aliments = dernierEtat.aliment;
		List<Aliment> fruitsLegumes = new ArrayList<Aliment>();
		List<Aliment> viandes = new ArrayList<Aliment>();
		List<Aliment> laitages = new ArrayList<Aliment>();
		List<Aliment> boissons= new ArrayList<Aliment>() ;
		List<Aliment> autre = new ArrayList<Aliment>();
		List<Aliment> epicerie = new ArrayList<Aliment>();
		
		Iterator iter = aliments.iterator();	
		while (iter.hasNext()) {
			Aliment cour = (Aliment) iter.next();
			switch (cour.section.name()) {
			case "FruitsLegumes":
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
			case "Epicerie":
				epicerie.add(cour);
				break;
			
			default:
				break;
			}
		}	
		render(fruitsLegumes, viandes, laitages, boissons, autre, epicerie, dernierEtat);

	}
	
	public static void ancienEtat(Long id) {
		EtatFrigo etatFrigo = EtatFrigo.findById(id);
		List<Aliment> aliments = etatFrigo.aliment;
		List<Aliment> fruitsLegumes = new ArrayList<Aliment>();
		List<Aliment> viandes = new ArrayList<Aliment>();
		List<Aliment> laitages = new ArrayList<Aliment>();
		List<Aliment> boissons= new ArrayList<Aliment>() ;
		List<Aliment> autre = new ArrayList<Aliment>();
		List<Aliment> epicerie = new ArrayList<Aliment>();
		
		Iterator iter = aliments.iterator();	
		while (iter.hasNext()) {
			Aliment cour = (Aliment) iter.next();
			switch (cour.section.name()) {
			case "FruitsLegumes":
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
			case "Epicerie":
				epicerie.add(cour);
				break;
			
			default:
				break;
			}
		}
		render(fruitsLegumes, viandes, laitages, boissons, autre, epicerie, etatFrigo);
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
    
    public static void ajoutAlimentListe(String aliment) {
    	User user = User.find("byEmail", Security.connected()).first();
    	ListeDeCourse listeCourante = user.listeDeCourse.get(0);
    	listeCourante.addAliment(aliment);
    	List<Aliment> articles = listeCourante.article;
        List<Aliment> artFruitsLegumes = new ArrayList<Aliment>();
		List<Aliment> artViandes = new ArrayList<Aliment>();
		List<Aliment> artLaitages = new ArrayList<Aliment>();
		List<Aliment> artBoissons= new ArrayList<Aliment>() ;
		List<Aliment> artAutre = new ArrayList<Aliment>();
		List<Aliment> artEpicerie = new ArrayList<Aliment>();
		
		Iterator iter = articles.iterator();	
		while (iter.hasNext()) {
			Aliment cour = (Aliment) iter.next();
			switch (cour.section.name()) {
			case "FruitsLegumes":
				artFruitsLegumes.add(cour);
				break;
			case "Laitages":
				artLaitages.add(cour);
				break;	
			case "Viandes":
				artViandes.add(cour);
				break;	
			case "Boissons":
				artBoissons.add(cour);
				break;
			case "Autre":
				artAutre.add(cour);
				break;	
			case "Epicerie":
				artEpicerie.add(cour);
				break;
			
			default:
				break;
			}
		}	
       
        renderArgs.put("artFruitsLegumes", artFruitsLegumes);
        renderArgs.put("artLaitages", artLaitages);
        renderArgs.put("artViandes", artViandes);
        renderArgs.put("artBoissons", artBoissons);
        renderArgs.put("artAutre", artAutre);
        renderArgs.put("artEpicerie", artEpicerie);
    	index();
    }
    
}
