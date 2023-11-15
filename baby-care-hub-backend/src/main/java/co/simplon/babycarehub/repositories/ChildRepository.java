package co.simplon.babycarehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.simplon.babycarehub.dtos.ChildDetail;
import co.simplon.babycarehub.entities.ChildEntity;

public interface ChildRepository
	extends JpaRepository<ChildEntity, Long> {

    ChildDetail findProjectDetailById(Long id);

    @Query("SELECT c FROM ChildEntity c JOIN c.personId p WHERE p.pseudoName = :pseudoName")
    ChildEntity findChildByPseudoName(
	    @Param("pseudoName") String pseudoName);

    ChildEntity findChildById(Long id);

}
