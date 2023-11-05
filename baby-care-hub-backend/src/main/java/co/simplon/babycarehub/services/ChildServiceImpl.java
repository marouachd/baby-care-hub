package co.simplon.babycarehub.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.babycarehub.dtos.ChildDto;
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
	child.setChildId(inputs.getChildId());
	child.setBirthdayDate(inputs.getBirthdayDate());
	Long genderId = inputs.getGenderId();
	GenderEntity gender = genders
		.getReferenceById(genderId);
	child.setGenderId(gender);
	Long guardId = inputs.getGuardId();
	GuardModeEntity guard = guardMode
		.getReferenceById(guardId);
	child.setGuardId(guard);

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

}
