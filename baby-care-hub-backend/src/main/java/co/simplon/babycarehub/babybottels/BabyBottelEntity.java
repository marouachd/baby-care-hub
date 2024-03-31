package co.simplon.babycarehub.babybottels;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    @Override
    public String toString() {
	return "{volume=" + volume + ", time=" + time
		+ ", childId=" + childId + ", date=" + date
		+ "}";
    }

}
