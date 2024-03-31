package co.simplon.babycarehub.babybottels;

import java.sql.Date;
import java.util.List;

public interface BabyBottelService {

    void create(BabyBottelCreateDto inputs);

    List<BabyBottelEntity> getAllByDateAndChildId(Date date,
	    Long childId);

}
