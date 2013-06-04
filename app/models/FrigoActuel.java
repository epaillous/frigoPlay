package models;

import javax.persistence.ManyToOne;

public class FrigoActuel {

	public Image image ;
	
	/* Un frigo actuel peut correspondre à plusieurs utilisateurs */
	@ManyToOne
	public User user;
	
	public FrigoActuel(Image image, User user){
		this.image=image;
		this.user=user;
	}

}
