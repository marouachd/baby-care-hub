package co.simplon.babycarehub.sendMailForgotPassword;

public interface SendMailForgotPasswordService {

    Boolean existsByEmail(String email);

    // void sendForgotPasswordMail(String email, String token);

    void sendForgotPasswordEmail(String email,
	    String token);

}
