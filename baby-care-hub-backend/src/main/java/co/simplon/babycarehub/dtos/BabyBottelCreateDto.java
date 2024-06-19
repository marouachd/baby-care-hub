package co.simplon.babycarehub.dtos;

import java.sql.Date;

public class BabyBottelCreateDto {

    private Long childId;

    private String time;

    private double volume;

    private Date date;

    public BabyBottelCreateDto() {

    }

    public Long getChildId() {
	return childId;
    }

    public void setChildId(Long childId) {
	this.childId = childId;
    }

    public String getTime() {
	return time;
    }

    public void setTime(String time) {
	this.time = time;
    }

    public double getVolume() {
	return volume;
    }

    public void setVolume(double volume) {
	this.volume = volume;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    @Override
    public String toString() {
	return "{childId=" + childId + ", time=" + time
		+ ", volume=" + volume + ", date=" + date
		+ "}";
    }

}
