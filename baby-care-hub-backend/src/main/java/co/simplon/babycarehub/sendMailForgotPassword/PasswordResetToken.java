package co.simplon.babycarehub.sendMailForgotPassword;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.simplon.babycarehub.entities.AbstractEntity;

@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken extends AbstractEntity {
    @Column(name = "token")
    private String token;
    @Column(name = "email")
    private String email;
    @Column(name = "expiry_date")
    private LocalDateTime expiryTime;

    public PasswordResetToken() {
	super();
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public LocalDateTime getExpiryTime() {
	return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
	this.expiryTime = expiryTime;
    }

    @Override
    public String toString() {
	return "{token=" + token + ", email=" + email
		+ ", expiryTime=" + expiryTime + "}";
    }

}