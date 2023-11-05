package co.simplon.babycarehub.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends AbstractEntity {

    @Column(name = "role_name")
    private String name;

    public RoleEntity() {

    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return "{name=" + name + "}";
    }

}
