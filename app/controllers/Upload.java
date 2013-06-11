package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;

import play.Play;
import play.mvc.Controller;
import play.mvc.Http;

public class Upload extends Controller {

//	//Fonction qui devrait marcher parce que ça fonctionne quand le post est fait à partir d'un formulaire
//	public static void upload(File[] files) {
//		System.out.println("dans upload");
////		if (files == null || files.length < 1){
////			return;
////		}
//		File file = files[0];
//		File uploadDir = new File(Play.applicationPath, "/public/uploads/");
//		if (!uploadDir.exists()){
//			uploadDir.mkdirs();
//		}
//		try {
//			FileUtils.moveFile(file, new File(uploadDir, file.getName()));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public static void upload() throws IOException{

		System.out.println("entrée dans upload");
		File uploadDir = new File(Play.applicationPath, "/public/uploads/");
		if (!uploadDir.exists()){
			uploadDir.mkdirs();
		}
		File file = new File(Play.applicationPath, "/public/uploads/picture_test.jpg");
		file.createNewFile();
		InputStream is = Http.Request.current().body;	
			FileOutputStream fos;
				fos = new FileOutputStream(file.getAbsolutePath());
				System.out.println(file.getAbsolutePath());
		try {
			// On utilise un tableau comme buffer
			byte[] buf = new byte[8192];
			// Et on utilise une variable pour connaitre le nombre
			// de bytes lus, et donc le nombres qu'il faudra écrire :
			int len;
			while ((len = is.read(buf)) >= 0) {
				fos.write(buf, 0, len);
			}			
			
		} finally {
			// On ferme le fichier quoi qu'il arrive :
			fos.close();
			is.close();
			System.out.println("on sort de upload");
		}
		
//		FileInputStream stream = (FileInputStream) Http.Request.current().body;	
//		File uploadDir = new File(Play.applicationPath, "/public/uploads/");
//		if (!uploadDir.exists()){
//			uploadDir.mkdirs();
//		}
//			System.out.println("le body est pas nul");
//			try {
//				OutputStream outputStream = new FileOutputStream(stream);
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		try {
//			FileUtils.moveFile(file, new File(uploadDir, file.getName()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}

