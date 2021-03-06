package fr.greta.filrouge.controller;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;
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
	
//	@GetMapping("/produits")
//	public ModelAndView afficherProduitsInactifs(ModelAndView mv) {
//		List<Produit> produits = produitRepos.findAll();
//		addImage64(produits);
//		mv.addObject("isRestaurateur", true);
//		mv.addObject("produits", produits);
//		mv.setViewName("produit/showAll");
//		return mv;
//	}
	
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
	public ModelAndView traiterAddform(@Valid Produit produit, BindingResult errors, ModelAndView mv) {
		logger.info(errors.toString());
		if(!errors.hasErrors()) {
			produitRepos.save(produit);
			mv.setViewName("redirect:/produits");
		}
		else {
			
			List<Categorie> categories = categorieRepos.findAll();
			mv.addObject("categorieList", categories);
			mv.setViewName("produit/addForm");
		}
		return mv;
	}

	@GetMapping("/restaurateur/produit/update/{id}")
	public ModelAndView afficherUpdateForm(ModelAndView mv, @PathVariable int id) {
		Optional<Produit> produitOpt = produitRepos.findById(id);
		Produit produit = produitOpt.get();
		boolean status = produit.actif;
		List<Produit> produitimage = new ArrayList<Produit>();
		produitimage.add(produit);
		addImage64(produitimage);
		List<Categorie> categories = categorieRepos.findAll();
		mv.addObject(categories);
		mv.addObject("produit", produit);
		mv.setViewName("/produit/updateForm");
		return mv;
	}

	@PostMapping("/restaurateur/produit/update/{id}")
	//on utilise MultipartFile dans les paramètres pour récupérer information venant de ma balise html (name="imageBrut")
	public String traiterUpdateForm(@Valid Produit produit, BindingResult errors, MultipartFile imageBrut, boolean status) { 
		if(!errors.hasErrors()) {
			boolean actifStatus = produitRepos.findById(produit.getId()).get().isActif();
			produit.setActif(actifStatus);	
			//on teste si imageBrut(balise hatml) est vide ou pas
			//si pas vide on remplace l'image d'origine par la nouvelle
			if(!imageBrut.isEmpty()) {
				try {
					byte[] bytes = imageBrut.getBytes();
					produit.setImage(bytes);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				byte[] image = produitRepos.findById(produit.getId()).get().getImage();
				produit.setImage(image);
			}

			produitRepos.save(produit);
			return "redirect:/produits";
		}
		else {
			return "produit/updateForm";
		}
	}

//	Désactive un produit
	@PostMapping("/restaurateur/produit/desactiver/{id}")
	@ResponseBody
	public boolean traiterInactiveForm(@PathVariable int id) {
		try {
			Optional<Produit> produitOpt = produitRepos.findById(id);
			Produit produit = produitOpt.get();
			produit.setActif(false);
			produitRepos.save(produit);
			return true;
		}catch(Exception ex) {
			return false;
		}		
	}
	
//	Active un produit
	@PostMapping("/restaurateur/produit/activer/{id}")
	@ResponseBody
	public boolean traiterActiveForm(@PathVariable int id) {
		try {
			Optional<Produit> produitOpt = produitRepos.findById(id);
			Produit produit = produitOpt.get();
			produit.setActif(true);
			produitRepos.save(produit);
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
			List<Produit> produitimage = new ArrayList<Produit>();
			produitimage.add(produit);
			addImage64(produitimage);
			mv.addObject("produit", produit);
			mv.setViewName("produit/show");	
		}
		else {
			redirectAttrs.addFlashAttribute("erreurMsg", "Produit introuvable !");
			mv.setViewName("redirect:/produits");
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

			System.out.println(imageBin);
			if(imageBin != null) {
			
			String image64 = Base64Utils.encodeToString(imageBin);
			produit.setImage64(image64);
			System.out.println(image64);
			}
		
		}
	}
	
}
