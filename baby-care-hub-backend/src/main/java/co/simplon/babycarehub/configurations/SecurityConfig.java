package co.simplon.babycarehub.configurations;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import com.auth0.jwt.algorithms.Algorithm;

import co.simplon.babycarehub.utils.AuthHelper;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Value("${babycarehub.auth.rounds}")
    private int rounds;
    @Value("${babycarehub.auth.issuer}")
    private String issuer;
    @Value("${babycarehub.auth.secret}")
    private String secret;
    @Value("${babycarehub.auth.tokenExpiration}")
    private long tokenExpiration;

    @Bean
    public AuthHelper authHelper() {
	Algorithm algorithm = Algorithm.HMAC256(secret);
	PasswordEncoder encoder = new BCryptPasswordEncoder(
		rounds);

	return new AuthHelper.Builder().algorithm(algorithm)
		.passwordEncoder(encoder).issuer(issuer)
		.expiration(tokenExpiration).build();
    }

    @Bean
    SecurityFilterChain securityFilterChain(
	    HttpSecurity http) throws Exception {
	http.cors().and().csrf().disable()
		.authorizeRequests()
		.antMatchers("/user", "/sign-in", "/role",
			"/password/reset-password/{token}",
			"/password/reset-password")
		.permitAll() // Autorise toutes les requêtes pour les URL spécifiées sans nécessiter
		// d'authentification.
		.anyRequest().authenticated().and() // l'authentification est nécessaire pour toutes les autres URLs de
		// l'application et
		.oauth2ResourceServer().jwt();
	// L'application utilise le protocole OAuth2 pour protéger ses ressources,
	// Ces ressources sont validées à l'aide de jetons JWT.
	return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() throws Exception {
	SecretKeySpec key = new SecretKeySpec(
		secret.getBytes(), "HmacSHA256");
	return NimbusJwtDecoder.withSecretKey(key).build();
    }

}
