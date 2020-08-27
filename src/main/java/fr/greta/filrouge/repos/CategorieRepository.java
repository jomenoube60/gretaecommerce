package fr.greta.filrouge.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.greta.filrouge.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

	

	List<Categorie> findByNomContaining(String nomCategorie);
	
	List <Categorie> findByActif(boolean bool);

	

}
