package controllers;

import play.*;
import play.mvc.*;
import play.mvc.results.Result;

import java.io.File;
import java.util.*;


import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void historique() {
    	render();
    }
    
    public static void upload() {
    	render();
    }

    public static void upload(File[] files) {

    }
}
