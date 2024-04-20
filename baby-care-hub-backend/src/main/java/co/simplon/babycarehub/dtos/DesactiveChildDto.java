package co.simplon.babycarehub.dtos;

import java.time.LocalDateTime;

public class DesactiveChildDto {

    public boolean active;

    private LocalDateTime startDate;

    public DesactiveChildDto() {

    }

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

    public LocalDateTime getStartDate() {
	return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
	this.startDate = startDate;
    }

    @Override
    public String toString() {
	return "{active=" + active + ", startDate="
		+ startDate + "}";
    }

}
