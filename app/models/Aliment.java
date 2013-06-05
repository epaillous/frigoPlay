package models;
import javax.persistence.OneToMany;

import models.EtatFrigo;
import models.Recette;
import models.ListeDeCourse;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Aliment{
   public String nom;

public void setNom(String value) {
    this.nom = value;
}
@Id
@NotEmpty
public String getNom() {
    return this.nom;
}
private String quantite;

public void setQuantite(String value) {
    this.quantite = value;
}
public String getQuantite() {
    return this.quantite;
}
private int calories;

public void setCalories(int value) {
    this.calories = value;
}
public int getCalories() {
    return this.calories;
}
private Date peremption;

public void setPeremption(Date value) {
    this.peremption = value;
}
public Date getPeremption() {
    return this.peremption;
}
   private Set<ListeDeCourse> listeDeCourse;
   
   @OneToMany
   public Set<ListeDeCourse> getListeDeCourse() {
      return this.listeDeCourse;
   }
   
   public void setListeDeCourse(Set<ListeDeCourse> listeDeCourses) {
      this.listeDeCourse = listeDeCourses;
   }
   
   private Set<Recette> recette;
   
   @OneToMany
   public Set<Recette> getRecette() {
      return this.recette;
   }
   
   public void setRecette(Set<Recette> recettes) {
      this.recette = recettes;
   }
   
   private EtatFrigo etatFrigo;
   
   @ManyToOne(optional=false)
   public EtatFrigo getEtatFrigo() {
      return this.etatFrigo;
   }
   
   public void setEtatFrigo(EtatFrigo etatFrigo) {
      this.etatFrigo = etatFrigo;
   }
   
   }
