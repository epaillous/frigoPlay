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
	
//	@Before
//    static void checkAuthentification() {
//        if(session.get("user") == null) login();
//    }

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
		renderTemplate("Application/recettes.html", recettes);
		
	}
	
//	public static void recettes(Long id){
//		/* id = 1 ssi on veut accéder à recettes favorites 
//		 * id = 2 ssi on veut accéder à recettes suggérées
//		 */
//		/* On recupère l'utilisateur en session */
//		User user = User.find("byEmail", Security.connected()).first();
//		/* On recupère le dernier état du frigo */
//		EtatFrigo etatFrigo = EtatFrigo.find("user like ? order by date desc", user).first();
//		/* On recupère la liste correspondante */
//		List<Recette> recettes= user.recettesFavorites;
//		List<Recette> recettesfav = user.recettesFavorites;
//		List<Recette> recettesSugg = null ;
//		if (id == 1){
//			session.put("page", "recettesFavorites");
//			/* la liste de recettes à afficher est la liste de recettes favorites */
//			recettes = recettesfav;
//		} else {
//			session.put("page", "recettesSuggerees");
//			/* définition des recettes suggérées :
//			 * Si l'utilisateur a dans son frigo au moins un aliment de la recette
//			 * alors la recette est suggérée
//			 */	
//			EntityManager em = JPA.em();
//			recettesSugg = Recette.find("prix like 3").fetch();
//			//	recettes = Recettes.find("user like ?", user);
//			recettes = recettesSugg;
//		}
//		renderArgs.put("liste", recettes);
//		renderTemplate("Application/recettes.html", recettes);		
//	}

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
		/* requete qui fonctionne */
		Query q3 = em.createQuery("select r from Recette r, User u where" +
				" r.prix=?1 and u=?2" );
		q3.setParameter(1, 3).setParameter(2, user);
		
		/* ingrédients dans toutes les recettes */
		Query q4 = em.createQuery("select r.ingredient from Recette r ");
		List<Aliment> ingredients = q4.getResultList();
		System.out.println("ingrédients recettes");
		Iterator<Aliment> i = ingredients.iterator();
		while(i.hasNext()){
			System.out.println(i.next().nom);
		}
		
		Query q4bis = em.createQuery("select distinct ig.id from Recette r, IN(r.ingredient) AS ig");
		List<Aliment> ingredientsbis = q4bis.getResultList();
		System.out.println("id ingrédients recettes");
		Iterator<Aliment> ibis = ingredientsbis.iterator();
		while(ibis.hasNext()){
			System.out.println(ibis.next());
		}
		
		/* ingrédients contenus dans le frigo de l'utilisateur */
		
		Query q5 = em.createQuery("select f.aliment from EtatFrigo f where f=?1 ");
		q5.setParameter(1, etatFrigo);//setParameter(2, ingredients);
		List<Aliment> alimentsContenus = q5.getResultList();
		System.out.println("\n\n aliments contenus frigo \n");
		Iterator<Aliment> a = alimentsContenus.iterator();
		while(a.hasNext()){
			System.out.println(a.next().nom);
		}
		
		Query q5bis = em.createQuery("select al.id from EtatFrigo f, IN(f.aliment) AS al where f=?1 ");
		q5bis.setParameter(1, etatFrigo);//setParameter(2, ingredients);
		List<Aliment> alimentsContenusbis = q5bis.getResultList();
		System.out.println("\n\n id aliments contenus frigo \n");
		Iterator<Aliment> abis = alimentsContenusbis.iterator();
		while(abis.hasNext()){
			System.out.println(abis.next());
		}

	
		/* ingrédients des recettes qui sont dans le frigo */
		Query ingdsfrig = em.createQuery("select ig from Recette r, EtatFrigo f, IN(r.ingredient) AS ig, IN(f.aliment) AS al where " +
				"f=?1 and ig.id = al.id)");
		ingdsfrig.setParameter(1, etatFrigo);
		List<Aliment> alimentsRecCont = ingdsfrig.getResultList();
		System.out.println("aliments des recettes contenus dans le frigo");
		Iterator<Aliment> idf = alimentsRecCont.iterator();
		while(idf.hasNext()){
			System.out.println(idf.next().nom);
		}
				
		/* recettes contenant des aliments présents dans le contenu de l'utilisateur */
		Query qRecSug = em.createQuery("select r from Recette r, EtatFrigo f, IN(r.ingredient) AS ig, IN(f.aliment) AS al where " +
				"f=?1 and ig.id = al.id)");
		qRecSug.setParameter(1, etatFrigo);
		List<Recette> recettesSugg = qRecSug.getResultList();
		System.out.println("recettes ");
		Iterator<Recette> r = recettesSugg.iterator();
		while(r.hasNext()){
			System.out.println(r.next().nom);
		}
		

		recettes = (List<Recette>) qRecSug.getResultList();
		
		
		renderArgs.put("liste", recettes);
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
	
	public static void ajoutAlimentContenu(String aliment) {
		/* On recupère l'utilisateur en session */
		User user = User.find("byEmail", Security.connected()).first();

		/* On recupère le dernier etat Frigo (non nulle) */
		// EtatFrigo etatCourant = user.etatFrigo.get(0);
		EtatFrigo etatCourant = EtatFrigo.find("user like ? order by date desc", user).first(); 
		etatCourant.addAliment(aliment);
		System.out.println("on a ajouté un aliment");
		index();
	}

	public static void supprimeAlimentListe(Long id, Long idfrigo) {

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

}
