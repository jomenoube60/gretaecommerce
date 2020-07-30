package fr.greta.filrouge.model;

import java.io.Serializable;

public class Category implements Serializable {
	private String codeCategory;
	private String nameCategory;
	public String getCodeCategory() {
		return codeCategory;
	}
	public void setCodeCategory(String codeCategory) {
		this.codeCategory = codeCategory;
	}
	public String getNameCategory() {
		return nameCategory;
	}
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
}
