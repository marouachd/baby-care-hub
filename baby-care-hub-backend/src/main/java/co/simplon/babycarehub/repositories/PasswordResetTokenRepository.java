package co.simplon.babycarehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.entities.PasswordResetToken;

public interface PasswordResetTokenRepository
	extends JpaRepository<PasswordResetToken, String> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findAdressMailByToken(String token);
}
