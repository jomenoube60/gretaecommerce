package fr.greta.filrouge.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.greta.filrouge.model.Produit;
import fr.greta.filrouge.repos.ProduitRepository;

@Component
public class StringArrayToProduitListConverter implements Converter<String[], List<Produit>> {
	@Autowired
	ProduitRepository produitRepos;
	@Override
	public List<Produit> convert(String[] ids){
		List<Produit> produitList = new ArrayList<>();
		for(String idStr : ids) {
			int id = Integer.parseInt(idStr);
			Optional<Produit> produitOpt = produitRepos.findById(id);
			if(produitOpt.isPresent()) {
				produitList.add(produitOpt.get());
			}
		}
		return produitList;
	}
}