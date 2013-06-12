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
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import com.ning.http.client.Body;

import play.Play;
import play.mvc.Controller;
import play.mvc.Http;

public class Upload extends Controller {

	public static void upload(String name, File data) throws IOException{
	File uploadDir = new File(Play.applicationPath, "/public/img/");
	try {
		FileUtils.moveFile(data, new File(uploadDir, data.getName()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
}


