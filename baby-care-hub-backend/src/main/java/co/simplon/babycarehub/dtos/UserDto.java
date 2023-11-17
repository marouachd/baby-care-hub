package co.simplon.babycarehub.dtos;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.babycarehub.customValidation.PictureSize;
import co.simplon.babycarehub.customValidation.PictureType;

public class UserDto {

    private String mailAdress;

    private String phoneNumber;

    private String password;

    private String firstName;

    private String lastName;

    private String pseudoName;

    @PictureType
    @PictureSize
    private MultipartFile personalPicture;

    public Long roleId;

    public UserDto() {

    }

    public String getMailAdress() {
	return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
	this.mailAdress = mailAdress;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
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

    public Long getRoleId() {
	return roleId;
    }

    public void setRoleId(Long roleId) {
	this.roleId = roleId;
    }

    @Override
    public String toString() {
	return "{mailAdress=" + mailAdress
		+ ", phoneNumber=" + phoneNumber
		+ ", password=" + password + ", firstName="
		+ firstName + ", lastName=" + lastName
		+ ", pseudoName=" + pseudoName
		+ ", personalPicture=" + personalPicture
		+ ", roleId=" + roleId + "}";
    }

}
