package co.simplon.babycarehub.outingandleisure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.simplon.babycarehub.entities.AbstractEntity;

@Entity
@Table(name = "childs_leisures")
public class ChildLeisureEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "leisure_id")
    private LeisureEntity leisureId;

    @Column(name = "child_id")
    private Long childId;

    @Column(name = "commentaire")
    private String commentaire;

    public ChildLeisureEntity() {

    }

    public LeisureEntity getLeisureId() {
	return leisureId;
    }

    public void setLeisureId(LeisureEntity leisureId) {
	this.leisureId = leisureId;
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

    @Override
    public String toString() {
	return "{leisureId=" + leisureId + ", childId="
		+ childId + ", commentaire=" + commentaire
		+ "}";
    }

}