package co.simplon.babycarehub.services;

import java.util.List;

import co.simplon.babycarehub.entities.History;

public interface HistoryService {

    List<History> getAllByChildminderId(Long id);

}
