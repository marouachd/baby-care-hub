package co.simplon.babycarehub.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "histories")
public class History extends AbstractEntity {

    @Column(name = "childminder_id")
    private Long childminderId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "child_id")
    @JsonManagedReference
    private ChildEntity childId;

    public History() {

    }

    public Long getChildminderId() {
	return childminderId;
    }

    public void setChildminderId(Long childminderId) {
	this.childminderId = childminderId;
    }

    public LocalDate getStartDate() {
	return startDate;
    }

    public void setStartDate(LocalDate startDate) {
	this.startDate = startDate;
    }

    public LocalDate getEndDate() {
	return endDate;
    }

    public void setEndDate(LocalDate endDate) {
	this.endDate = endDate;
    }

    public ChildEntity getChildId() {
	return childId;
    }

    public void setChildId(ChildEntity childId) {
	this.childId = childId;
    }

}
