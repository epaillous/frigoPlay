package utils;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import controllers.Security;

import models.Aliment;
import models.EtatFrigo;
import models.ListeDeCourse;
import models.User;

public class ApplicationUtils {

	/* Fonction permettant le tri des aliments de listeAliment par sections */
	public static void misAJourListes(List<Aliment> listeAliment, List<Aliment> listeFruits, List<Aliment> listeViandes, 
			List<Aliment> listeLaitages, List<Aliment> listeBoissons, List<Aliment> listeAutre, List<Aliment> listeEpicerie){

		Iterator iter = listeAliment.iterator();	
		while (iter.hasNext()) {
			Aliment cour = (Aliment) iter.next();
			switch (cour.section.name()) {
			case "FruitsLegumes":
				listeFruits.add(cour);
				break;
			case "Laitages":
				listeLaitages.add(cour);
				break;	
			case "Viandes":
				listeViandes.add(cour);
				break;	
			case "Boissons":
				listeBoissons.add(cour);
				break;
			case "Autre":
				listeAutre.add(cour);
				break;	
			case "Epicerie":
				listeEpicerie.add(cour);
				break;

			default:
				break;
			}
		}
	}


	public static long nombreOuvertureFrigoParJour(Date jour){
		/* Bizarre, jour.getMonth renvoie 5 au lieu de 6 .. */
		return EtatFrigo.count("DAY(date) = ?1 AND MONTH(date) = ?2", jour.getDate(), jour.getMonth()+1);
	}

}
