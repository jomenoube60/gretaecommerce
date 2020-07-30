package fr.greta.filrouge;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.greta.filrouge.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {

}
