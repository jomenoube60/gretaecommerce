package fr.greta.filrouge.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.greta.filrouge.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
