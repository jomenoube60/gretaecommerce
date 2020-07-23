package fr.greta.filrouge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProduitController {

	@GetMapping("/produit/{id}")
	public ModelAndView show(ModelAndView mv, @PathVariable int id) {
		mv.setViewName("/produit/show");
	return mv;
	}

	@GetMapping("/produit")
	public ModelAndView showAll(ModelAndView mv) {
		mv.setViewName("/produit/showAll");
	return mv;
	}
	
	@GetMapping("/produit/restaurateur/add")
	public ModelAndView addForm(ModelAndView mv) {
		mv.setViewName("/produit/addForm");
	return mv;
	}
	
	@PostMapping("/produit/restaurateur/add")
	public ModelAndView treatAddForm(ModelAndView mv) {
		mv.setViewName("/produit/addForm");
	return mv;
	}
	
	@GetMapping("/produit/restaurateur/update/{id}")
	public ModelAndView treatUpdateForm(ModelAndView mv) {
		mv.setViewName("/produit/updateForm");
	return mv;
	}
	
	@PostMapping("/produit/restaurateur/update/{id}")
	public ModelAndView updateForm(ModelAndView mv) {
		mv.setViewName("/produit/updateForm");
	return mv;
	}
	
	@PostMapping("/produit/restaurateur/delete/{id}")
	public ModelAndView treatDeleteForm(ModelAndView mv) {
		mv.setViewName("/produit/deleteForm");
	return mv;
	}
	

}
