package co.simplon.babycarehub.services;

import co.simplon.babycarehub.dtos.SignInCredentials;
import co.simplon.babycarehub.dtos.TokenInfo;

public interface AuthService {

    TokenInfo signIn(SignInCredentials inputs);

}
