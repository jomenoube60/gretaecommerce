package fr.greta.filrouge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/user")
public class RestaurantController {
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mv) {
		mv.setViewName("index");
		mv.addObject("idCategorie", "15§");
		mv.addObject("nomCategorie");
		mv.addObject("message", "Bienvenue chez nous !");
		return mv;
//		return new ModelAndView("index", "message", "Hello World !");
	}
}

