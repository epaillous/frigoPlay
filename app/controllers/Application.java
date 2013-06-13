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
import utils.ApplicationUtils;


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
		
		/* On recupère l'utilisateur en session */
		User user = User.find("byEmail", Security.connected()).first();

		/* On recupère sa liste de course courante (non nulle) */
		ListeDeCourse listeCourante = user.listeDeCourse.get(0);
		List<Aliment> articles = listeCourante.article;
		List<Aliment> artFruitsLegumes = new ArrayList<Aliment>();
		List<Aliment> artViandes = new ArrayList<Aliment>();
		List<Aliment> artLaitages = new ArrayList<Aliment>();
		List<Aliment> artBoissons= new ArrayList<Aliment>() ;
		List<Aliment> artAutre = new ArrayList<Aliment>();
		List<Aliment> artEpicerie = new ArrayList<Aliment>();

		/* tri par section */
		ApplicationUtils.misAJourListes(articles, artFruitsLegumes, artViandes, artLaitages, artBoissons, artAutre, artEpicerie);

		renderArgs.put("artFruitsLegumes", artFruitsLegumes);
		renderArgs.put("artLaitages", artLaitages);
		renderArgs.put("artViandes", artViandes);
		renderArgs.put("artBoissons", artBoissons);
		renderArgs.put("artAutre", artAutre);
		renderArgs.put("artEpicerie", artEpicerie);
	}

	public static void index() {
		
		/* On recupère l'utilisateur en session */
		User user = User.find("byEmail", Security.connected()).first();

		/* On recupère le dernier état de son frigo */
		EtatFrigo dernierEtat = EtatFrigo.find("user like ? order by date desc", user).first();
		List<Aliment> aliments = dernierEtat.aliment;
		List<Aliment> fruitsLegumes = new ArrayList<Aliment>();
		List<Aliment> viandes = new ArrayList<Aliment>();
		List<Aliment> laitages = new ArrayList<Aliment>();
		List<Aliment> boissons= new ArrayList<Aliment>() ;
		List<Aliment> autre = new ArrayList<Aliment>();
		List<Aliment> epicerie = new ArrayList<Aliment>();

		/* tri par section */
		ApplicationUtils.misAJourListes(aliments, fruitsLegumes, viandes, laitages, boissons, autre, epicerie);

		render(fruitsLegumes, viandes, laitages, boissons, autre, epicerie, dernierEtat);

	}

	public static void ancienEtat(Long id) {
		
		/* On recupère l'état frigo sur lequel on a cliqué */
		EtatFrigo etatFrigo = EtatFrigo.findById(id);

		/* On recupère sa liste d'aliment */
		List<Aliment> aliments = etatFrigo.aliment;
		List<Aliment> fruitsLegumes = new ArrayList<Aliment>();
		List<Aliment> viandes = new ArrayList<Aliment>();
		List<Aliment> laitages = new ArrayList<Aliment>();
		List<Aliment> boissons= new ArrayList<Aliment>() ;
		List<Aliment> autre = new ArrayList<Aliment>();
		List<Aliment> epicerie = new ArrayList<Aliment>();

		/* tri par section */
		ApplicationUtils.misAJourListes(aliments, fruitsLegumes, viandes, laitages, boissons, autre, epicerie);

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
		
		/* On recupère l'utilisateur en session */
		User user = User.find("byEmail", Security.connected()).first();

		/* On recupère sa liste courante (non nulle) */
		ListeDeCourse listeCourante = user.listeDeCourse.get(0);
		listeCourante.addAliment(aliment);
		
		/* on trie à nouveau les listes pour ajouter l'aliment dans la bonne section */
		List<Aliment> articles = listeCourante.article;
		List<Aliment> artFruitsLegumes = new ArrayList<Aliment>();
		List<Aliment> artViandes = new ArrayList<Aliment>();
		List<Aliment> artLaitages = new ArrayList<Aliment>();
		List<Aliment> artBoissons= new ArrayList<Aliment>() ;
		List<Aliment> artAutre = new ArrayList<Aliment>();
		List<Aliment> artEpicerie = new ArrayList<Aliment>();

		/* tri par section */
		ApplicationUtils.misAJourListes(articles, artFruitsLegumes, artViandes, artLaitages, artBoissons, artAutre, artEpicerie);

		renderArgs.put("artFruitsLegumes", artFruitsLegumes);
		renderArgs.put("artLaitages", artLaitages);
		renderArgs.put("artViandes", artViandes);
		renderArgs.put("artBoissons", artBoissons);
		renderArgs.put("artAutre", artAutre);
		renderArgs.put("artEpicerie", artEpicerie);
		index();
	}
	
	public static void supprimeAlimentListe(Long id) {
		
		Aliment aliment = Aliment.findById(id);
		aliment.delete();
		index();
	}

}
