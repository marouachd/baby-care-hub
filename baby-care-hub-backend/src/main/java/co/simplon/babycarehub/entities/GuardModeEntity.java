package co.simplon.babycarehub.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "guard_mode")
public class GuardModeEntity extends AbstractEntity {

    @Column(name = "guard_mode_name")
    private String name;

    public GuardModeEntity() {

    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return String.format("{id=%s, name=%s}", getId(),
		name);
    }

}
