package fr.greta.filrouge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.greta.filrouge.model.Menu;

@Controller
public class MenuController {
	// Menu
  //       show "/menu/{id} - menu/show
  //       showAll "/menu/" - menu/show_all
  //       add "/menu/restaurateur/add" - menu/add
  //       update "/menu/restaurateur/update" - menu/update
  //       delete "/menu/restaurateur/delete" - menu/delete

	@GetMapping("/menu/")
	public ModelAndView showAllAction(ModelAndView mv) {
		mv.setViewName("/menu/show_all");
		return mv;
	}

	@GetMapping("/menu/{id}")
	public ModelAndView showAction(ModelAndView mv , @PathVariable("id") int id) {
		mv.setViewName("/menu/show");
		return mv;
	}

	@GetMapping("/menu/restaurateur/add")
	public ModelAndView addFormAction(ModelAndView mv ) {
		mv.setViewName("/menu/add");
		return mv;
	}

	@PostMapping("/menu/restaurateur/add")
	public ModelAndView addAction(ModelAndView mv , Menu menu) {
		mv.setViewName("/menu/add");
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
