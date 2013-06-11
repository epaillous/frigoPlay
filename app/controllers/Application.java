package controllers;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.apache.commons.io.FileUtils;

import models.EtatFrigo;
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

	
	public static void index() {
		render();
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
    	List<User> utilisateurs = User.findAll();
    	render(utilisateurs);
    }
    
    public static void photo() {
    	render();
    }         
//    
//    public static void upload(){
//    	FileInputStream stream = (FileInputStream) Http.Request.current().body;
//    	
//    	File uploadDir = new File(Play.applicationPath, "/public/uploads/");
//    	if (!uploadDir.exists()){
//   		uploadDir.mkdirs();
//    	}
//    }
    

}
