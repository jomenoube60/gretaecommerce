package fr.greta.filrouge.controller;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.greta.filrouge.model.Categorie;
import fr.greta.filrouge.model.Produit;
import fr.greta.filrouge.repos.CategorieRepository;
import fr.greta.filrouge.repos.ProduitRepository;



@Controller
public class ProduitController {
	@Autowired
	private ProduitRepository produitRepos;
	@Autowired
	private CategorieRepository categorieRepos;
	Logger logger = LoggerFactory.getLogger(ProduitController.class);
	@GetMapping("/produits")
	
	public ModelAndView afficherProduits(ModelAndView mv) {
		List<Produit> produits = produitRepos.findAll();
		addImage64(produits);
		
		mv.addObject("isRestaurateur", true);
		mv.addObject("produits", produits);

		mv.setViewName("produit/showAll");
		return mv;
	}
	@GetMapping("/restaurateur/produit/add")
	public ModelAndView afficherAddForm(ModelAndView mv) {
		List<Categorie> categories = categorieRepos.findAll();
		Produit produit = new Produit();
		mv.addObject("categorieList", categories);
		mv.addObject("produit", produit);
		mv.setViewName("produit/addForm");
		return mv;
	}
	@PostMapping("/restaurateur/produit/add")
	public ModelAndView traiterAddform(ModelAndView mv, @Valid Produit produit, BindingResult errors) {
		logger.info(errors.toString());
		if(!errors.hasErrors()) {
			produitRepos.save(produit);
			mv.setViewName("redirect:/produits");
		}
		else {
			mv.setViewName("produit/addForm");
		}
		return mv;
	}

	@GetMapping("/restaurateur/produit/update/{id}")
	public ModelAndView afficherUpdateForm(ModelAndView mv, @PathVariable int id) {
		Optional<Produit> produitOpt = produitRepos.findById(id);
		Produit produit = produitOpt.get();
		mv.addObject("produit", produit);
		mv.setViewName("/produit/updateForm");
		return mv;
	}

	@PostMapping("/restaurateur/produit/update/{id}")
	public String traiterUpdateForm(@Valid Produit produit, BindingResult errors) {
		if(!errors.hasErrors()) {
			produitRepos.save(produit);
			return "redirect:/produits";
		}
		else {
			return "produit/updateForm";
		}
	}

	@PostMapping("/restaurateur/produit/delete/{id}")
	@ResponseBody
	public boolean traiterDeleteForm(@PathVariable int id) {
		try {
			produitRepos.deleteById(id);
			return true;
		}catch(Exception ex) {
			return false;
		}		
	}

	@GetMapping("/produit/{id}")
	public ModelAndView showProduit(ModelAndView mv, @PathVariable int id, RedirectAttributes redirectAttrs) {
		Optional<Produit> produitOpt = produitRepos.findById(id);
		if(produitOpt.isPresent()) {
			Produit produit = produitOpt.get();
			mv.addObject("produit",produit);
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
											@RequestParam(name = "name") String nomProduit) {
		mv.addObject("nomProduit", nomProduit);
		List<Produit> produits = produitRepos.findByNomContaining(nomProduit);
		addImage64(produits);
		mv.setViewName("produit/afficherResultat");
		mv.addObject("produits", produits);
		logger.info(produits.toString());
		mv.setViewName("produit/afficherResultat");
		return mv;
	}
	
	//Methode pour image64
	private void addImage64(List<Produit> produits) {
		for (Iterator iterator = produits.iterator(); iterator.hasNext();) {
			Produit produit = (Produit) iterator.next();
			byte[] imageBin = produit.getImage();
			String image64 = Base64Utils.encodeToString(imageBin);
			produit.setImage64(image64);
		
		}
	}
	
}
