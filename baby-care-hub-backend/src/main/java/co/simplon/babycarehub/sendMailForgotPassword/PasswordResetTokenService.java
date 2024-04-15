package co.simplon.babycarehub.sendMailForgotPassword;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.babycarehub.entities.UserEntity;
import co.simplon.babycarehub.repositories.UserRepository;
import co.simplon.babycarehub.utils.AuthHelper;

@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private UserRepository users;

    private AuthHelper authHelper;

    public PasswordResetTokenService(
	    PasswordResetTokenRepository tokenRepository,
	    UserRepository users, AuthHelper authHelper) {

	this.authHelper = authHelper;
	this.users = users;
	this.tokenRepository = tokenRepository;
    }

    public String generateToken(String email) {
	String token = UUID.randomUUID().toString();
	PasswordResetToken resetToken = new PasswordResetToken();
	resetToken.setToken(token);
	resetToken.setEmail(email);
	resetToken.setExpiryTime(calculateExpiryTime());
	tokenRepository.save(resetToken);
	System.out.println("token" + token);
	return token;
    }

    public PasswordResetToken getToken(String token) {
	return tokenRepository.findByToken(token);
    }

    private LocalDateTime calculateExpiryTime() {
	LocalDateTime now = LocalDateTime.now();
	LocalDateTime expiryDate = now.plus(60,
		ChronoUnit.SECONDS);
	return expiryDate;
    }

    public void updatePassword(String password,
	    String token) {
	// Récupérer l'e-mail de l'utilisateur à partir du token
	PasswordResetToken recordToken = tokenRepository
		.findAdressMailByToken(token);
	String email = recordToken.getEmail();

	// Vérifier si l'e-mail est valide
	if (email != null) {
	    // Trouver l'utilisateur correspondant à l'e-mail
	    UserEntity user = users.findByMailAdress(email);

	    // Vérifier si l'utilisateur existe
	    if (user != null) {
		// Mettre à jour le mot de passe de l'utilisateur
		String hashPassword = authHelper
			.encode(password);
		user.setPassword(hashPassword);
		users.save(user);
	    } else {
		// Gérer le cas où aucun utilisateur n'est trouvé pour l'e-mail
		throw new RuntimeException(
			"User not found for email: "
				+ email);
	    }
	} else {
	    // Gérer le cas où aucun e-mail n'est trouvé pour le token
	    throw new RuntimeException(
		    "Email not found for token: " + token);
	}
    }

}
