package co.simplon.babycarehub.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
