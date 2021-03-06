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



/*Categorie
show "/categorie/{id}"
showAll "/categorie/"
add "/produit/categorie/add"
update "/produit/categorie/update"
delete "/produit/categorie/delete"*/

@Controller
public class CategorieController {
	@Autowired
	private CategorieRepository cateRepos;

	@Autowired
	private ProduitRepository produitRepos;

	Logger logger = LoggerFactory.getLogger(CategorieController.class);

	@GetMapping("/categorie")
	public ModelAndView afficherCategories(ModelAndView mv) {
		List<Categorie> categories = cateRepos.findAll();
		addImage64Categorie(categories);
		mv.addObject("isRestaurateur", true);
		mv.addObject("categories", categories);

		mv.setViewName("categorie/afficherActifs");
		return mv;
	}

	
	@GetMapping("/categorie/inactifs")

public ModelAndView afficherCategoriesActives(ModelAndView mv) {
		List<Categorie> categories = cateRepos.findAll();
		addImage64Categorie(categories);
		mv.addObject("isRestaurateur", true);
		mv.addObject("categories", categories);

		mv.setViewName("categorie/afficherDesactifs");
		return mv;
	}

	@GetMapping("/restaurateur/categorie/add")
	public ModelAndView afficherAddForm(ModelAndView mv) {
		List<Categorie> categories = cateRepos.findAll();
		Categorie categorie = new Categorie();
		mv.addObject("categorie", categorie);
		mv.setViewName("categorie/ajoutForm");
		return mv;
	}

	@PostMapping ("/restaurateur/categorie/add")
	public ModelAndView traiterAddForm(ModelAndView mv, @Valid Categorie categorie, BindingResult errors) {
		logger.info(errors.toString());
		if (!errors.hasErrors()){
			cateRepos.save(categorie);
			mv.setViewName("redirect:/categorie");
		}
		else {

			mv.setViewName("categorie/ajoutForm");
		}
		return mv;
	}

	@GetMapping("/restaurateur/categorie/update/{id}")
	public ModelAndView afficherUpdateForm(ModelAndView mv, @PathVariable int id) {
		Optional<Categorie> categorieOpt = cateRepos.findById(id);
		Categorie categorie = categorieOpt.get();
		List<Categorie> categorieImage = new ArrayList<Categorie>();
		categorieImage.add(categorie);
		addImage64Categorie(categorieImage);
		mv.addObject("categorie", categorie);
		mv.setViewName("categorie/updateForm");
		return mv;
	}
	
	@PostMapping ("/restaurateur/categorie/update/{id}")
	public String traiterUpdateForm(@Valid Categorie categorie, BindingResult errors, MultipartFile imageBrut) {
		if (!errors.hasErrors()) {
			boolean actifStatus = cateRepos.findById(categorie.getId()).get().isActif();
			categorie.setActif(actifStatus);
			if(!imageBrut.isEmpty()) {
				try {
					byte[] bytes = imageBrut.getBytes();
					categorie.setImage(bytes);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				byte[] image = cateRepos.findById(categorie.getId()).get().getImage();
				categorie.setImage(image);
			}
			cateRepos.save(categorie);
			return "redirect:/categorie";
		} else {
			return "categorie/updateForm";
		}
	}

	@PostMapping ("/restaurateur/categorie/desactiver/{id}")
	@ResponseBody
	public boolean traiterDesactiverForm(@PathVariable int id) {
		try {
			Optional<Categorie> categorieOpt = cateRepos.findById(id);
			Categorie categorie = categorieOpt.get();
			categorie.setActif(false);
			cateRepos.save(categorie);
			return true;
		}
		catch (Exception ex) {
			return false;
		}

	}

	@PostMapping ("/restaurateur/categorie/activer/{id}")
	@ResponseBody
	public boolean traiterActiverForm(@PathVariable int id) {
		try {
			Optional<Categorie> categorieOpt = cateRepos.findById(id);
			Categorie categorie = categorieOpt.get();
			categorie.setActif(true);
			cateRepos.save(categorie);
			return true;
		}
		catch (Exception ex) {
			return false;
		}

	}

	@GetMapping("/categorie/{id}")
	public ModelAndView afficherCategorie(ModelAndView mv, @PathVariable int id, RedirectAttributes redirectAttrs) {
		Optional<Categorie> categorieOpt = cateRepos.findById(id);
// tester si categorie existe
		if(categorieOpt.isPresent()) {
			Categorie categorie = categorieOpt.get();
			List <Produit> produitList = produitRepos.getFindByCategories_Id(categorie.getId());
			addImage64Produit(produitList);
			List<Categorie> categorieImage = new ArrayList<Categorie>();
			categorieImage.add(categorie);
			addImage64Categorie(categorieImage);
			mv.addObject("categorie", categorie);
			mv.addObject("produitList" , produitList);
			mv.setViewName("categorie/afficher");
			System.out.println(categorie);
		} else {
			redirectAttrs.addFlashAttribute("erreurMsg", "Catégorie introuvable !");
			mv.setViewName("redirect:/categorie");
			System.out.println("non");
		}
		return mv;
	}

	private void addImage64Categorie(List<Categorie> categories) {
		for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
			Categorie categorie = (Categorie) iterator.next();
			if(categorie.getImage() != null) {
				byte[] imageBin = categorie.getImage();
				String image64 = Base64Utils.encodeToString(imageBin);
				categorie.setImage64(image64);
			}

		}
	}

	private void addImage64Produit(List<Produit> produitList) {
		for (Iterator iterator = produitList.iterator(); iterator.hasNext();) {
			Produit produit = (Produit) iterator.next();
			if(produit.getImage() != null) {
				byte[] imageBin = produit.getImage();
				String image64 = Base64Utils.encodeToString(imageBin);
				produit.setImage64(image64);
			}

		}
	}
}
