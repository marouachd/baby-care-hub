package co.simplon.babycarehub.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends AbstractEntity {

    @Column(name = "mail_adress")
    private String mailAdress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity personId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleId;

    public UserEntity() {

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

    public PersonEntity getPersonId() {
	return personId;
    }

    public void setPersonId(PersonEntity personId) {
	this.personId = personId;
    }

    public RoleEntity getRoleId() {
	return roleId;
    }

    public void setRoleId(RoleEntity roleId) {
	this.roleId = roleId;
    }

    @Override
    public String toString() {
	return "{mailAdress=" + mailAdress
		+ ", phoneNumber=" + phoneNumber
		+ ", password=" + password + ", personId="
		+ personId + ", roleId=" + roleId + "}";
    }

}
