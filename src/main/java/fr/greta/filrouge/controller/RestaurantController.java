package fr.greta.filrouge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.greta.filrouge.model.Categorie;
import fr.greta.filrouge.model.Produit;
import fr.greta.filrouge.model.Restaurant;
import fr.greta.filrouge.repos.CategorieRepository;
import fr.greta.filrouge.repos.ProduitRepository;
import fr.greta.filrouge.repos.RestaurantRepository;

import java.util.List;
import java.util.Optional;


@Controller
//@RequestMapping("/user")
public class RestaurantController {
	@Autowired
	private RestaurantRepository restoRepo;
	@Autowired
	private CategorieRepository categorieRepo;
	@Autowired
	private ProduitRepository produitRepo;

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mv) {
		mv.setViewName("index");
		Restaurant resto = null ;
		Optional <Restaurant>restoOpt = restoRepo.findById(1);
		if (restoOpt.isPresent() ) {
			resto = restoOpt.get();
			mv.addObject("restaurant" , resto);
		}

		List <Categorie> categorieList = categorieRepo.findAll();
		mv.addObject("categorieList" , categorieList);
		return mv;
	}

	@RequestMapping(value = {"/recherche"}, method = RequestMethod.GET)
	public  ModelAndView rechercherParMotClé(ModelAndView mv, @RequestParam(name = "query") String query) {
		List <Categorie> categorieList = categorieRepo.findByNomContaining(query);
		List <Produit> produitListParNom = produitRepo.findByNomContaining(query);
		List <Produit> produitListParCategorie = produitRepo.findByCategories_Nom(query);
		query = query.toLowerCase();
		mv.addObject("categorieList" , categorieList);
		mv.addObject("produitListParNom" , produitListParNom);
		mv.addObject("produitListParCategorie" , produitListParCategorie);

		mv.addObject("query" , query);
		mv.setViewName("restaurant/resultat_recherche");
		return mv;
	}
}
