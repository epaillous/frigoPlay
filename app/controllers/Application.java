package controllers;
import java.io.File;
import java.util.*;

import models.EtatFrigo;
import models.User;

import play.mvc.Before;
import play.mvc.Controller;
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
    	EtatFrigo etatFrigo = EtatFrigo.find("order by date desc").first();
        render(etatFrigo);
    }
    
    public static void historique() {
    	render();
    }
    
    public static void recettes() {
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
    
    
 // fonction qui crée objet Photo  
//    public static void upload(Photo PhotoSauvee) {
//    	// correct si paramètre de type Upload
//    	// File to = Play.getFile("data/" + file.getFileName());
//    	//PhotoSauvee.save();
//    }   

//    public static void affichePhoto(long id){
//    	
//    	final Photo photo = Photo.findById(id);
//    	notFoundIfNull(photo);
//    	response.setContentTypeIfNotSet(photo.photo.type());
//    	renderBinary(photo.photo.get());
//    }
//    
 			// Fonction qui devrait marcher parce que ça fonctionne quand le post est fait à partir d'un formulaire
//            public static void upload(File[] files) {
//              System.out.println("sortie upload");
//                if (files == null || files.length < 1){
//                	System.out.println("files null");
//                    return;
//                }
//                File file = files[0];
//                File uploadDir = new File(Play.applicationPath, "/public/uploads/");
//                if (!uploadDir.exists()){
//                    uploadDir.mkdirs();
//                }
//                try {
//                    FileUtils.moveFile(file, new File(uploadDir, file.getName()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                render();
//            }

            // fonction qui fait apparaître le .jpg temporaire à cause du pointeur null 
       public static void upload(File file) {
    	   final Map<String, String[]> entries = params.data;
    	   System.out.println("val entree : " + entries.toString());
//    		//notFoundIfNull(file);
//    		File to = Play.getFile("data/" + file.getName());
//    		file.renameTo(to);
//    		try {
//    			Files.copy(file, to);
//    		} catch (RuntimeException e){
//    			Play.getFile("data").mkdir();
//    			Files.copy(file, to);
//    		   	e.printStackTrace();
//    		}
    System.out.println("sortie upload");
    	
    }
    
}
