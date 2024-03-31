package co.simplon.babycarehub.babybottels;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BabyBottelRepository
	extends JpaRepository<BabyBottelEntity, Long> {

    List<BabyBottelEntity> findByDateAndChildId(Date date,
	    Long childId);

}
