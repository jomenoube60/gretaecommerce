package fr.greta.filrouge.controller;

@Controller
public class MenuController {
	// Menu
  //       show "/menu/{id} - menu/show
  //       showAll "/menu/" - menu/show_all
  //       add "/menu/restaurateur/add" - menu/add
  //       update "/menu/restaurateur/update" - menu/update
  //       delete "/menu/restaurateur/delete" - menu/delete

	@GetMapping("/menu/")
	public MenuAndView showAllAction(MenuAndView mv) {
		mv.setViewName("/menu/show_all");
		return mv;
	}

	@GetMapping("/menu/{id}")
	public MenuAndView showAction(MenuAndView mv , @PathVariable int idmenu) {
		mv.setViewName("/menu/show");
		return mv;
	}

	@GetMapping("/menu/restaurateur/add")
	public MenuAndView addFormAction(MenuAndView mv ) {
		mv.setViewName("/menu/add");
		return mv;
	}

	@PostMapping("/menu/restaurateur/add")
	public MenuAndView addAction(MenuAndView mv , Menu menu) {
		mv.setViewName("/menu/add");
		return mv;
	}

	@GetMapping("/menu/restaurateur/update/{id}")
	public MenuAndView updateFormAction(MenuAndView mv , @PathVariable int id) {
		mv.setViewName("/menu/update");
		mv.addObject("menu" , menu)
		return mv;
	}

	@PostMapping("/menu/restaurateur/update/{id}")
	public MenuAndView updateAction(MenuAndView mv , @PathVariable int id) {
		mv.setViewName("/menu/update");
		return mv;
	}

	@PostMapping("/menu/restaurateur/delete/{id}")
	public MenuAndView deleteAction(MenuAndView mv, @PathVariable int id) {
		mv.setViewName("/menu/delete");
		return mv;
	}

}
