package fr.greta.filrouge.controller;
import java.util.ArrayList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.greta.filrouge.model.Produit;



public class ProduitController {
	@GetMapping("/produit")
	public ModelAndView afficherLivres(ModelAndView mv) {
		ArrayList<String> products = new ArrayList<>();
		products.add("produit 1");
		products.add("produit 2");
		products.add("produit 3");
		products.add("produit 4");


		mv.addObject("isRestaurateur", true);
		mv.addObject("products", products);

		mv.setViewName("produit/showAll");
		return mv;
	}
	@GetMapping("/restaurateur/produit/add")
	public ModelAndView afficherAddForm(ModelAndView mv) {
		Produit product = new Produit();
		mv.addObject("product", product);
		mv.setViewName("produit/ajoutForm");
		return mv;
	}
	@PostMapping("/restaurateur/produit/add")
	public ModelAndView traiterAddform(ModelAndView mv, @Valid Produit product, BindingResult errors) {
		if(!errors.hasErrors()) {
			mv.setViewName("redirect:/produit");
		}
		else {
			mv.setViewName("produit/ajoutForm");
		}
		return mv;
	}

	@GetMapping("/restaurateur/produit/update/{id}")
	public ModelAndView afficherUpdateForm(ModelAndView mv) {
		mv.setViewName("produit/updateForm");
		return mv;
	}

	@PostMapping("/restaurateur/produit/update/{id}")
	public String traiterUpdateForm() {
		return "redirect:/produit";
	}

	@PostMapping("/restaurateur/produit/delete/{id}")
	public String traiterDeleteForm() {
		return "redirect:/produit";
	}

	@GetMapping("/user/produit/{id}")
	public ModelAndView showProduit(ModelAndView mv) {
		mv.setViewName("produit/show");
		return mv;
	}

	@GetMapping("/user/produit/search")
	public ModelAndView searchProduit(ModelAndView mv,
											@RequestParam(name = "name") String nameProduit) {
		mv.addObject("nameProduit", nameProduit);
		mv.setViewName("produit/afficherResultat");
		return mv;
	}

}
