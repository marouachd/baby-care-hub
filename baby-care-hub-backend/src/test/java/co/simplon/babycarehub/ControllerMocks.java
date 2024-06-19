package co.simplon.babycarehub;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import co.simplon.babycarehub.controllers.AuthController;

@TestConfiguration
class ControllerMocks {

    @Bean
    AuthController authController() {
	return Mockito.mock(AuthController.class);
    }

}