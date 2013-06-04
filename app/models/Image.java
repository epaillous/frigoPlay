package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

public class Image {

	public String nom;
	public Date dateDePrise;
	public String lien;
	
	@ManyToMany(mappedBy="image", cascade=CascadeType.ALL)
	public List<Aliment> aliments;
}
