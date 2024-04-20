package co.simplon.babycarehub.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.babycarehub.entities.History;
import co.simplon.babycarehub.repositories.HistoryRepository;

@Service
@Transactional(readOnly = true)
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository histories;

    public HistoryServiceImpl(HistoryRepository histories) {
	this.histories = histories;
    }

    @Override
    public List<History> getAllByChildminderId(Long id) {

	return histories.findAllByChildminderId(id);
    }

}
