package co.simplon.babycarehub.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import co.simplon.babycarehub.dtos.ChildDetail;
import co.simplon.babycarehub.dtos.ChildDto;
import co.simplon.babycarehub.dtos.ChildUpdateDto;
import co.simplon.babycarehub.entities.ChildEntity;
import co.simplon.babycarehub.entities.GenderEntity;
import co.simplon.babycarehub.entities.GuardModeEntity;
import co.simplon.babycarehub.entities.PersonEntity;
import co.simplon.babycarehub.entities.UserEntity;
import co.simplon.babycarehub.repositories.ChildRepository;
import co.simplon.babycarehub.repositories.GenderRepository;
import co.simplon.babycarehub.repositories.GuardModeRepository;
import co.simplon.babycarehub.repositories.PersonRepository;
import co.simplon.babycarehub.repositories.UserRepository;

@Service
public class ChildServiceImpl implements ChildService {

    @Value("${baby-care-hub.uploads.location}")
    private String uploadDir;

    private GuardModeRepository guardMode;

    private PersonRepository persons;

    private ChildRepository childs;

    private GenderRepository genders;

    private UserRepository users;

    public ChildServiceImpl(ChildRepository childs,
	    GuardModeRepository guardMode,
	    GenderRepository genders,
	    PersonRepository persons,
	    UserRepository users) {
	this.childs = childs;
	this.genders = genders;
	this.persons = persons;
	this.guardMode = guardMode;
	this.users = users;

    }

    @Override
    public void create(ChildDto inputs) {

	ChildEntity child = new ChildEntity();
	String token = inputs.getToken();
	System.out.println("Token from context: " + token);
	System.out.println("token" + token);
	child.setBirthdayDate(inputs.getBirthdayDate());
	Long genderId = inputs.getGenderId();
	GenderEntity gender = genders
		.getReferenceById(genderId);
	child.setGenderId(gender);
	Long guardId = inputs.getGuardId();
	GuardModeEntity guard = guardMode
		.getReferenceById(guardId);
	child.setGuardId(guard);

	Long userId = extractUserIdFromToken(token);
	System.out.println("User Id from Token: " + userId);

	UserEntity parent = users.findUserById(userId);
	child.setParentId(parent);

	String pseudoName = inputs.getChildminderCode();
	UserEntity user = users
		.findUserByPseudoName(pseudoName);

	if (user != null) {
	    child.setChildminderCode(user);

	}

	String fileName = null;
	if (inputs.getPersonalPicture() != null) {
	    MultipartFile file = inputs
		    .getPersonalPicture();
	    String baseName = UUID.randomUUID().toString();
	    fileName = baseName
		    + file.getOriginalFilename();
	    try {
		store(file, fileName);
	    } catch (IOException e) {

		e.printStackTrace();
	    }
	}
	PersonEntity person = new PersonEntity();
	person.setFirstName(inputs.getFirstName());
	person.setLastName(inputs.getLastName());
	person.setPseudoName(inputs.getPseudoName());
	person.setIdentityPhotoName(fileName);
	persons.save(person);
	PersonEntity savedPerson = persons.save(person);
	child.setPersonId(savedPerson);
	childs.save(child);

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
    @Transactional // read only=false!
    public void delete(Long id) {
	System.out.println("Id: " + id);
	ChildEntity child = childs.findChildById(id);
	System.out.println("Person: " + child);
	childs.delete(child);
	PersonEntity person = child.getPersonId();
	persons.delete(person);
    }

    @Override
    public ChildDetail detail(Long id) {
	return childs.findProjectDetailById(id);
    }

    @Override
    public void update(Long id, ChildUpdateDto inputs) {
	String pseudoName = inputs.getPseudoName();

	ChildEntity child = childs
		.findChildByPseudoName(pseudoName);

	child.setBirthdayDate(inputs.getBirthdayDate());
	Long genderId = inputs.getGenderId();
	GenderEntity gender = genders
		.getReferenceById(genderId);
	child.setGenderId(gender);
	Long guardId = inputs.getGuardId();
	GuardModeEntity guard = guardMode
		.getReferenceById(guardId);
	child.setGuardId(guard);

	String ChildminderPseudoName = inputs
		.getChildminderCode();
	UserEntity user = users.findUserByPseudoName(
		ChildminderPseudoName);

	if (user != null) {
	    child.setChildminderCode(user);

	}

	String fileName = null;
	if (inputs.getPersonalPicture() != null) {
	    MultipartFile file = inputs
		    .getPersonalPicture();
	    String baseName = UUID.randomUUID().toString();
	    fileName = baseName
		    + file.getOriginalFilename();
	    try {
		store(file, fileName);
	    } catch (IOException e) {

		e.printStackTrace();
	    }
	}
	PersonEntity person = child.getPersonId();
	person.setFirstName(inputs.getFirstName());
	person.setLastName(inputs.getLastName());
	person.setPseudoName(inputs.getPseudoName());
	person.setIdentityPhotoName(fileName);
	persons.save(person);
	PersonEntity savedPerson = persons.save(person);
	child.setPersonId(savedPerson);
	childs.save(child);

    }

    private Long extractUserIdFromToken(String token) {
	DecodedJWT jwt = JWT.decode(token);
	return jwt.getClaim("User Id").asLong();
    }

    @Override
    public List<ChildEntity> getAll() {
	return childs.findAll();
    }

    @Override
    public List<ChildEntity> getAllByParentId(
	    Long parentId) {

	return childs.findAllByParentId(parentId);
    }

    @Override
    public List<ChildEntity> getAllByChildminderCode(
	    UserEntity childminderCode) {
	return childs
		.findAllByChildminderCode(childminderCode);
    }

}
