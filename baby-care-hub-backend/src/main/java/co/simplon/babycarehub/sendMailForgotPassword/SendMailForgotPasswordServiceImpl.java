package co.simplon.babycarehub.sendMailForgotPassword;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import co.simplon.babycarehub.repositories.UserRepository;

@Service
public class SendMailForgotPasswordServiceImpl
	implements SendMailForgotPasswordService {

    private final JavaMailSender javaMailSender;
    private UserRepository users;

    public SendMailForgotPasswordServiceImpl(
	    JavaMailSender javaMailSender,
	    UserRepository users) {
	this.javaMailSender = javaMailSender;
	this.users = users;
    }

    @Override
    public void sendForgotPasswordMail(
	    SendMailForgotPasswordDto inputs) {
	if (existsByEmail(inputs.getPrimaryRecipient())) {

	    try {
		// Créez un objet SimpleMailMessage
		MimeMessage message = javaMailSender
			.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(
			message, true, "UTF-8");

		// Configurez l'expéditeur, le destinataire et l'objet du message
		helper.setFrom(
			"no-reply.dev@readresolve.io");
		helper.setTo(inputs.getPrimaryRecipient());
		helper.setSubject(
			"Réinitialisation de votre mot de passe");

		// Construisez le contenu HTML de l'e-mail
		String htmlContent = "<!DOCTYPE html>"
			+ "<html lang=\"fr\">" + "<head>"
			+ "<meta charset=\"UTF-8\">"
			+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
			+ "<title>Réinitialisation de votre mot de passe</title>"
			+ "</head>"
			+ "<body style=\"font-family: Arial, sans-serif;\">"
			+ "<h2 style=\"color: #333;\">Réinitialisation de votre mot de passe</h2>"
			+ "<p>Bonjour,</p>"
			+ "<p>Vous recevez cet e-mail car vous avez demandé une réinitialisation de votre mot de passe pour votre compte sur notre plateforme.</p>"
			+ "<p>Pour continuer le processus de réinitialisation, veuillez cliquer sur le lien ci-dessous :</p>"
			+ "<p><a href=\"http://127.0.0.1:5173/new-password\" style=\"background-color: #007bff; color: #fff; text-decoration: none; padding: 10px 20px; border-radius: 5px;\">Réinitialiser votre mot de passe</a></p>"
			+ "<p>Si vous n'avez pas demandé cette réinitialisation ou si vous avez des questions, veuillez contacter notre équipe d'assistance à l'adresse suivante : [Adresse e-mail de l'équipe d'assistance].</p>"
			+ "<p>Merci,</p>"
			+ "<p>L'équipe BabyCareHub</p>"
			+ "</body>" + "</html>";

		// Ajoutez le contenu HTML au message
		helper.setText(htmlContent, true);

		// Envoyez le message
		javaMailSender.send(message);

		System.out.println(
			"E-mail de réinitialisation du mot de passe envoyé avec succès à : "
				+ inputs.getPrimaryRecipient());

	    } catch (Exception e) {
		System.out.println(
			"Erreur lors de l'envoi de l'e-mail de réinitialisation du mot de passe : "
				+ e.getMessage());
	    }
	} else {
	    System.out.println(
		    "aucun compte trouvé avec cette adresse mail");
	}
    }

    @Override
    public Boolean existsByEmail(String email) {

	return users.existsByMailAdress(email);
    }

}
