package models;

import java.util.Date;

import javax.persistence.ManyToOne;


public class Archive extends ListeDeCourse {

	public Archive(String nom, User user, Date dateArchivage) {
		super(nom, user);
		this.dateArchivage = dateArchivage;
	}

	public Date dateArchivage ;
}
