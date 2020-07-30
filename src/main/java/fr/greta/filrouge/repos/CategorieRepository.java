package fr.greta.filrouge.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.greta.filrouge.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

	

	List<Categorie> findByNomContaining(String nomCategorie);

	

}
