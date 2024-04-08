package co.simplon.babycarehub.sendMailForgotPassword;

public interface SendMailForgotPasswordService {

    void sendForgotPasswordMail(
	    SendMailForgotPasswordDto inputs);

}
