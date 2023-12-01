package co.simplon.babycarehub.dtos;

public class ActiveChildDto {

    public String childminderCode;

    public boolean active;

    public ActiveChildDto() {

    }

    public String getChildminderCode() {
	return childminderCode;
    }

    public void setChildminderCode(String childminderCode) {
	this.childminderCode = childminderCode;
    }

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

    @Override
    public String toString() {
	return "{childminderCode=" + childminderCode
		+ ", active=" + active + "}";
    }

}
