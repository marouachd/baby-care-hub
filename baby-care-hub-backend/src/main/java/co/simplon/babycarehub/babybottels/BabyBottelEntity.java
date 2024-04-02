package co.simplon.babycarehub.babybottels;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import co.simplon.babycarehub.actualities.ActualityEntity;
import co.simplon.babycarehub.entities.AbstractEntity;

@Entity
@Table(name = "baby_bottels")
public class BabyBottelEntity extends AbstractEntity {

    @Column(name = "volume")
    private double volume;

    @Column(name = "time")
    private String time;

    @Column(name = "child_id")
    private Long childId;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "actuality_id")
    @JsonBackReference
    private ActualityEntity actuality;

    public BabyBottelEntity() {

    }

    public double getVolume() {
	return volume;
    }

    public void setVolume(double volume) {
	this.volume = volume;
    }

    public String getTime() {
	return time;
    }

    public void setTime(String time) {
	this.time = time;
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

    public ActualityEntity getActuality() {
	return actuality;
    }

    public void setActuality(ActualityEntity actuality) {
	this.actuality = actuality;
    }

    // pas de tostring

}
