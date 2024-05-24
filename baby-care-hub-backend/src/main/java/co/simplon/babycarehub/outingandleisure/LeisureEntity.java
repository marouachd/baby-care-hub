package co.simplon.babycarehub.outingandleisure;

import co.simplon.babycarehub.entities.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "leisures")
public class LeisureEntity extends AbstractEntity {

    @Column(name = "leisure_name")
    private String name;

    public LeisureEntity() {

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
