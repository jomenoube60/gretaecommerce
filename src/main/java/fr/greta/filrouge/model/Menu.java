package fr.greta.filrouge.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Menu implements Serializable {

	@Id
	private int id;
	@NotNull
	private double prixHT;
	private String name;
	private boolean disponible;
	
	@ManyToMany
	private List <Produit> produits;

	public double getPrixHT() {
		return prixHT;
	}
	public void setPrixHT(double prixHT) {
		this.prixHT = prixHT;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}



}
