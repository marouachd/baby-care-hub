package co.simplon.babycarehub.naps;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import co.simplon.babycarehub.actualities.ActualityEntity;
import co.simplon.babycarehub.entities.AbstractEntity;

@Entity
@Table(name = "naps")
public class NapEntity extends AbstractEntity {

    @Column(name = "type")
    private String type;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "child_id")
    private Long childId;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "actuality_id")
    @JsonBackReference
    private ActualityEntity actuality;

    public NapEntity() {

    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getStartTime() {
	return startTime;
    }

    public void setStartTime(String startTime) {
	this.startTime = startTime;
    }

    public String getEndTime() {
	return endTime;
    }

    public void setEndTime(String endTime) {
	this.endTime = endTime;
    }

    public String getCommentaire() {
	return commentaire;
    }

    public void setCommentaire(String commentaire) {
	this.commentaire = commentaire;
    }

    public Long getChildId() {
	return childId;
    }

    public void setChildId(Long childId) {
	this.childId = childId;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public ActualityEntity getActuality() {
	return actuality;
    }

    public void setActuality(ActualityEntity actuality) {
	this.actuality = actuality;
    }

    // pas de tString
}
