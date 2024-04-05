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

import co.simplon.babycarehub.activities.ChildActivityEntity;
import co.simplon.babycarehub.activities.ChildActivityRepository;
import co.simplon.babycarehub.actualities.ActualityEntity;
import co.simplon.babycarehub.actualities.ActualityRepository;
import co.simplon.babycarehub.dtos.ActiveChildDto;
import co.simplon.babycarehub.dtos.ChildDetail;
import co.simplon.babycarehub.dtos.ChildDto;
import co.simplon.babycarehub.dtos.ChildUpdateDto;
import co.simplon.babycarehub.dtos.DesactiveChildDto;
import co.simplon.babycarehub.entities.ChildEntity;
import co.simplon.babycarehub.entities.GenderEntity;
import co.simplon.babycarehub.entities.GuardModeEntity;
import co.simplon.babycarehub.entities.PersonEntity;
import co.simplon.babycarehub.entities.UserEntity;
import co.simplon.babycarehub.meals.MealEntity;
import co.simplon.babycarehub.meals.MealRepository;
import co.simplon.babycarehub.outingandleisure.ChildLeisureEntity;
import co.simplon.babycarehub.outingandleisure.ChildLeisureRepository;
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

    private ActualityRepository actualities;

    private MealRepository meals;

    private ChildActivityRepository childs_activities;

    private ChildLeisureRepository childs_leisures;

    public ChildServiceImpl(ChildRepository childs,
	    GuardModeRepository guardMode,
	    GenderRepository genders,
	    PersonRepository persons, UserRepository users,
	    ActualityRepository actualities,
	    MealRepository meals,
	    ChildActivityRepository childs_activities,
	    ChildLeisureRepository childs_leisures) {
	this.childs = childs;
	this.genders = genders;
	this.persons = persons;
	this.guardMode = guardMode;
	this.users = users;
	this.actualities = actualities;
	this.meals = meals;
	this.childs_activities = childs_activities;
	this.childs_leisures = childs_leisures;

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
    @Transactional
    public void delete(Long id) {
	// Supprimer l'actualité associée à cette enfant
	ActualityEntity actuality = actualities
		.findByChildId(id);
	if (actuality != null) {
	    actualities.delete(actuality);
	}

	// Supprimer les repas associés à cette enfant
	List<MealEntity> mealList = meals.findByChildId(id);
	System.out.println("mealList" + mealList);
	mealList.clear();

	// Supprimer les activités associées à cette enfant
	List<ChildActivityEntity> activities = childs_activities
		.findByChildId(id);
	activities.forEach(activity -> childs_activities
		.delete(activity));

	// Supprimer les loisirs associés à cette enfant
	List<ChildLeisureEntity> leisures = childs_leisures
		.findByChildId(id);
	leisures.forEach(
		leisure -> childs_leisures.delete(leisure));

	// Supprimer l'entité ChildEntity
	ChildEntity child = childs.findChildById(id);
	if (child != null) {
	    // Supprimer l'entité Person associée
	    PersonEntity person = child.getPersonId();
	    if (person != null) {
		persons.delete(person);
	    }
	    // Supprimer l'entité ChildEntity
	    childs.delete(child);
	}
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
    public void desactive(Long id,
	    DesactiveChildDto inputs) {

	ChildEntity child = childs.findChildById(id);

	if (child != null) {
	    child.setActive(false);
	    childs.save(child);
	}

    }

    @Override
    public void activeChild(Long id,
	    ActiveChildDto inputs) {
	ChildEntity child = childs.findChildById(id);
	child.setActive(true);
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

}
