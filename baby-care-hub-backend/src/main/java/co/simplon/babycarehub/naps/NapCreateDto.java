package co.simplon.babycarehub.naps;

import java.sql.Date;

public class NapCreateDto {

    private String type;

    private Long childId;

    private String commentaire;

    private String startTime;

    private String endTime;

    private Date date;

    public NapCreateDto() {

    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
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

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    @Override
    public String toString() {
	return "{type=" + type + ", childId=" + childId
		+ ", commentaire=" + commentaire
		+ ", startTime=" + startTime + ", endTime="
		+ endTime + ", date=" + date + "}";
    }

}
