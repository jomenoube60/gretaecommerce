package fr.greta.filrouge.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.greta.filrouge.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

}
