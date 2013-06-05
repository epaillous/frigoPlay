package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;

import java.net.URL;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Recette{
   private String nom;

public void setNom(String value) {
    this.nom = value;
}
@Id
@NotEmpty
public String getNom() {
    return this.nom;
}
private int difficulte;

public void setDifficulte(int value) {
    this.difficulte = value;
}
public int getDifficulte() {
    return this.difficulte;
}
private int prix;

public void setPrix(int value) {
    this.prix = value;
}
public int getPrix() {
    return this.prix;
}
private int calories;

public void setCalories(int value) {
    this.calories = value;
}
public int getCalories() {
    return this.calories;
}
private URL lien;

public void setLien(URL value) {
    this.lien = value;
}
public URL getLien() {
    return this.lien;
}
   private Set<Aliment> ingredient;
   
   @ManyToMany(mappedBy="recette" )
   public Set<Aliment> getIngredient() {
      return this.ingredient;
   }
   
   public void setIngredient(Set<Aliment> ingredients) {
      this.ingredient = ingredients;
   }
   
   }
