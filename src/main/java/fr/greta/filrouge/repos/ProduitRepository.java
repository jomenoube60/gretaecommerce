package fr.greta.filrouge.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.greta.filrouge.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {

	List<Produit> findByNomContaining(String nomProduit);

}
