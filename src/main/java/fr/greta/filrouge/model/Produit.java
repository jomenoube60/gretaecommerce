package fr.greta.filrouge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Produit implements Serializable {
	@Id
	private int id;
	private String nom;
	@NotBlank(message = "Nom de produit ne pas être vide !")
	private String categorie;
	private int quantiteDisponible;

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getQuantiteDisponible() {
		return quantiteDisponible;
	}
	public void setQuantiteDisponible(int quantiteDisponible) {
		this.quantiteDisponible = quantiteDisponible;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
