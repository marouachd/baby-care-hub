package co.simplon.babycarehub.naps;

import java.sql.Date;
import java.util.List;

public interface NapService {

    void create(NapCreateDto inputs, String type);

    List<NapEntity> getAllByDateAndChildIdAndType(Date date,
	    Long childId, String type);

}
