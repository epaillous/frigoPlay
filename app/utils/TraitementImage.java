package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import play.mvc.Controller;
import play.mvc.With;

import controllers.Secure;
import controllers.Security;

import models.Aliment;
import models.AlimentConnu;
import models.EtatFrigo;
import models.Section;
import models.User;

public class TraitementImage {

	public static List<Aliment> imageToAliments(File data, User user, EtatFrigo newFrigo){
		
		/* Analyse de l'image non implémentée dans ce projet */
		
		/* Instance des aliments */
		List<Aliment> aliments = new ArrayList<Aliment>();
		List<EtatFrigo> anciensEtats = user.etatFrigo;
		EtatFrigo dernierEtat = anciensEtats.get(anciensEtats.size()-1);
		
//		/* Exemple de liste d'aliments générée  */	
		
		ajoutAliments(aliments, "Carottes", "200g", 0, dernierEtat, newFrigo, Section.FruitsLegumes );
		ajoutAliments(aliments, "Lait", "1L", 0, dernierEtat, newFrigo, Section.Laitages );
		ajoutAliments(aliments, "Brocolis", "100g", 0, dernierEtat, newFrigo, Section.FruitsLegumes );
		ajoutAliments(aliments, "Crème", "50cl", 1000, dernierEtat, newFrigo, Section.Laitages );
		ajoutAliments(aliments, "Ketchup", "500ml", 119, dernierEtat, newFrigo, Section.Epicerie );
		ajoutAliments(aliments, "Bière", "12x33cl", 0, dernierEtat, newFrigo, Section.Boissons );
		ajoutAliments(aliments, "Vodka", "75cl", 0, dernierEtat, newFrigo, Section.Boissons );
		ajoutAliments(aliments, "Emmental", "500g", 0, dernierEtat, newFrigo, Section.Laitages );
		ajoutAliments(aliments, "Oeufs", "12", 0, dernierEtat, newFrigo, Section.Autre );
		ajoutAliments(aliments, "Jus d'orange", "1.5L", 0, dernierEtat, newFrigo, Section.Boissons );
		ajoutAliments(aliments, "Sauce Pesto Rosso", "30cl", 0, dernierEtat, newFrigo, Section.Epicerie );
		ajoutAliments(aliments, "Filet de Porc cuisiné", "1 personne", 0, dernierEtat, newFrigo, Section.Viandes );
		ajoutAliments(aliments, "Yaourts aux fruits", "12", 0, dernierEtat, newFrigo, Section.Laitages );
		ajoutAliments(aliments, "Confiture", "50cl", 0, dernierEtat, newFrigo, Section.Epicerie );
		ajoutAliments(aliments, "Compotes à la fraise", "4", 0, dernierEtat, newFrigo, Section.FruitsLegumes );
		ajoutAliments(aliments, "Coca-Cola", "12 canettes", 0, dernierEtat, newFrigo, Section.Boissons );
		ajoutAliments(aliments, "Lardons", "200g", 0, dernierEtat, newFrigo, Section.Viandes );
		ajoutAliments(aliments, "Cornichons", "100g", 0, dernierEtat, newFrigo, Section.Epicerie);
		ajoutAliments(aliments, "Brocolis", "100g", 0, dernierEtat, newFrigo, Section.FruitsLegumes );
		
		
		return aliments;
		
	}
	
	/* Fonction ajoutant un aliment dans la DB et dans la liste des aliments du frigo */
	public static void ajoutAliments(List<Aliment> aliments, String nom, String quantite, int calories,EtatFrigo dernierEtat, EtatFrigo newFrigo, Section section){
		Aliment aliment = new Aliment(nom, quantite,calories, new Date(), new Date(), newFrigo, section);
		if ((dernierEtat.aliment != null) && (dernierEtat.aliment.contains(aliment))) {
			/* Le frigo contenait déjà l'aliment */
			Date entree = dernierEtat.aliment.get(dernierEtat.aliment.lastIndexOf(aliment)).entreeFrigo;
			aliment.entreeFrigo = entree;
		} 
		/* Si on ne connaît pas l'aliment on le rajoute dans les aliments connus */

		AlimentConnu present = AlimentConnu.find("byNom", nom).first();

		if (present == null) {
			new AlimentConnu(nom, section).save();
		}
		/* On crée l'instance de la carotte */
		aliment.save();
		aliments.add(aliment);
	}
}
