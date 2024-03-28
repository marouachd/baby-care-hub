package co.simplon.babycarehub.actualities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.simplon.babycarehub.activities.ChildActivityEntity;
import co.simplon.babycarehub.babybottels.BabyBottelEntity;
import co.simplon.babycarehub.entities.AbstractEntity;
import co.simplon.babycarehub.meals.MealEntity;
import co.simplon.babycarehub.naps.NapEntity;
import co.simplon.babycarehub.outingandleisure.ChildLeisureEntity;

@Entity
@Table(name = "actualities")
public class ActualityEntity extends AbstractEntity {

    @Column(name = "child_id")
    private Long childId;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private ChildActivityEntity childActivity;

    @ManyToOne
    @JoinColumn(name = "baby_bottel_id")
    private BabyBottelEntity babybottel;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private MealEntity meal;

    @ManyToOne
    @JoinColumn(name = "nap_id")
    private NapEntity nap;

    @ManyToOne
    @JoinColumn(name = "leisure_id")
    private ChildLeisureEntity leisure;

    public ActualityEntity() {

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

    public ChildActivityEntity getChildActivity() {
	return childActivity;
    }

    public void setChildActivity(
	    ChildActivityEntity childActivity) {
	this.childActivity = childActivity;
    }

    public BabyBottelEntity getBabybottel() {
	return babybottel;
    }

    public void setBabybottel(BabyBottelEntity babybottel) {
	this.babybottel = babybottel;
    }

    public MealEntity getMeal() {
	return meal;
    }

    public void setMeal(MealEntity meal) {
	this.meal = meal;
    }

    public NapEntity getNap() {
	return nap;
    }

    public void setNap(NapEntity nap) {
	this.nap = nap;
    }

    public ChildLeisureEntity getLeisure() {
	return leisure;
    }

    public void setLeisure(ChildLeisureEntity leisure) {
	this.leisure = leisure;
    }

    @Override
    public String toString() {
	return "{childId=" + childId + ", date=" + date
		+ ", childActivity=" + childActivity
		+ ", babybottel=" + babybottel + ", meal="
		+ meal + ", nap=" + nap + ", leisure="
		+ leisure + "}";
    }

}
