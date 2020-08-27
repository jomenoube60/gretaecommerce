package fr.greta.filrouge.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.greta.filrouge.model.Categorie;
import fr.greta.filrouge.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {

	List<Produit> findByNomContaining(String nomProduit);

	List<Produit> findByCategories_Nom(String nomCategorie);


	List<Produit> getFindByCategories_Id(int categorie_id);
	@Query(nativeQuery = true, 
			value = "select * from produit p" +
					" inner join produit_categories c on c.produit_id=p.id" +
					" where c.categories_id=?1")
	List<Produit> getProductsByCategorie(int categorie_id);
}
