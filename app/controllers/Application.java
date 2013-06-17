package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.io.FileUtils;

import models.Aliment;
import models.EtatFrigo;
import models.ListeDeCourse;
import models.Recette;
import models.Section;
import models.User;

import play.Play;
import play.db.jpa.JPA;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.With;
import utils.ApplicationUtils;


@With(Secure.class)
public class Application extends Controller {


	@Before
    static void checkAuthentification() throws Throwable {
        if(User.find("byEmail", Security.connected()).first() == null) Secure.login();
    }

	@Before
	static void setConnectedUser() {
		if(Security.isConnected()) {
			User user = User.find("byEmail", Security.connected()).first();
			renderArgs.put("user", user);
			EtatFrigo etatFrigo = EtatFrigo.find("user like ? order by date desc", user).first();
			renderArgs.put("etatFrigo", etatFrigo);
		}
	}

	@Before
	static void listeCourante() {

		/* On recupère l'utilisateur en session */
		User user = User.find("byEmail", Security.connected()).first();
		if(User.find("byEmail", Security.connected()).first() == null)
			try {
				Secure.login();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
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

		/* On recupère le dernier etat */
		EtatFrigo etatFrigo = EtatFrigo.find("user like ? order by date desc", user).first();


		/* on génère la liste correspondante */
		List<Aliment> aliments = etatFrigo.aliment;
		List<Aliment> fruitsLegumes = new ArrayList<Aliment>();
		List<Aliment> viandes = new ArrayList<Aliment>();
		List<Aliment> laitages = new ArrayList<Aliment>();
		List<Aliment> boissons= new ArrayList<Aliment>() ;
		List<Aliment> autre = new ArrayList<Aliment>();
		List<Aliment> epicerie = new ArrayList<Aliment>();

		/* tri par section */
		ApplicationUtils.misAJourListes(aliments, fruitsLegumes, viandes, laitages, boissons, autre, epicerie);

		/* On passe en paramètre la page dans laquelle on était */
		session.put("page", "index");	

		renderTemplate("Application/afficheEtat.html",fruitsLegumes, viandes, laitages, boissons, autre, epicerie, etatFrigo);
	}

	public static void ancienEtat(Long id) {
		if (id == null){
		}
		/* On recupère l'état frigo correspondant à l'id */
		EtatFrigo etatFrigo = EtatFrigo.findById(id);	

		/* on génère la liste correspondante */
		List<Aliment> aliments = etatFrigo.aliment;
		List<Aliment> fruitsLegumes = new ArrayList<Aliment>();
		List<Aliment> viandes = new ArrayList<Aliment>();
		List<Aliment> laitages = new ArrayList<Aliment>();
		List<Aliment> boissons= new ArrayList<Aliment>() ;
		List<Aliment> autre = new ArrayList<Aliment>();
		List<Aliment> epicerie = new ArrayList<Aliment>();

		/* tri par section */
		ApplicationUtils.misAJourListes(aliments, fruitsLegumes, viandes, laitages, boissons, autre, epicerie);

		/* On passe en paramètre la page dans laquelle on était */
		session.put("page", "ancienEtat");
		session.put("idfrigo", etatFrigo.id);
		renderArgs.put("etatFrigo", etatFrigo);
		renderTemplate("Application/afficheEtat.html",fruitsLegumes, viandes, laitages, boissons, autre, epicerie, etatFrigo);
	}

	public static void historique() {
		render();
	}

	public static void recettesFavorites() {
		/* On recupère l'utilisateur en session */
		User user = User.find("byEmail", Security.connected()).first();
		/* On recupère le dernier état du frigo */
		EtatFrigo etatFrigo = EtatFrigo.find("user like ? order by date desc", user).first();
		/* On recupère la liste correspondante */
		List<Recette> recettes= user.recettesFavorites;
		session.put("page", "recettesFavorites");
		renderArgs.put("liste", recettes);
		renderArgs.put("type_recette", "Vos recettes favorites");
		renderTemplate("Application/recettes.html", recettes);

	}

	public static void recettesSuggerees() {
		session.put("page", "recettesSuggerees");
		/* On recupère l'utilisateur en session */
		User user = User.find("byEmail", Security.connected()).first();
		/* On recupère le dernier état du frigo */
		EtatFrigo etatFrigo = EtatFrigo.find("user like ? order by date desc", user).first();
		/* définition des recettes suggérées :
		 * Si l'utilisateur a dans son frigo au moins un aliment de la recette
		 * alors la recette est suggérée
		 */	
		List<Recette> recettes ; 
		EntityManager em = JPA.em();

		/* recettes contenant des aliments présents dans le contenu de l'utilisateur */
		Query q_RecSug = em.createQuery("select DISTINCT r from Recette r, EtatFrigo f, IN(r.ingredient) AS ig, IN(f.aliment) AS al where " +
				"f=?1 and ig.id = al.id)");
		q_RecSug.setParameter(1, etatFrigo);
		List<Recette> recettesSugg = q_RecSug.getResultList();
		Iterator<Recette> r = recettesSugg.iterator();

		recettes = (List<Recette>) q_RecSug.getResultList();

		renderArgs.put("liste", recettes);
		renderArgs.put("type_recette", "Suggestions de recettes");
		renderTemplate("Application/recettes.html", recettes);

	}

	public static void listesArchivees() {
		render();
	}

	public static void listesFavorites() {
		render();
	}

	public static void profil() {
		long nbOuvertureJour = ApplicationUtils.nombreOuvertureFrigoParJour(new Date());
		render(nbOuvertureJour);

	}

	public static void photo() {
		render();
	}   

	public static void ajoutAlimentListe(String aliment, Long id) {

		/* On recupère l'utilisateur en session */
		User user = User.find("byEmail", Security.connected()).first();
		String page = session.get("page");

		if ((id == null) && (page == "ancienEtat")){
			String idS = session.get("idfrigo");
			id = Long.parseLong(idS);
		}

		/* On recupère sa liste courante (non nulle) */
		ListeDeCourse listeCourante = user.listeDeCourse.get(0);
		listeCourante.addAliment(aliment);


		switch (page){
		case "index":
			index();
			break;
		case "ancienEtat":
			ancienEtat(id);
			break;
		case "recettesFavorites":
			recettesFavorites();
		case "recettesSuggerees":
			recettesSuggerees();
			default:
				break;	
		}
	}


	public static void ajoutAlimentContenu(String aliment, String section) {
		/* On recupère l'utilisateur en session */
		User user = User.find("byEmail", Security.connected()).first();
		/* On recupère le dernier etat Frigo (non nulle) */
		EtatFrigo etatCourant = EtatFrigo.find("user like ? order by date desc", user).first(); 
		/* Si l'instance d'aliment n'existe pas, la créer */
		/* ajouter l'instance dans le frigo */
		//etatCourant.addAliment(aliment);
		etatCourant.addAliment(aliment, section);
		index();
	}

	public static void supprimeAlimentListe(Long id, Long idfrigo) {
		/* Supprimer l'aliment de la liste */
		Aliment aliment = Aliment.findById(id);
		/* On récupère la liste cour */
		/* .. */
		aliment.delete();
		String page = session.get("page");

		switch (page){
		case "index":
			index();
			break;
		case "ancienEtat":
			ancienEtat(idfrigo);
			break;
		case "recettesFavorites":
			recettesFavorites();
		case "recettesSuggerees":
			recettesSuggerees();
		default:
			break;
		}
	}

	public static void supprimeAlimentFrigo(Long id, Long idfrigo) {
		/* Supprimer l'aliment du contenu du frigo */
		Aliment aliment = Aliment.findById(id);
		aliment.delete();
		String page = session.get("page");

		switch (page){
		case "index":
			index();
			break;
		case "ancienEtat":
			ancienEtat(idfrigo);
			break;
		case "recettesFavorites":
			recettesFavorites();
		case "recettesSuggerees":
			recettesSuggerees();
		default:
			break;

		}
	}

	public static void ajoutContenuListe(Long id){
		EtatFrigo etatFrigo = EtatFrigo.findById(id);
		User user = User.find("byEmail", Security.connected()).first();
		List<Aliment> aliments = etatFrigo.aliment;
		Iterator iter = aliments.iterator();	
		/* On recupère sa liste courante (non nulle) */
		ListeDeCourse listeCourante = user.listeDeCourse.get(0);
		while (iter.hasNext()) {
			Aliment cour = (Aliment) iter.next();
		listeCourante.addAliment(cour.nom);
		}
		ancienEtat(id);
	}

}
