package co.simplon.babycarehub.dtos;

import java.sql.Date;

public class MealCreateDto {

    private String type;

    private Long snackId;

    private Long childId;

    private String eval;

    private Date date;

    private String commentaire;

    public MealCreateDto() {

    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public Long getSnackId() {
	return snackId;
    }

    public void setSnackId(Long snackId) {
	this.snackId = snackId;
    }

    public Long getChildId() {
	return childId;
    }

    public void setChildId(Long childId) {
	this.childId = childId;
    }

    public String getEval() {
	return eval;
    }

    public void setEval(String eval) {
	this.eval = eval;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public String getCommentaire() {
	return commentaire;
    }

    public void setCommentaire(String commentaire) {
	this.commentaire = commentaire;
    }

    @Override
    public String toString() {
	return "{type=" + type + ", snackId=" + snackId
		+ ", childId=" + childId + ", eval=" + eval
		+ ", date=" + date + ", commentaire="
		+ commentaire + "}";
    }

}
