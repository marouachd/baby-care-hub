package co.simplon.babycarehub.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.repositories.GuardModeRepository;

@Service
@Transactional(readOnly = true)
public class BabyCareModeServiceImpl
	implements BabyCareModeService {

    private final GuardModeRepository babyCareMode;

    public BabyCareModeServiceImpl(
	    GuardModeRepository babyCareMode) {
	this.babyCareMode = babyCareMode;

    }

    @Override
    public Collection<LabelValue> getAll() {
	return babyCareMode.findAllProjectBy();
    }

}
