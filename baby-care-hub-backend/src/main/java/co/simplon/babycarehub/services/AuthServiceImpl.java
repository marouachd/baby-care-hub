package co.simplon.babycarehub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import co.simplon.babycarehub.dtos.SignInCredentials;
import co.simplon.babycarehub.dtos.TokenInfo;
import co.simplon.babycarehub.entities.UserEntity;
import co.simplon.babycarehub.repositories.AuthRepository;
import co.simplon.babycarehub.utils.AuthHelper;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    private final AuthHelper authHelper;

    public AuthServiceImpl(AuthHelper authHelper,
	    AuthRepository authRepository) {
	this.authHelper = authHelper;
	this.authRepository = authRepository;
    }

    @Override
    public TokenInfo signIn(SignInCredentials inputs) {
	String identifier = inputs.getMailAdress();
	String candidate = inputs.getPassword();

	UserEntity user = authRepository
		.getByMailAdress(identifier);

	if (user != null) {
	    List<String> roles = new ArrayList<String>();
	    roles.add(user.getRoleId().getName());
	    boolean match = authHelper.matches(candidate,
		    user.getPassword());
	    if (match) {
		String token = authHelper.createJWT(roles,
			user);
		Long userId = user.getId();
		Long roleId = user.getRoleId().getId();
		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setToken(token);
		tokenInfo.setUserId(userId);
		tokenInfo.setRoleId(roleId);
		return tokenInfo;
	    } else {
		throw new BadCredentialsException(
			"Invalides credentials");
	    }
	} else {
	    throw new BadCredentialsException(
		    "Invalides credentials");
	}
    }
}
