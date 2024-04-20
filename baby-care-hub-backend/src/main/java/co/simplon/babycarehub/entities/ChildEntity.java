package co.simplon.babycarehub.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "childs")
public class ChildEntity extends AbstractEntity {

    @Column(name = "birthday_date")
    private LocalDate birthdayDate;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private GenderEntity genderId;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity personId;

    @ManyToOne
    @JoinColumn(name = "guard_id")
    private GuardModeEntity guardId;

    @ManyToOne
    @JoinColumn(name = "childminder_code")
    private UserEntity childminderCode;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private UserEntity parentId;

    @ManyToOne
    @JoinColumn(name = "history_id")
    @JsonBackReference
    private History history;

    @Column(name = "is_active")
    boolean active;

    @Column(name = "is_accepted")
    boolean accepted;

    public ChildEntity() {
    }

    public LocalDate getBirthdayDate() {
	return birthdayDate;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
	this.birthdayDate = birthdayDate;
    }

    public GenderEntity getGenderId() {
	return genderId;
    }

    public void setGenderId(GenderEntity genderId) {
	this.genderId = genderId;
    }

    public PersonEntity getPersonId() {
	return personId;
    }

    public void setPersonId(PersonEntity personId) {
	this.personId = personId;
    }

    public GuardModeEntity getGuardId() {
	return guardId;
    }

    public void setGuardId(GuardModeEntity guardId) {
	this.guardId = guardId;
    }

    public UserEntity getChildminderCode() {
	return childminderCode;
    }

    public void setChildminderCode(
	    UserEntity childminderCode) {
	this.childminderCode = childminderCode;
    }

    public UserEntity getParentId() {
	return parentId;
    }

    public void setParentId(UserEntity parentId) {
	this.parentId = parentId;
    }

    public History getHistory() {
	return history;
    }

    public void setHistory(History history) {
	this.history = history;
    }

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

    public boolean isAccepted() {
	return accepted;
    }

    public void setAccepted(boolean accepted) {
	this.accepted = accepted;
    }

}
