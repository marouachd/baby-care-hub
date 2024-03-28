package co.simplon.babycarehub.outingandleisure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.simplon.babycarehub.entities.AbstractEntity;

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
