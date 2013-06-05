package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class ListeDeCourse{
   private String nom;

public void setNom(String value) {
    this.nom = value;
}
@Id
@NotEmpty
public String getNom() {
    return this.nom;
}
   private User user;
   
   @ManyToOne(optional=false)
   public User getUser() {
      return this.user;
   }
   
   public void setUser(User user) {
      this.user = user;
   }
   
   private Set<Aliment> article;
   
   @ManyToMany(mappedBy="listeDeCourse" )
   public Set<Aliment> getArticle() {
      return this.article;
   }
   
   public void setArticle(Set<Aliment> articles) {
      this.article = articles;
   }
   
   }
