package co.simplon.babycarehub.sendMailForgotPassword;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password")
@CrossOrigin
public class PasswordResetController {

    @Autowired
    private PasswordResetTokenService tokenService;
    @Autowired
    private SendMailForgotPasswordService sendMailForgotPasswordService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @PostMapping("/reset-password")
    public String generateResetToken(
	    @RequestBody String email) {
	// String email = inputs.getPrimaryRecipient();
	try {
	    email = URLDecoder.decode(email, "UTF-8");
	    if (email.endsWith("=")) {
		email = email.substring(0,
			email.length() - 1);
	    }

	} catch (UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	String token = tokenService.generateToken(email);
	// Send email containing the token to the user for password reset
	sendMailForgotPasswordService
		.sendForgotPasswordEmail(email, token);
	System.out.println("email" + email);
	return "Password reset token generated successfully and sent to the user.";
    }

    @GetMapping("/reset-password/{token}")
    public String resetPassword(@PathVariable String token,
	    @RequestParam String password) {
	PasswordResetToken resetToken = tokenService
		.getToken(token);
	System.out.println("token" + token);
	if (resetToken != null && resetToken.getExpiryTime()
		.isAfter(LocalDateTime.now())) {
	    this.passwordResetTokenService
		    .updatePassword(password, token);
	    System.out.println(password + token + "inputs");

	    return "Password reset token is valid. Allow password reset.";
	} else {
	    // Token is invalid or expired
	    return "Password reset token is invalid or expired.";
	}
    }
}