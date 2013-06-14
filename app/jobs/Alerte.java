package jobs;
 
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import models.Aliment;
import models.EtatFrigo;
import models.User;
import play.jobs.*;
import play.libs.Mail;
import notifiers.*;
 
@Every("24h")
@OnApplicationStart(async=true)
public class Alerte extends Job {
    
    public void doJob() throws EmailException {
    	System.out.println("je suis dans alerte!");
		/* On verifie les dates de péremption de tous les aliments contenus dans le dernier etat Frigo pour tous les utilisateurs */
    	List<User> users = User.findAll();
    	Calendar date=new GregorianCalendar();
    	/* On recupère la date du lendemain */
    	date.add(Calendar.DATE, 1);
    	Iterator iter = users.iterator();	
    	while (iter.hasNext()) {
    		User usercour = (User) iter.next();
    		System.out.println(usercour.prenom);
        	EtatFrigo dernierEtat = EtatFrigo.find("user like ? order by date desc", usercour).first();
        	List<Aliment> aliments = dernierEtat.aliment;
        	Iterator iter2 = aliments.iterator();
        	while (iter2.hasNext()) {
        		Aliment aliment = (Aliment) iter2.next();
        		System.out.println(aliment.nom);
        		if ((date.getTime().getDay() == aliment.peremption.getDay()) 
        				&& (date.getTime().getMonth() == aliment.peremption.getMonth()) 
        				&& (date.getTime().getYear() == aliment.peremption.getYear())) {   
        			/* le produit périme demain ! */
        			Mails.alerte(usercour, aliment.nom);
        		}
        	}
    	}

    	
	}
    
}
