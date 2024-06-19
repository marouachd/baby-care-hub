package co.simplon.babycarehub.dtos;

import java.sql.Date;

public class ChildActivityCreateDto {

    private Long childId;

    private Long activityId;

    private String commentaire;

    private Date date;

    public ChildActivityCreateDto() {

    }

    public Long getChildId() {
	return childId;
    }

    public void setChildId(Long childId) {
	this.childId = childId;
    }

    public Long getActivityId() {
	return activityId;
    }

    public void setActivityId(Long activityId) {
	this.activityId = activityId;
    }

    public String getCommentaire() {
	return commentaire;
    }

    public void setCommentaire(String commentaire) {
	this.commentaire = commentaire;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    @Override
    public String toString() {
	return "{childId=" + childId + ", activityId="
		+ activityId + ", commentaire="
		+ commentaire + ", date=" + date + "}";
    }

}
