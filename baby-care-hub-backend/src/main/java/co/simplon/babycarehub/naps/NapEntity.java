package co.simplon.babycarehub.naps;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.simplon.babycarehub.entities.AbstractEntity;

@Entity
@Table(name = "naps")
public class NapEntity extends AbstractEntity {

    @Column(name = "type")
    private String type;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "child_id")
    private Long childId;

    @Column(name = "date")
    private Date date;

    public NapEntity() {

    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public Time getStartTime() {
	return startTime;
    }

    public void setStartTime(Time startTime) {
	this.startTime = startTime;
    }

    public Time getEndTime() {
	return endTime;
    }

    public void setEndTime(Time endTime) {
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

    @Override
    public String toString() {
	return "{type=" + type + ", startTime=" + startTime
		+ ", endTime=" + endTime + ", commentaire="
		+ commentaire + ", childId=" + childId
		+ ", date=" + date + "}";
    }

}
