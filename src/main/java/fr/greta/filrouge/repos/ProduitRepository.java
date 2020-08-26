package fr.greta.filrouge.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.greta.filrouge.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {

	List<Produit> findByNomContaining(String nomProduit);

	List<Produit> findByCategories_Nom(String nomCategorie);
}
