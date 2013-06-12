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
		/* Exemple avec des carottes */
		Aliment carottes = new Aliment("Carottes", "200g",0, new Date(), new Date(), newFrigo, Section.FruitsLegumes);
		if ((dernierEtat.aliment != null) && (dernierEtat.aliment.contains(carottes))) {
			/* Le frigo contenait déjà des tomates */
			Date entree = dernierEtat.aliment.get(dernierEtat.aliment.lastIndexOf(carottes)).entreeFrigo;
			carottes.entreeFrigo = entree;
		} 
		/* On crée l'instance de la carotte */
		carottes.save();
		aliments.add(carottes);
		return aliments;
		
	}
}
