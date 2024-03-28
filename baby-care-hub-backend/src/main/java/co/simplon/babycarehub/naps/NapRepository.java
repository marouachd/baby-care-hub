package co.simplon.babycarehub.naps;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NapRepository
	extends JpaRepository<NapEntity, Long> {

    // NapEntity findByChildIdAndDate(Long childId, Date date);

    NapEntity findByChildIdAndDateAndType(Long childId,
	    Date date, String type);

}
