package co.simplon.babycarehub.services;

import org.springframework.stereotype.Service;

import co.simplon.babycarehub.dtos.UserDetail;
import co.simplon.babycarehub.dtos.UserDto;
import co.simplon.babycarehub.dtos.UserUpdateDto;
import co.simplon.babycarehub.entities.PersonEntity;
import co.simplon.babycarehub.entities.RoleEntity;
import co.simplon.babycarehub.entities.UserEntity;
import co.simplon.babycarehub.repositories.AuthRepository;
import co.simplon.babycarehub.repositories.PersonRepository;
import co.simplon.babycarehub.repositories.RoleRepository;
import co.simplon.babycarehub.repositories.UserRepository;
import co.simplon.babycarehub.utils.AuthHelper;

@Service
public class UserServiceImpl implements UserService {

    private PersonRepository persons;

    private UserRepository users;

    private RoleRepository roles;

    private AuthRepository authRepository;

    private AuthHelper authHelper;

    public UserServiceImpl(PersonRepository persons,
	    UserRepository users, RoleRepository roles,
	    AuthRepository authRepository,
	    AuthHelper authHelper) {

	this.authHelper = authHelper;
	this.persons = persons;
	this.roles = roles;
	this.users = users;
	this.authRepository = authRepository;
    }

    @Override
    public void create(UserDto inputs) {

	UserEntity user = new UserEntity();
	user.setMailAdress(inputs.getMailAdress());
	user.setPhoneNumber(inputs.getPhoneNumber());
	String hashPassword = authHelper
		.encode(inputs.getPassword());
	user.setPassword(hashPassword);

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
	authRepository.save(user);
    }

    public UserEntity findUserByPseudoNameAndRole(
	    String pseudoName, Long roleId) {
	PersonEntity person = persons
		.findByPseudoName(pseudoName);

	return null;
    }

    @Override
    public UserDetail detail(Long id) {
	return users.findProjectDetailById(id);
    }

    @Override
    public void update(Long id, UserUpdateDto inputs) {
	String pseudoName = inputs.getPseudoName();
	System.out.println(
		"PseudoName à rechercher : " + pseudoName);

	UserEntity user = users
		.findUserByPseudoName(pseudoName);

	if (user != null) {

	    user.setMailAdress(inputs.getMailAdress());
	    user.setPhoneNumber(inputs.getPhoneNumber());
	    String hashPassword = authHelper
		    .encode(inputs.getPassword());
	    user.setPassword(hashPassword);

	    Long roleId = inputs.getRoleId();
	    RoleEntity role = roles
		    .getReferenceById(roleId);
	    user.setRoleId(role);

	    PersonEntity person = persons.findByPseudoName(
		    inputs.getPseudoName());
	    person.setFirstName(inputs.getFirstName());
	    person.setLastName(inputs.getLastName());
	    person.setPseudoName(inputs.getPseudoName());

	    persons.save(person);
	    PersonEntity savedPerson = persons.save(person);
	    user.setPersonId(savedPerson);
	    users.save(user);
	    authRepository.save(user);
	} else {
	    System.out.println(
		    "L'utilisateur avec le pseudoName "
			    + pseudoName
			    + " n'existe pas.");
	}
    }

}
