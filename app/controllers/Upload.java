package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import models.Aliment;
import models.EtatFrigo;
import models.User;
import utils.TraitementImage;
import org.apache.commons.io.FileUtils;

import com.ning.http.client.Body;

import play.Play;
import play.mvc.Controller;
import play.mvc.Http;

public class Upload extends Controller {

	public static void upload(String name, File picture, String email) throws IOException{
		
		/* Reception de la photo */
	File uploadDir = new File(Play.applicationPath, "/public/img/");
	try {
		FileUtils.moveFile(picture, new File(uploadDir, picture.getName()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	
	/* Instance d'un nouvel etat frigo */
	/* On récupère l'utilisateur connecté */
	User user = User.find("byEmail", email).first();

	EtatFrigo newFrigo = new EtatFrigo(new Date(), user, "/public/img/" + picture.getName(), null).save();	
	/* Ajout en première position */
	user.etatFrigo.add(newFrigo);
	
	/* On etablit la liste des aliments */ 
	List<Aliment> aliments = TraitementImage.imageToAliments(picture, user, newFrigo);
	newFrigo.aliment = aliments;
	newFrigo.save();
	user.save();
	}

	
}


