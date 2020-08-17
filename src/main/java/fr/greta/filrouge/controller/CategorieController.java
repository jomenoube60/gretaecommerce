package fr.greta.filrouge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


/*Categorie
show "/categorie/{id}"
showAll "/categorie/"
add "/produit/categorie/add"
update "/produit/categorie/update"
delete "/produit/categorie/delete"*/

@Controller
public class CategorieController {
	@GetMapping("/categorie/{id}")
	public ModelAndView show(ModelAndView mv, @PathVariable int id) {
		mv.setViewName("/categorie/show");
	return mv;
	}

	@GetMapping("/categorie")
	public ModelAndView showAll(ModelAndView mv) {
		mv.setViewName("/categorie/showAll");
	return mv;
	}
	@GetMapping("/categorie/add")
	public ModelAndView addForm(ModelAndView mv) {
		mv.setViewName("/categorie/addForm");
	return mv;
	}
	
	@PostMapping("/categorie/restaurateur/add")
	public ModelAndView treatAddForm(ModelAndView mv) {
		mv.setViewName("/categorie/addForm");
	return mv;
	}
	
	@GetMapping("/categorie/restaurateur/update/{id}")
	public ModelAndView treatUpdateForm(ModelAndView mv) {
		mv.setViewName("/categorie/updateForm");
	return mv;
	}
	
	@PostMapping("/categorie/restaurateur/update/{id}")
	public ModelAndView updateForm(ModelAndView mv) {
		mv.setViewName("/categorie/updateForm");
	return mv;
	}
	
	@PostMapping("/categorie/restaurateur/delete/{id}")
	public ModelAndView treatDeleteForm(ModelAndView mv) {
		mv.setViewName("/categorie/deleteForm");
	return mv;
	}
	

}
