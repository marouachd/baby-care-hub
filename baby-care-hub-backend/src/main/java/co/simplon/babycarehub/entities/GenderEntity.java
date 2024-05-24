package co.simplon.babycarehub.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "genders")
public class GenderEntity extends AbstractEntity {

    @Column(name = "gender_name")
    private String name;

    public GenderEntity() {

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
