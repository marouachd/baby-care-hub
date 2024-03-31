package co.simplon.babycarehub.activities;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildActivityRepository
	extends JpaRepository<ChildActivityEntity, Long> {

    List<ChildActivityEntity> findByDateAndChildId(
	    Date date, Long childId);
}
