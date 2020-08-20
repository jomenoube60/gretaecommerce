package fr.greta.filrouge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import fr.greta.filrouge.model.Categorie;
import fr.greta.filrouge.model.Restaurant;
import fr.greta.filrouge.repos.CategorieRepository;
import fr.greta.filrouge.repos.RestaurantRepository;

@Controller
//@RequestMapping("/user")
public class RestaurantController {
	@Autowired
	private RestaurantRepository restoRepo;
	@Autowired
	private CategorieRepository categorieRepo;

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
		mv.addObject("categorie" , categorieList);
		return mv;
	}
}
