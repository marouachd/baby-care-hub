package co.simplon.babycarehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.entities.ChildEntity;

public interface ChildRepository
	extends JpaRepository<ChildEntity, Long> {

}
