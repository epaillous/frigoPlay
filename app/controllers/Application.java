package controllers;
import play.db.jpa.Model;
import play.*;
import play.mvc.*;
import play.mvc.results.Result;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;


import models.*;

public class Application extends Controller {

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
    	render();
    }
    
//    public static void upload() {
//    	render();
//    }
//
//    
    public static void upload() {
    	InputStream inputStream = request.body;

    	// Save it somewhere..
    	// String filePath = "C:\\Utilisateurs\\Lili\\image.jpg";
    	try 
    	{ 
    	
    		File fichier = new File("C:\\image2.jpg\\"); 
    	
    	fichier.setWritable(true);
    	fichier.createNewFile(); // Cette fonction doit �tre appel�e au sein d'un bloc TRY 
    	java.io.FileOutputStream monFluxFichier = new java.io.FileOutputStream(fichier); // Doit �tre utilis� dans un bloc TRY 
    	

			byte[] buf = new byte [1024] ;
	    	int len;
	    	while((len=inputStream.read(buf))>0) monFluxFichier.write(buf,0,len);
	    	monFluxFichier.close();
	    	inputStream.close();
	    }catch (IOException e) 
	    	{ 
	    	System.out.println("Impossible de cr�er le fichier"); 
	    	e.printStackTrace();
	    	} 
    	
    	// Anything which is appensed to POST URL at client end maps to String name
    	System.out.println("This Method Called");
    	render();
    }
}
