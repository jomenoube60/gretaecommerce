package fr.greta.filrouge.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.greta.filrouge.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
