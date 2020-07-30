package fr.greta.filrouge.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.greta.filrouge.model.Categorie;
import fr.greta.filrouge.repos.CategorieRepository;
@Component
public class StringArrayToCategorieListConverter implements Converter<String[], List<Categorie>> {
	@Autowired
	CategorieRepository catRepos;
	@Override
	public List<Categorie> convert(String[] ids){
		List<Categorie> categories = new ArrayList<>();
		for(String idStr : ids) {
			int id = Integer.parseInt(idStr);
			Optional<Categorie> catOpt = catRepos.findById(id);
			if(catOpt.isPresent()) {
				categories.add(catOpt.get());
			}
		}
		return categories;
	}
}

