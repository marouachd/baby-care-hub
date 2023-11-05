package co.simplon.babycarehub.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.repositories.GenderRepository;

@Service
@Transactional(readOnly = true)
public class GenderServiceImpl implements GenderService {

    private final GenderRepository gender;

    public GenderServiceImpl(GenderRepository gender) {
	this.gender = gender;
    }

    @Override
    public Collection<LabelValue> getAll() {
	return gender.findAllProjectBy();
    }
}
