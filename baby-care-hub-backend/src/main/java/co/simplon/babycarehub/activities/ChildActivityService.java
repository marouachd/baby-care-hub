package co.simplon.babycarehub.activities;

import java.util.Collection;

import co.simplon.babycarehub.dtos.LabelValue;

public interface ChildActivityService {

    Collection<LabelValue> getAll();

    void create(ChildActivityCreateDto inputs);

}
