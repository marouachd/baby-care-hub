package co.simplon.babycarehub.sendMailForgotPassword;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository
	extends JpaRepository<PasswordResetToken, String> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findAdressMailByToken(String token);
}
