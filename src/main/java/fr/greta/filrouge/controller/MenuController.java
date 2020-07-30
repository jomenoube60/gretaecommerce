package fr.greta.filrouge.controller;


import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.greta.filrouge.MenuRepository;
import fr.greta.filrouge.model.Menu;

@Controller
public class MenuController {
	
	@Autowired
	private MenuRepository menuRepos;

	@GetMapping("/menu")
	public ModelAndView showAllAction(ModelAndView mv) {
		ArrayList<String> menus = new ArrayList<>();
		menus.add("menu 1");
		menus.add("menu 2");
		menus.add("menu 3");
		menus.add("menu 4");


		mv.addObject("isRestaurateur", true);
		mv.addObject("menus", menus);

		mv.setViewName("/menu/showAll");
		return mv;
	}

	@GetMapping("/menu/{id}")
	public ModelAndView showAction(ModelAndView mv , @PathVariable("id") int id , RedirectAttributes redirectAttrs) {
		Optional<Menu> menuOpt = menuRepos.findById(id);
		if(menuOpt.isPresent()) {
			Menu menu = menuOpt.get();
			mv.setViewName("/menu/show");
			mv.addObject(menu);
		} else {
			redirectAttrs.addAttribute("erreurMsg", "Livre introuvable");
			
		}
		return mv;
	}

	@GetMapping("/menu/restaurateur/add")
	public ModelAndView addFormAction(ModelAndView mv) {
		Menu menu = new Menu();
		mv.addObject("menu" , menu);
		mv.setViewName("menu/add");

		return mv;
	}

	@PostMapping("/menu/restaurateur/add")
	public ModelAndView addAction(ModelAndView mv ,@Valid Menu menu, BindingResult errors) {
		if(!errors.hasErrors()) {
			mv.setViewName("redirect:/menu");
		} else {
			mv.setViewName("menu/add");
		}
		return mv;
	}

	@GetMapping("/menu/restaurateur/update/{id}")
	public ModelAndView updateFormAction(ModelAndView mv , @PathVariable("id") int id) {
		mv.setViewName("/menu/update");
		return mv;
	}

	@PostMapping("/menu/restaurateur/update/{id}")
	public ModelAndView updateAction(ModelAndView mv , @PathVariable("id") int id) {
		mv.setViewName("/menu/update");
		return mv;
	}

	@PostMapping("/menu/restaurateur/delete/{id}")
	public ModelAndView deleteAction(ModelAndView mv, @PathVariable("id") int id) {
		mv.setViewName("/menu/delete");
		return mv;
	}

}
