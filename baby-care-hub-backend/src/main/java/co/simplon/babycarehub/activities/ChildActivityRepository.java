package co.simplon.babycarehub.activities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildActivityRepository
	extends JpaRepository<ChildActivityEntity, Long> {

}
