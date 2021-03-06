package fr.greta.filrouge.controller;



import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.greta.filrouge.model.Categorie;
import fr.greta.filrouge.model.Menu;
import fr.greta.filrouge.model.Produit;
import fr.greta.filrouge.repos.MenuRepository;


@Controller
public class MenuController {

	@Autowired
	private MenuRepository menuRepos;

	Logger logger = LoggerFactory.getLogger(ProduitController.class);
	@GetMapping("/menu")
	public ModelAndView showAllAction(ModelAndView mv) {
		List<Menu> menus = menuRepos.findAll();

		mv.addObject("isRestaurateur", true);
		mv.addObject("menus", menus);

		mv.setViewName("/menu/showAll");
		return mv;
	}

	@GetMapping("/menu/{id}")
	public ModelAndView showAction(ModelAndView mv , @PathVariable("id") int id , RedirectAttributes redirectAttrs) {
		Optional<Menu> menuOpt = menuRepos.findById(id);
		mv.setViewName("/menu/show");
		if(menuOpt.isPresent()) {
			Menu menu = menuOpt.get();
			mv.addObject(menu);
		} else {
			redirectAttrs.addAttribute("erreurMsg", "Menu introuvable");
		}
		return mv;
	}

	@GetMapping("/restaurateur/menu/add")
	public ModelAndView addFormAction(ModelAndView mv) {
		List<Menu> menus = menuRepos.findAll();
		Menu menu = new Menu();
		mv.addObject("menuList", menus);
		mv.addObject("menu" , menu);
		mv.setViewName("menu/add");

		return mv;
	}

	@PostMapping("/restaurateur/menu/add")
	public ModelAndView traiterAddform(@Valid Menu menu, BindingResult errors, ModelAndView mv) {
		logger.info(errors.toString());
		if(!errors.hasErrors()) {
			menuRepos.save(menu);
			mv.setViewName("redirect:/menu");
		}
		else {
			
			List<Menu> menus = menuRepos.findAll();
			mv.addObject("menuList", menu);
			mv.setViewName("menu/add");
		}
		return mv;
	}

	@GetMapping("/menu/restaurateur/update/{id}")
	public ModelAndView updateFormAction(ModelAndView mv , @PathVariable("id") int id) {
		Optional<Menu> menuOpt = menuRepos.findById(id);
		Menu menu = menuOpt.get();
		mv.addObject("menu", menu);
		mv.setViewName("/menu/update");
		return mv;
	}

	@PostMapping("/menu/restaurateur/update/{id}")
	public String updateAction(@Valid Menu menu, BindingResult errors ) {
		logger.info(errors.toString());
		System.out.println(menu);
		if (!errors.hasErrors()){

			menuRepos.save(menu);
			return "redirect:/menu";
		}
		else {
			return "/menu/update";
		}
	}

	@PostMapping("/menu/restaurateur/delete/{id}")
	@ResponseBody
	public boolean deleteAction(ModelAndView mv, @PathVariable("id") int id) {
		try {
			Optional <Menu>  menuOpt = menuRepos.findById(id);
			Menu menu = menuOpt.get();
			menu.setActif(false);
			menuRepos.save(menu);
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

}
