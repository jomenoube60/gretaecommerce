package fr.greta.filrouge.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
class Restaurant  {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @NotBlank(message = "Nom de produit ne pas être vide !")
  private String nom;

  @NotBlank(message = "Adresse ne doit pas être vide !")
  private String adresse;

  @OneToMany
  private List <Categorie> categories;
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getAdresse() {
	return adresse;
}

public void setAdresse(String adresse) {
	this.adresse = adresse;
}

public List<Categorie> getCategories() {
	return categories;
}

public void setCategories(List<Categorie> categories) {
	this.categories = categories;
}


}
