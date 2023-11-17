package co.simplon.babycarehub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.simplon.babycarehub.dtos.ChildDetail;
import co.simplon.babycarehub.entities.ChildEntity;
import co.simplon.babycarehub.entities.UserEntity;

public interface ChildRepository
	extends JpaRepository<ChildEntity, Long> {

    ChildDetail findProjectDetailById(Long id);

    @Query("SELECT c FROM ChildEntity c JOIN c.personId p WHERE p.pseudoName = :pseudoName")
    ChildEntity findChildByPseudoName(
	    @Param("pseudoName") String pseudoName);

    ChildEntity findChildById(Long id);

    @Query("SELECT c FROM ChildEntity c JOIN c.parentId u WHERE u.id = :parentId")
    List<ChildEntity> findAllByParentId(
	    @Param("parentId") Long parentId);

    @Query(value = "SELECT * FROM childs c WHERE c.childminder_code = CAST(:childminderCode AS varchar)", nativeQuery = true)
    List<ChildEntity> findAllByChildminderCode(
	    @Param("childminderCode") UserEntity childminderCode);
}
