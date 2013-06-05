package models;

import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import org.hibernate.validator.constraints.Email;

import java.util.Date;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User{
   public String nom;

public void setNom(String value) {
    this.nom = value;
}
@NotEmpty
public String getNom() {
    return this.nom;
}
public String prenom;

public void setPrenom(String value) {
    this.prenom = value;
}
@NotEmpty
public String getPrenom() {
    return this.prenom;
}
public String motDePasse;

public void setMotDePasse(String value) {
    this.motDePasse = value;
}
@NotEmpty
public String getMotDePasse() {
    return this.motDePasse;
}
public Date dateDeNaissance;

public void setDateDeNaissance(Date value) {
    this.dateDeNaissance = value;
}
@Temporal(TemporalType.DATE)
public Date getDateDeNaissance() {
    return this.dateDeNaissance;
}
public int nbFoyer = 1;

public void setNbFoyer(int value) {
    this.nbFoyer = value;
}
public int getNbFoyer() {
    return this.nbFoyer;
}
public String email;

public void setEmail(String value) {
    this.email = value;
}
@Id
@Email
@NotEmpty
public String getEmail() {
    return this.email;
}
public String preference = "nulle";

public void setPreference(String value) {
    this.preference = value;
}
public String getPreference() {
    return this.preference;
}
   public Set<EtatFrigo> etatFrigo;
   
   @ManyToMany(mappedBy="user" )
   public Set<EtatFrigo> getEtatFrigo() {
      return this.etatFrigo;
   }
   
   public void setEtatFrigo(Set<EtatFrigo> etatFrigos) {
      this.etatFrigo = etatFrigos;
   }
   
   public Set<ListeDeCourse> listeDeCourse;
   
   @OneToMany(mappedBy="user" )
   public Set<ListeDeCourse> getListeDeCourse() {
      return this.listeDeCourse;
   }
   
   public void setListeDeCourse(Set<ListeDeCourse> listeDeCourses) {
      this.listeDeCourse = listeDeCourses;
   }
   
   }
