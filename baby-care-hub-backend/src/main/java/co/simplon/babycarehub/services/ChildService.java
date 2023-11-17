package co.simplon.babycarehub.services;

import java.util.List;

import co.simplon.babycarehub.dtos.ChildDetail;
import co.simplon.babycarehub.dtos.ChildDto;
import co.simplon.babycarehub.dtos.ChildUpdateDto;
import co.simplon.babycarehub.entities.ChildEntity;
import co.simplon.babycarehub.entities.UserEntity;

public interface ChildService {

    void create(ChildDto inputs);

    List<ChildEntity> getAll();

    ChildDetail detail(Long id);

    void update(Long id, ChildUpdateDto inputs);

    void delete(Long id);

    List<ChildEntity> getAllByParentId(Long parentId);


    List<ChildEntity> getAllByChildminderCode(
	    UserEntity childminderCode);

}
