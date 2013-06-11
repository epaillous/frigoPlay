package models;

import play.db.jpa.Blob;
import play.db.jpa.Model;
import javax.persistence.*;

	@Entity
	public class Photo extends Model {
    	public Blob photo;
    	public String name;
    }
  
