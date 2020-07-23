package fr.greta.filrouge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


/*Categorie
show "/categorie/{id}"
showAll "/categorie/"
add "/produit/categorie/add"
update "/produit/categorie/update"
delete "/produit/categorie/delete"*/

@Controller
public class CategorieController {
	@GetMapping("/category/{id}")
	public ModelAndView show(ModelAndView mv, @PathVariable int id) {
		mv.setViewName("/category/show");
	return mv;
	}

	@GetMapping("/category")
	public ModelAndView showAll(ModelAndView mv) {
		mv.setViewName("/category/showAll");
	return mv;
	}
	

}