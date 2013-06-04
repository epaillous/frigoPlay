package models;
 
import play.*;
import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.*;
 
import javax.persistence.*;

import java.util.*;
 
@Entity
public class User extends Model {
 		
	@Email
	@Required
	public String email;
	@Required
	public String nom;
	@Required
	public String prenom;
	@Required
	public String motDePasse;
	public int nbFoyer;
	public String preference;
    public Date dateDeNaissance;

	 public String toString() {
	        return email;
	    }
	 
	 public static User connect(String email, String motDePasse) {
		    return find("byEmailAndPassword", email, motDePasse).first();
		}
		
 	
}