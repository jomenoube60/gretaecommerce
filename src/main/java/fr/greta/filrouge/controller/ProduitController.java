package fr.greta.filrouge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProduitController {
  // Produit
  //         show "/produit/{id}"
  //         showAll "/produit/"
  //         add "/produit/restaurateur/add"
  //         update "/produit/restaurateur/update"
  //         delete "/produit/restaurateur/delete"

  @GetMapping("/produit/{id}")
  public ModelAndView showAll( ModelAndView mv) {
	  return null;
  }

}
