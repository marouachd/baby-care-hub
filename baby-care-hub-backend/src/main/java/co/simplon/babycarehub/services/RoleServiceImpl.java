package co.simplon.babycarehub.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.repositories.RoleRepository;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository role;

    public RoleServiceImpl(RoleRepository role) {
	this.role = role;
    }

    @Override
    public Collection<LabelValue> getAll() {
	return role.findAllProjectBy();
    }
}
