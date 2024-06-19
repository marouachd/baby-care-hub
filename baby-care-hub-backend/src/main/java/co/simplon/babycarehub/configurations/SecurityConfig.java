package co.simplon.babycarehub.configurations;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
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
    @Value("${babycarehub.cors.enabled}")
    private boolean corsEnabled;

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
	http.cors(corsCustomizer())// au lieu de Customizer.withDefaults()
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests((authz) -> {
		    authz.requestMatchers("/user",
			    "/sign-in", "/role", "/gender",
			    "/password/reset-password/{token}",
			    "/password/reset-password",
			    "/forgot-password").permitAll()
			    .anyRequest().authenticated();
		}).oauth2ResourceServer((oauth2) -> oauth2
			.jwt(Customizer.withDefaults()));
	;
	return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() throws Exception {
	SecretKeySpec key = new SecretKeySpec(
		secret.getBytes(), "HmacSHA256");
	return NimbusJwtDecoder.withSecretKey(key).build();
    }

    private Customizer<CorsConfigurer<HttpSecurity>> corsCustomizer() {
	return corsEnabled ? Customizer.withDefaults()
		: cors -> cors.disable();
    }

}
