package co.simplon.babycarehub.meals;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository
	extends JpaRepository<MealEntity, Long> {

}
