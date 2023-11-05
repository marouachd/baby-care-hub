package co.simplon.babycarehub.services;

import org.springframework.stereotype.Service;

import co.simplon.babycarehub.dtos.UserDto;
import co.simplon.babycarehub.entities.PersonEntity;
import co.simplon.babycarehub.entities.RoleEntity;
import co.simplon.babycarehub.entities.UserEntity;
import co.simplon.babycarehub.repositories.PersonRepository;
import co.simplon.babycarehub.repositories.RoleRepository;
import co.simplon.babycarehub.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private PersonRepository persons;

    private UserRepository users;

    private RoleRepository roles;

    public UserServiceImpl(PersonRepository persons,
	    UserRepository users, RoleRepository roles) {

	this.persons = persons;
	this.roles = roles;
	this.users = users;
    }

    @Override
    public void create(UserDto inputs) {

	UserEntity user = new UserEntity();
	user.setMailAdress(inputs.getMailAdress());
	user.setPhoneNumber(inputs.getPhoneNumber());
	user.setPassword(inputs.getPassword());

	Long roleId = inputs.getRoleId();
	RoleEntity role = roles.getReferenceById(roleId);
	user.setRoleId(role);

	PersonEntity person = new PersonEntity();
	person.setFirstName(inputs.getFirstName());
	person.setLastName(inputs.getLastName());
	person.setPseudoName(inputs.getPseudoName());

	persons.save(person);
	PersonEntity savedPerson = persons.save(person);
	user.setPersonId(savedPerson);
	users.save(user);
    }

    public UserEntity findUserByPseudoNameAndRole(
	    String pseudoName, Long roleId) {
	PersonEntity person = persons
		.findByPseudoName(pseudoName);

	return null;
    }

}
