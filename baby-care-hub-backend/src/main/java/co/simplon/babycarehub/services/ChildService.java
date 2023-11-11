package co.simplon.babycarehub.services;

import java.util.List;

import co.simplon.babycarehub.dtos.ChildDetail;
import co.simplon.babycarehub.dtos.ChildDto;
import co.simplon.babycarehub.dtos.ChildUpdateDto;
import co.simplon.babycarehub.entities.ChildEntity;

public interface ChildService {

    void create(ChildDto inputs);

    List<ChildEntity> getAll();

    void delete(Long id);

    ChildDetail detail(Long id);

    void update(Long id, ChildUpdateDto inputs);

}
