package co.simplon.babycarehub.activities;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.simplon.babycarehub.entities.AbstractEntity;

@Entity
@Table(name = "childs_activities")
public class ChildActivityEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private ActivityEntity activityId;

    @Column(name = "child_id")
    private Long childId;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "time")
    private Time time;

    @Column(name = "date")
    private Date date;

    public ChildActivityEntity() {

    }

    public ActivityEntity getActivityId() {
	return activityId;
    }

    public void setActivityId(ActivityEntity activityId) {
	this.activityId = activityId;
    }

    public Long getChildId() {
	return childId;
    }

    public void setChildId(Long childId) {
	this.childId = childId;
    }

    public String getCommentaire() {
	return commentaire;
    }

    public void setCommentaire(String commentaire) {
	this.commentaire = commentaire;
    }

    public Time getTime() {
	return time;
    }

    public void setTime(Time time) {
	this.time = time;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    @Override
    public String toString() {
	return "{activityId=" + activityId + ", childId="
		+ childId + ", commentaire=" + commentaire
		+ ", time=" + time + ", date=" + date + "}";
    }

}
