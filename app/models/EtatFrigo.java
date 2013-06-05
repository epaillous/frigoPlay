package models;

import javax.persistence.Entity;

import java.net.URL;
import java.util.Date;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class EtatFrigo{
   private Date date;

public void setDate(Date value) {
    this.date = value;
}
public Date getDate() {
    return this.date;
}
private Set<User> user;

@ManyToMany
public Set<User> getUser() {
   return this.user;
}

public void setUser(Set<User> users) {
   this.user = users;
}

private URL image;

public void setImage(URL value) {
    this.image = value;
}
@Id
public URL getImage() {
    return this.image;
}
   private Set<Aliment> aliment;
   
   @OneToMany(mappedBy="etatFrigo" )
   public Set<Aliment> getAliment() {
      return this.aliment;
   }
   
   public void setAliment(Set<Aliment> aliments) {
      this.aliment = aliments;
   }
   
   }
