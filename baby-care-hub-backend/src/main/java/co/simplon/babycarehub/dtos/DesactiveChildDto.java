package co.simplon.babycarehub.dtos;

public class DesactiveChildDto {

    public boolean active;

    public DesactiveChildDto() {

    }

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

    @Override
    public String toString() {
	return "{active=" + active + "}";
    }

}
