package notifiers;
 
import models.Aliment;
import models.User;

import org.apache.commons.mail.*; 
import play.*;
import play.mvc.*;
import java.util.*;
 
public class Mails extends Mailer {
 
   public static void alerte(User user, String aliment) {
      setSubject("Alerte sur le produit %s", aliment);
      addRecipient(user.email);
      setFrom("MonFrigo <frigotimemachine@gmail.com>");
      send(user);
   }
 
   public static void lostPassword(User user) {
      String newpassword = user.motDePasse;
      setFrom("MonFrigo <emilie.paillous@gmail.com>");
      setSubject("Voici votre mot de Passe");
      addRecipient(user.email);
      send(user, newpassword);
   }
 
}
