package co.simplon.babycarehub.outingandleisure;

import java.util.Collection;

import co.simplon.babycarehub.dtos.LabelValue;

public interface LeisureService {

    Collection<LabelValue> getAll();

    void create(ChildLeisureCreateDto inputs);

}
