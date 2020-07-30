package fr.greta.filrouge.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import fr.greta.filrouge.model.Produit;

@Entity

public class Categorie {
@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nom;
	
	@ManyToOne
	private Categorie categorieParente;
	
	@ManyToMany
	private List<Produit> produits;
	
	@Lob
	private byte[] image;
	
	@Transient
	private String image64;
	
	public String getImage64() {
		return image64;
	}

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

	public Categorie getCategorieParente() {
		return categorieParente;
	}

	public void setCategorieParente(Categorie categorieParente) {
		this.categorieParente = categorieParente;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public void setImage64(String image64) {
		this.image64 = image64;
	}


	
	
}
