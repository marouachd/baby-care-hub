package co.simplon.babycarehub.meals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.simplon.babycarehub.entities.AbstractEntity;

@Entity
@Table(name = "meals")
public class MealEntity extends AbstractEntity {

    @Column(name = "type")
    private String type;

    @Column(name = "snack")
    private String snack;

    @Column(name = "eval")
    private String eval;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "child_id")
    private Long childId;

    public MealEntity() {

    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getSnack() {
	return snack;
    }

    public void setSnack(String snack) {
	this.snack = snack;
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

    @Override
    public String toString() {
	return "{type=" + type + ", snack=" + snack
		+ ", eval=" + eval + ", commentaire="
		+ commentaire + ", childId=" + childId
		+ "}";
    }

}
