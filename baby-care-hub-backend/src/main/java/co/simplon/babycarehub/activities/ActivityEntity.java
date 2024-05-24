package co.simplon.babycarehub.activities;

import co.simplon.babycarehub.entities.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "activities")
public class ActivityEntity extends AbstractEntity {

    @Column(name = "activity_name")
    private String name;

    public ActivityEntity() {

    }

    public String getActivityName() {
	return name;
    }

    public void setActivityName(String activityName) {
	this.name = activityName;
    }

    @Override
    public String toString() {
	return "{name=" + name + "}";
    }

}
