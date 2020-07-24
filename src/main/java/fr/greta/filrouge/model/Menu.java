package fr.greta.filrouge.model;

import java.io.Serializable;
import javax.validation.constraints.*;

public class Menu implements Serializable {

	@NotNull
	private double prixHT;
	private String name;
	private boolean disponible;

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
