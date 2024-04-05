package co.simplon.babycarehub.outingandleisure;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildLeisureRepository
	extends JpaRepository<ChildLeisureEntity, Long> {

    List<ChildLeisureEntity> findByDateAndChildId(Date date,
	    Long childId);

    List<ChildLeisureEntity> findByChildId(Long id);

}
