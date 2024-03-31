package co.simplon.babycarehub.naps;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NapRepository
	extends JpaRepository<NapEntity, Long> {

    // NapEntity findByChildIdAndDate(Long childId, Date date);

    NapEntity findByChildIdAndDateAndType(Long childId,
	    Date date, String type);

    List<NapEntity> findByDateAndChildIdAndType(Date date,
	    Long childId, String type);

}
