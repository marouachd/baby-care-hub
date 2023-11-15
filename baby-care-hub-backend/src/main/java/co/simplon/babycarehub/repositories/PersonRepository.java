package co.simplon.babycarehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.entities.PersonEntity;

public interface PersonRepository
	extends JpaRepository<PersonEntity, Long> {

    PersonEntity findByPseudoName(String pseudoName);

    String findPseudoNameById(Long id);

}
