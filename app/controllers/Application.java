package controllers;

import play.*;
import play.mvc.*;
import play.mvc.results.Result;

import java.util.*;

import com.ning.http.multipart.FilePart;
import com.ning.http.multipart.MultipartBody;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void historique() {
    	render();
    }
}