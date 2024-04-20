package co.simplon.babycarehub.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.entities.ChildEntity;
import co.simplon.babycarehub.entities.History;

public interface HistoryRepository
	extends JpaRepository<History, Long> {

    List<History> findAllByChildminderId(Long id);

    History findByChildIdAndChildminderIdAndStartDate(
	    ChildEntity child, Long childminderId,
	    LocalDate startDate);

}
