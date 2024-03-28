package co.simplon.babycarehub.outingandleisure;

import java.sql.Date;

public class ChildLeisureCreateDto {

    private Long childId;

    private Long leisureId;

    private String commentaire;

    private Date date;

    public ChildLeisureCreateDto() {

    }

    public Long getChildId() {
	return childId;
    }

    public void setChildId(Long childId) {
	this.childId = childId;
    }

    public Long getLeisureId() {
	return leisureId;
    }

    public void setLeisureId(Long leisureId) {
	this.leisureId = leisureId;
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
	return "{childId=" + childId + ", leisureId="
		+ leisureId + ", commentaire=" + commentaire
		+ ", date=" + date + "}";
    }

}
