package co.simplon.babycarehub.meals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.simplon.babycarehub.entities.AbstractEntity;

@Entity
@Table(name = "snacks")
public class SnackEntity extends AbstractEntity {

    @Column(name = "snack_name")
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    public SnackEntity() {
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getImagePath() {
	return imagePath;
    }

    public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
    }

    @Override
    public String toString() {
	return "{name=" + name + ", imagePath=" + imagePath
		+ "}";
    }

}
