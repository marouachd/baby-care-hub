package co.simplon.babycarehub.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import co.simplon.babycarehub.dtos.ActiveChildDto;
import co.simplon.babycarehub.dtos.ChildDetail;
import co.simplon.babycarehub.dtos.ChildDto;
import co.simplon.babycarehub.dtos.ChildUpdateDto;
import co.simplon.babycarehub.dtos.DesactiveChildDto;
import co.simplon.babycarehub.entities.ChildEntity;
import co.simplon.babycarehub.entities.GenderEntity;
import co.simplon.babycarehub.entities.GuardModeEntity;
import co.simplon.babycarehub.entities.History;
import co.simplon.babycarehub.entities.PersonEntity;
import co.simplon.babycarehub.entities.UserEntity;
import co.simplon.babycarehub.repositories.ChildRepository;
import co.simplon.babycarehub.repositories.GenderRepository;
import co.simplon.babycarehub.repositories.GuardModeRepository;
import co.simplon.babycarehub.repositories.HistoryRepository;
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

    private HistoryRepository histories;

    public ChildServiceImpl(ChildRepository childs,
	    GuardModeRepository guardMode,
	    GenderRepository genders,
	    PersonRepository persons, UserRepository users,
	    HistoryRepository history) {
	this.childs = childs;
	this.genders = genders;
	this.persons = persons;
	this.guardMode = guardMode;
	this.users = users;
	this.histories = history;

    }

    @Override
    @Transactional
    public void create(ChildDto inputs,
	    DesactiveChildDto isActive) {

	ChildEntity child = new ChildEntity();

	child.setBirthdayDate(inputs.getBirthdayDate());
	Long genderId = inputs.getGenderId();
	GenderEntity gender = genders
		.getReferenceById(genderId);
	child.setGenderId(gender);
	Long guardId = inputs.getGuardId();
	GuardModeEntity guard = guardMode
		.getReferenceById(guardId);
	child.setGuardId(guard);

	child.setActive(true);
	child.setAccepted(false);
	// String parentId = inputs.getParentId()
	// Long userId = extractUserIdFromToken(token);
	// UserEntity parent = users.findUserById(userId);

	Long parentId = inputs.getParentId();
	UserEntity parent = users.findUserById(parentId);
	child.setParentId(parent);

	String pseudoName = inputs.getChildminderCode();

	UserEntity chilminderUser = users
		.findUserByPseudoName(pseudoName);

	if (chilminderUser != null) {
	    child.setChildminderCode(chilminderUser);

	}

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

	ChildEntity child = childs.findChildById(id);

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

	PersonEntity person = child.getPersonId();

	if (inputs.getPersonalPicture() != null) {
	    Path existPicture = Paths.get(uploadDir,
		    person.getIdentityPhotoName());
	    MultipartFile file = inputs
		    .getPersonalPicture();
	    String baseName = UUID.randomUUID().toString();
	    String fileName = baseName
		    + inputs.getPersonalPicture()
			    .getOriginalFilename();
	    try {
		store(file, fileName);
	    } catch (IOException e) {

		e.printStackTrace();
	    }
	    person.setIdentityPhotoName(fileName);
	    existPicture.toFile().delete();
	}

	person.setFirstName(inputs.getFirstName());
	person.setLastName(inputs.getLastName());
	person.setPseudoName(inputs.getPseudoName());
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
	    Long childminderId) {
	return childs
		.findAllByChildminderCode(childminderId);
    }

    @Override
    @Transactional
    public void desactive(Long id) {

	ChildEntity child = childs.findChildById(id);
	Long childminderId = child.getChildminderCode()
		.getId();
	System.out.println(childminderId + "childminderId");

	System.out.println(child.getId() + "child ok ");
	System.out.println(child + "child nok ");

	if (child != null) {

	    LocalDate startDate = child.getHistory()
		    .getStartDate();
	    System.out.println(startDate + "start date");
	    History history = histories
		    .findByChildIdAndChildminderIdAndStartDate(
			    child, childminderId,
			    startDate);
	    if (history != null) {
		history.setEndDate(LocalDate.now());
		histories.save(history);
	    }

	    child.setActive(false);
	    child.setChildminderCode(null);
	    child.setAccepted(true);
	    childs.save(child);
	}
    }

    @Override
    public void activeChild(Long id,
	    ActiveChildDto inputs) {
	ChildEntity child = childs.findChildById(id);
	child.setActive(true);
	child.setAccepted(false);
	String childminderPseudoName = inputs
		.getChildminderCode();
	System.out.println("childminderPseudoName: "
		+ childminderPseudoName);
	UserEntity childminderUser = users
		.findUserByPseudoName(
			childminderPseudoName);
	System.out.println(
		"childminderUser: " + childminderUser);
	if (childminderUser != null) {
	    child.setChildminderCode(childminderUser);
	}
	childs.save(child);

    }

    @Override
    public void accepte(Long id) {
	History history = new History();
	ChildEntity child = childs.findChildById(id);
	Long childminderId = child.getChildminderCode()
		.getId();
	history.setChildminderId(childminderId);
	history.setStartDate(LocalDate.now());
	history.setChildId(child);
	histories.save(history);
	child.setHistory(history);
	child.setAccepted(true);
	child.setActive(true);
	childs.save(child);

    }

    @Override
    public void refuseGuard(Long id) {
	ChildEntity child = childs.findChildById(id);
	child.setAccepted(false);
	child.setChildminderCode(null);
	child.setActive(false);
	childs.save(child);

    }

}
