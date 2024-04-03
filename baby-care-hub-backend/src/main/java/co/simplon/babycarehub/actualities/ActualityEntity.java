package co.simplon.babycarehub.actualities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JoinColumn(name = "child_activity_id")
    private ChildActivityEntity childActivity;

    @OneToMany(mappedBy = "actuality")
    @JsonManagedReference
    private List<BabyBottelEntity> babyBottels;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private MealEntity meal;

    @ManyToOne
    @JoinColumn(name = "snack_id")
    private MealEntity snack;

    @OneToMany(mappedBy = "actuality")
    @JsonManagedReference
    private List<NapEntity> nap;

    @ManyToOne
    @JoinColumn(name = "presence_id")
    private NapEntity presence;

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

    public List<BabyBottelEntity> getBabyBottels() {
	return babyBottels;
    }

    public void setBabyBottels(
	    List<BabyBottelEntity> babyBottels) {
	this.babyBottels = babyBottels;
    }

    public MealEntity getMeal() {
	return meal;
    }

    public void setMeal(MealEntity meal) {
	this.meal = meal;
    }

    public MealEntity getSnack() {
	return snack;
    }

    public void setSnack(MealEntity snack) {
	this.snack = snack;
    }

    public List<NapEntity> getNap() {
	return nap;
    }

    public void setNap(List<NapEntity> nap) {
	this.nap = nap;
    }

    public NapEntity getPresence() {
	return presence;
    }

    public void setPresence(NapEntity presence) {
	this.presence = presence;
    }

    public ChildLeisureEntity getLeisure() {
	return leisure;
    }

    public void setLeisure(ChildLeisureEntity leisure) {
	this.leisure = leisure;
    }

    // pas de toString

}
