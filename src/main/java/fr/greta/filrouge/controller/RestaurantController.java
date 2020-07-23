package fr.greta.filrouge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestaurantController {
	@GetMapping("/")
	public ModelAndView showAll(ModelAndView mv) {
		mv.setViewName("/index");
	return mv;
	}
}
