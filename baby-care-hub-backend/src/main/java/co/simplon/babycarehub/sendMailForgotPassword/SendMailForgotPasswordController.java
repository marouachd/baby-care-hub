package co.simplon.babycarehub.sendMailForgotPassword;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forgot-password")
public class SendMailForgotPasswordController {

    private final SendMailForgotPasswordService service;

    public SendMailForgotPasswordController(
	    SendMailForgotPasswordService service) {

	this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendForgotPasswordMail(
	    @RequestBody SendMailForgotPasswordDto inputs) {
	this.service.sendForgotPasswordMail(inputs);
    }

}
