package co.simplon.babycarehub.entities;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import co.simplon.babycarehub.meals.MealEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
