package co.simplon.babycarehub.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "meals")
public class MealEntity extends AbstractEntity {

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "snack_id")
    private SnackEntity snackId;

    @Column(name = "eval")
    private String eval;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "child_id")
    private Long childId;

    @Column(name = "date")
    private Date date;

    public MealEntity() {

    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public SnackEntity getSnackId() {
	return snackId;
    }

    public void setSnackId(SnackEntity snackId) {
	this.snackId = snackId;
    }

    public String getEval() {
	return eval;
    }

    public void setEval(String eval) {
	this.eval = eval;
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
	return "{type=" + type + ", snackId=" + snackId
		+ ", eval=" + eval + ", commentaire="
		+ commentaire + ", childId=" + childId
		+ "}";
    }

}
