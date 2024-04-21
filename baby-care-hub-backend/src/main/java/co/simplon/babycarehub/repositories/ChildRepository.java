package co.simplon.babycarehub.repositories;

import java.util.List;

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

    // @Query("SELECT c FROM ChildEntity c JOIN c.parentId u WHERE u.id =
    // :parentId")
    // List<ChildEntity> findAllByParentId(
    // @Param("parentId") Long parentId);

    // @Query("SELECT c FROM ChildEntity c JOIN c.childminderCode u WHERE u.id =
    // :childminderCode")
    // List<ChildEntity> findAllByChildminderCode(
    // @Param("childminderCode") Long childminderId);

    @Query("SELECT c FROM ChildEntity c JOIN c.parentId u WHERE u.id = :parentId AND c.isDeleted = false")
    List<ChildEntity> findAllByParentIdAndIsNotDeleted(
	    @Param("parentId") Long parentId);

    @Query("SELECT c FROM ChildEntity c JOIN c.childminderCode u WHERE u.id = :childminderCode AND c.isDeleted = false")
    List<ChildEntity> findAllByChildminderCodeAndIsNotDeleted(
	    @Param("childminderCode") Long childminderId);

}
