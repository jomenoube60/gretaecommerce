package fr.greta.filrouge.model;

import java.io.Serializable;

public class Produit implements Serializable {
	private int id;
	private String nom;
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
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public int getQuantiteDisponible() {
		return quantiteDisponible;
	}
	public void setQuantiteDisponible(int quantiteDisponible) {
		this.quantiteDisponible = quantiteDisponible;
	}
}
