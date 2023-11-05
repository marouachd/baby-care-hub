package co.simplon.babycarehub.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "persons")
public class PersonEntity extends AbstractEntity {

    @Column(name = "pseudo_name")
    private String pseudoName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "identity_photo_name")
    private String identityPhotoName;

    public PersonEntity() {
    }

    public String getPseudoName() {
	return pseudoName;
    }

    public void setPseudoName(String pseudoName) {
	this.pseudoName = pseudoName;
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

    public String getIdentityPhotoName() {
	return identityPhotoName;
    }

    public void setIdentityPhotoName(
	    String identityPhotoName) {
	this.identityPhotoName = identityPhotoName;
    }

    @Override
    public String toString() {
	return "{pseudoName=" + pseudoName + ", firstName="
		+ firstName + ", lastName=" + lastName
		+ ", identityPhotoName=" + identityPhotoName
		+ "}";
    }

}
