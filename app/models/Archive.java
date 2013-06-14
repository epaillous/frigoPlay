package models;

import java.util.Date;

import javax.persistence.ManyToOne;


public class Archive extends ListeDeCourse {

	public Date dateArchivage ;
	@ManyToOne(optional=false)
	public User user;
}
