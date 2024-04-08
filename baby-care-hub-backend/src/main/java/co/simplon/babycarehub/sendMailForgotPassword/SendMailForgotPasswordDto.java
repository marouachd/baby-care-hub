package co.simplon.babycarehub.sendMailForgotPassword;

import javax.validation.constraints.Email;

public class SendMailForgotPasswordDto {

    @Email
    private String primaryRecipient;

    public SendMailForgotPasswordDto() {

    }

    public String getPrimaryRecipient() {
	return primaryRecipient;
    }

    public void setPrimaryRecipient(
	    String primaryRecipient) {
	this.primaryRecipient = primaryRecipient;
    }

    @Override
    public String toString() {
	return "{primaryRecipient=" + primaryRecipient
		+ "}";
    }

}
