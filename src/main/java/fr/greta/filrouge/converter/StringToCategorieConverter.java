package fr.greta.filrouge.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import org.springframework.stereotype.Component;

import fr.greta.filrouge.model.Categorie;
import fr.greta.filrouge.repos.CategorieRepository;

@Component
public class StringToCategorieConverter implements Converter<String, Categorie> {
	@Autowired
	CategorieRepository catRepos;
	@Override
	public Categorie convert(String idStr) {
		int id = Integer.parseInt(idStr);
		Optional<Categorie> catOpt = catRepos.findById(id);
		return catOpt.isPresent()?catOpt.get() : null;
	}
	
}
