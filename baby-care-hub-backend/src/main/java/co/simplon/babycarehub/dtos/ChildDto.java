package co.simplon.babycarehub.dtos;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.babycarehub.customValidation.PictureSize;
import co.simplon.babycarehub.customValidation.PictureType;

public class ChildDto {

    private String firstName;

    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthdayDate;

    private String pseudoName;

    @PictureType
    @PictureSize
    private MultipartFile personalPicture;

    public Long genderId;

    public Long guardId;

    public String childminderCode;

    public ChildDto() {

    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public LocalDate getBirthdayDate() {
	return birthdayDate;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
	this.birthdayDate = birthdayDate;
    }

    public String getPseudoName() {
	return pseudoName;
    }

    public void setPseudoName(String pseudoName) {
	this.pseudoName = pseudoName;
    }

    public MultipartFile getPersonalPicture() {
	return personalPicture;
    }

    public void setPersonalPicture(
	    MultipartFile personalPicture) {
	this.personalPicture = personalPicture;
    }

    public Long getGenderId() {
	return genderId;
    }

    public void setGenderId(Long genderId) {
	this.genderId = genderId;
    }

    public Long getGuardId() {
	return guardId;
    }

    public void setGuardId(Long guardId) {
	this.guardId = guardId;
    }

    public String getChildminderCode() {
	return childminderCode;
    }

    public void setChildminderCode(String childminderCode) {
	this.childminderCode = childminderCode;
    }

    @Override
    public String toString() {
	return "{firstName=" + firstName + ", lastName="
		+ lastName + ", birthdayDate="
		+ birthdayDate + ", pseudoName="
		+ pseudoName + ", personalPicture="
		+ personalPicture + ", genderId=" + genderId
		+ ", guardId=" + guardId
		+ ", childminderCode=" + childminderCode
		+ "}";
    }

}
