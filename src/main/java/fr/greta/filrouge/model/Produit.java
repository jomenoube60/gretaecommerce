package fr.greta.filrouge.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
public class Produit implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Nom de produit ne pas être vide !")
	private String nom;

	@ManyToMany
	private List<Categorie> categories;

	private int quantiteDisponible;

	private double prixHt;

	@Lob
	private byte[] image;
	
	@Transient
	private String image64;

	public boolean actif;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the categories
	 */
	public List<Categorie> getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}
	/**
	 * @return the quantiteDisponible
	 */
	public int getQuantiteDisponible() {
		return quantiteDisponible;
	}
	/**
	 * @param quantiteDisponible the quantiteDisponible to set
	 */
	public void setQuantiteDisponible(int quantiteDisponible) {
		this.quantiteDisponible = quantiteDisponible;
	}
	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}
	/**
	 * @return the image64
	 */
	public String getImage64() {
		return image64;
	}
	/**
	 * @param image64 the image64 to set
	 */
	public void setImage64(String image64) {
		this.image64 = image64;
	}
	public double getPrixHt() {
		return prixHt;
	}
	public void setPrixHt(double prixHt) {
		this.prixHt = prixHt;
	}

	
	public boolean isActif() {
		return actif;
	}
	
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
}
