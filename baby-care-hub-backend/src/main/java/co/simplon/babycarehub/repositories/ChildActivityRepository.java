package co.simplon.babycarehub.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.entities.ChildActivityEntity;

public interface ChildActivityRepository
	extends JpaRepository<ChildActivityEntity, Long> {

    List<ChildActivityEntity> findByDateAndChildId(
	    Date date, Long childId);
}
