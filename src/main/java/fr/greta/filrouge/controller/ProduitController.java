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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.greta.filrouge.ProduitRepository;
import fr.greta.filrouge.model.Produit;



@Controller
public class ProduitController {
	@Autowired
	private ProduitRepository produitRepos;
	Logger logger = LoggerFactory.getLogger(ProduitController.class);
	@GetMapping("/produits")
	
	public ModelAndView afficherProduits(ModelAndView mv) {
		List<Produit> produits = produitRepos.findAll();
		
		mv.addObject("isRestaurateur", true);
		mv.addObject("produits", produits);

		mv.setViewName("produit/showAll");
		return mv;
	}
	@GetMapping("/restaurateur/produit/add")
	public ModelAndView afficherAddForm(ModelAndView mv) {
		Produit produit = new Produit();
		mv.addObject("produit", produit);
		mv.setViewName("produit/addForm");
		return mv;
	}
	@PostMapping("/restaurateur/produit/add")
	public ModelAndView traiterAddform(ModelAndView mv, @Valid Produit produit, BindingResult errors) {
		logger.info(errors.toString());
		if(!errors.hasErrors()) {
			produitRepos.save(produit);
			mv.setViewName("redirect:/user/produits");
		}
		else {
			mv.setViewName("produit/addForm");
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
		return "redirect:/user/produits";
	}

	@PostMapping("/restaurateur/produit/delete/{id}")
	public String traiterDeleteForm() {
		return "redirect:/user/produits";
	}

	@GetMapping("/produit/{id}")
	public ModelAndView showProduit(ModelAndView mv, @PathVariable int id, RedirectAttributes redirectAttrs) {
		Optional<Produit> produitOpt = produitRepos.findById(id);
		if(produitOpt.isPresent()) {
			Produit produit = produitOpt.get();
			mv.setViewName("produit/show");	
		}
		else {
			redirectAttrs.addFlashAttribute("erreurMsg", "Produit introuvable !");
			mv.setViewName("redirect:/user/produits");
		}
		return mv;
	}

	@GetMapping("/produit/search")
	public ModelAndView searchProduit(ModelAndView mv,
											@RequestParam(name = "name") String nameProduit) {
		mv.addObject("nameProduit", nameProduit);
		mv.setViewName("produit/afficherResultat");
		return mv;
	}

}
