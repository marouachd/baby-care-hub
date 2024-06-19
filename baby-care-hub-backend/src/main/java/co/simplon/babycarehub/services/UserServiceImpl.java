package co.simplon.babycarehub.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Value("${baby-care-hub.uploads.location}")
    private String uploadDir;

    private PersonRepository persons;

    private static UserRepository users;

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

    @Transactional
    public void create(UserDto inputs) {
	System.out.println(inputs + "this inputs");

	UserEntity user = new UserEntity();
	user.setMailAddress(inputs.getMailAddress());
	user.setPhoneNumber(inputs.getPhoneNumber());
	String hashPassword = authHelper
		.encode(inputs.getPassword());
	user.setPassword(hashPassword);

	Long roleId = inputs.getRoleId();
	RoleEntity role = roles.getReferenceById(roleId);
	user.setRoleId(role);

	PersonEntity person = new PersonEntity();
	if (inputs.getPersonalPicture() != null) {
	    MultipartFile file = inputs
		    .getPersonalPicture();
	    String baseName = UUID.randomUUID().toString();
	    String fileName = baseName
		    + file.getOriginalFilename();
	    try {
		store(file, fileName);
	    } catch (IOException e) {

		e.printStackTrace();
	    }
	    person.setIdentityPhotoName(fileName);
	}

	person.setFirstName(inputs.getFirstName());
	person.setLastName(inputs.getLastName());
	person.setPseudoName(inputs.getPseudoName());

	persons.save(person);
	PersonEntity savedPerson = persons.save(person);
	user.setPersonId(savedPerson);
	users.save(user);
	System.out.println(user + "ceci est user");
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

	UserEntity user = users
		.findUserByPseudoName(pseudoName);
	System.out
		.println("L'utilisateur avec le pseudoName "

			+ pseudoName + user);

	if (user != null) {

	    user.setMailAddress(inputs.getMailAdress());
	    user.setPhoneNumber(inputs.getPhoneNumber());

	    Long roleId = inputs.getRoleId();
	    RoleEntity role = roles
		    .getReferenceById(roleId);
	    user.setRoleId(role);

	    PersonEntity person = persons.findByPseudoName(
		    inputs.getPseudoName());

	    if (inputs.getPersonalPicture() != null) {
		String identityPhotoName = person
			.getIdentityPhotoName();
		if (identityPhotoName != null) {
		    Path existPicture = Paths.get(uploadDir,
			    identityPhotoName);
		    existPicture.toFile().delete();
		}

		MultipartFile file = inputs
			.getPersonalPicture();
		String baseName = UUID.randomUUID()
			.toString();
		String fileName = baseName
			+ inputs.getPersonalPicture()
				.getOriginalFilename();
		try {
		    store(file, fileName);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		person.setIdentityPhotoName(fileName);
	    }

	    person.setFirstName(inputs.getFirstName());
	    person.setLastName(inputs.getLastName());
	    person.setPseudoName(inputs.getPseudoName());
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

    private void store(MultipartFile file, String fileName)
	    throws IOException {
	Path uploadPath = Paths.get(uploadDir);
	Path target = uploadPath.resolve(fileName);

	try (InputStream in = file.getInputStream()) {
	    Files.copy(in, target,
		    StandardCopyOption.REPLACE_EXISTING);
	}
    }

    @Override
    public List<UserEntity> getAllChildminder(
	    RoleEntity role) {

	return users.findAllByRoleId(role);
    }

    @Override
    public Boolean existsByEmail(String email) {
	return users.existsByMailAddress(email); // Vérifie si un utilisateur existe avec l'adresse e-mail donnée
    }

    // Méthode pour trouver un utilisateur par adresse e-mail
    @Override
    public UserEntity findByEmail(String email) {
	return users.findByMailAddress(email); // Renvoie l'utilisateur correspondant à l'adresse e-mail donnée
    }
}
