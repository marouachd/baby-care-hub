package co.simplon.babycarehub.services;

import java.util.List;

import co.simplon.babycarehub.dtos.ActiveChildDto;
import co.simplon.babycarehub.dtos.ChildDetail;
import co.simplon.babycarehub.dtos.ChildDto;
import co.simplon.babycarehub.dtos.ChildUpdateDto;
import co.simplon.babycarehub.dtos.DesactiveChildDto;
import co.simplon.babycarehub.entities.ChildEntity;

public interface ChildService {

    List<ChildEntity> getAll();

    ChildDetail detail(Long id);

    void update(Long id, ChildUpdateDto inputs);

    void delete(Long id);

    List<ChildEntity> getAllByParentId(Long parentId);

    List<ChildEntity> getAllByChildminderCode(Long id);

    void desactive(Long id, DesactiveChildDto inputs);

    void create(ChildDto inputs,
	    DesactiveChildDto isActive);

    void activeChild(Long id, ActiveChildDto inputs);

    void accepte(Long id);

    void refuseGuard(Long id);

}
