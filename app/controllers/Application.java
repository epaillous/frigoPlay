package controllers;
import play.data.Upload;
import javax.persistence.*;
import play.db.jpa.Model;
import play.*;
import play.libs.Files;
import play.mvc.*;
import play.mvc.results.RenderBinary;
import play.mvc.results.Result;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.tools.shell.commands.ShowCommand;

import com.ning.http.multipart.MultipartBody;


import models.*;
import play.db.jpa.*;
public class Application extends Controller {

    public static void index() {
        render();
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
    	render();
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
//                if (files == null || files.length < 1){
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
//            }

            // fonction qui fait apparaître le .jpg temporaire à cause du pointeur null 
       public static void upload(File file) {
            
    	System.out.println("dans upload");
    		//notFoundIfNull(file);
    		File to = Play.getFile("data/" + file.getName());
    		file.renameTo(to);
    		try{
    			Files.copy(file, to);
    		} catch (RuntimeException e){
    			Play.getFile("data").mkdir();
    			Files.copy(file, to);
    		   	e.printStackTrace();
    		}
    
    	System.out.println("sortie upload");
    }
    
}
