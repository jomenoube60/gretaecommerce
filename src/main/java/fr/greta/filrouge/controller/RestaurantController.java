package fr.greta.filrouge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.greta.filrouge.model.Categorie;
import fr.greta.filrouge.model.Menu;
import fr.greta.filrouge.model.Produit;
import fr.greta.filrouge.model.Restaurant;
import fr.greta.filrouge.repos.CategorieRepository;
import fr.greta.filrouge.repos.MenuRepository;
import fr.greta.filrouge.repos.ProduitRepository;
import fr.greta.filrouge.repos.RestaurantRepository;

import java.util.Iterator;
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
	@Autowired
	private MenuRepository menuRepo;

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mv) {
		mv.setViewName("index");
		Restaurant resto = null ;
		Optional <Restaurant>restoOpt = restoRepo.findById(1);
		if (restoOpt.isPresent()) {
			resto = restoOpt.get();
			mv.addObject("restaurant" , resto);
		}

		List <Categorie> categorieList = categorieRepo.findByActif(true);
		addImage64Categorie(categorieList);
		List <Menu> menuList = menuRepo.findAll();
		mv.addObject("menuList" , menuList);
		mv.addObject("categorieList" , categorieList);
		return mv;
	}

	@RequestMapping(value = {"/recherche"}, method = RequestMethod.GET)
	public  ModelAndView rechercherParMotClé(ModelAndView mv, @RequestParam(name = "query") String query) {
		List <Categorie> categorieList = categorieRepo.findByNomContaining(query);
		addImage64Categorie(categorieList);
		List <Produit> produitListParNom = produitRepo.findByNomContaining(query);
		addImage64Produit(produitListParNom);
		List <Produit> produitListParCategorie = produitRepo.findByCategories_Nom(query);
		addImage64Produit(produitListParCategorie);
		query = query.toLowerCase();
		mv.addObject("categorieList" , categorieList);
		mv.addObject("produitListParNom" , produitListParNom);
		mv.addObject("produitListParCategorie" , produitListParCategorie);

		mv.addObject("query" , query);
		mv.setViewName("restaurant/resultat_recherche");
		return mv;
	}
	
	private void addImage64Categorie(List<Categorie> categories) {
		for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
			Categorie categorie = (Categorie) iterator.next();
			byte[] imageBin = categorie.getImage();

			System.out.println(imageBin);
			if(imageBin != null) {
			
			String image64 = Base64Utils.encodeToString(imageBin);
			categorie.setImage64(image64);
			System.out.println(image64);
			}
		
		}
	}
	
	private void addImage64Produit(List<Produit> produits) {
		for (Iterator iterator = produits.iterator(); iterator.hasNext();) {
			Produit produit = (Produit) iterator.next();
			byte[] imageBin = produit.getImage();

			System.out.println(imageBin);
			if(imageBin != null) {
			
			String image64 = Base64Utils.encodeToString(imageBin);
			produit.setImage64(image64);
			System.out.println(image64);
			}
		
		}
	}
}
